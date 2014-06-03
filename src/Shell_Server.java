
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shell_Server extends UnicastRemoteObject implements Shell{
    public Shell_Server() throws RemoteException{
        super();
    }
    @Override
    public String request(String req) throws RemoteException {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(req);
            //TO DO: Read the response from the process
        } catch (IOException ex) {
            return null;
        }
        return null;
    }

    @Override
    public boolean setFontColor(int color_code) throws RemoteException {
        return false;
       
    }

    @Override
    public boolean setBackgroundColor(int color_code) throws RemoteException {
        return false;
        
    }
    public static void main(String[] args) throws RemoteException, MalformedURLException{
        Shell calculadoraServer = new Shell_Server();
        LocateRegistry.createRegistry(1099);
        String url = "rmi://192.168.1.199:1099/Shell";
        Naming.rebind(url,calculadoraServer);
        
    }
}
