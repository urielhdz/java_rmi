import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServidorShell extends UnicastRemoteObject implements Shell {

	

	Hashtable<String,Screen> dictionariesShells =new Hashtable<String,Screen>();
        Hashtable<String,Theme> dictionariesThemes =new Hashtable<String,Theme>();
	public ServidorShell() throws RemoteException{
            super();
            dictionariesThemes.put("iPlastic", new Theme("#eeeeee","#0066ff"));
            dictionariesThemes.put("Twilight", new Theme("#141414","#5a575d"));
            dictionariesThemes.put("Solarized", new Theme("#fdf6e3","#93a1a1"));
	}

	public Screen getShell(String user) throws RemoteException{
		
            Screen myScreen;

            if ( this.dictionariesShells.containsKey(user) ){
                    System.out.println("Loading Shell");
                    myScreen = (Screen) this.dictionariesShells.get(user);
            }else{
                    System.out.println("New Shell was created ");
                    myScreen = new Screen(user);
                    this.dictionariesShells.put(user, myScreen);
            }

            return myScreen;			
	
	}

	public String getCommand(String command) throws RemoteException{
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            String final_answer = "";
            try {
                Process process = builder.start();
                String line;
                InputStream stdout = process.getInputStream ();
                
                BufferedReader reader = new BufferedReader (new InputStreamReader(stdout));
                while ((line = reader.readLine ()) != null) {
                    final_answer += line;
                }
            } catch (IOException ex) {
                Logger.getLogger(ServidorShell.class.getName()).log(Level.SEVERE, null, ex);
            }
            return final_answer;
	}

	
        public static void main(String[] args) throws Exception{
		System.out.println("Comenzando la ejecución del server ");
                LocateRegistry.createRegistry(1099);
		Shell myServerShell = new ServidorShell();

		Naming.rebind( "rmi://localhost:1099/Shell" , myServerShell);
		System.out.println("Servidor en linea...");
	}

    @Override
    public String request(String req) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Hashtable<String, Theme> getThemes() throws RemoteException {
        return this.dictionariesThemes;
    }

    @Override
    public Theme setTheme(String user, String theme) throws RemoteException {
        if ( this.dictionariesShells.containsKey(user) && this.dictionariesThemes.containsKey(theme)){
            Screen s = (Screen) this.dictionariesShells.get(user);
            Theme t = (Theme) this.dictionariesThemes.get(theme);
            s.setTheme(t);
            return t;
        }
        else return null;
        
    }


}