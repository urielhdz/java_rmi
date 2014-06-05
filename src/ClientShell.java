import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class ClientShell implements  ActionListener{
    private Screen myScreen;
    private Shell myClientShell;
    
    private JMenuItem credits;
    
    
    public ClientShell(String user, String address){
        try{
            
            myClientShell = (Shell)Naming.lookup(address);
            myScreen = (Screen) myClientShell.getShell(user);
            myScreen.setAddress(address);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showScreen() throws RemoteException{
        JFrame ventana = new JFrame();
        ventana.add(this.myScreen);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setJMenuBar(this.setUpMenuBar());
        ventana.setSize(820,630);
        ventana.setVisible(true);
    }
    
    private JMenuBar setUpMenuBar() throws RemoteException{
        JMenu properties;
        JMenu themes = new JMenu("Themes");
        JMenu file;
        JMenuItem exit;
        //JMenuItem credits;
        JMenuItem setFont = new JMenuItem("Larger");
        JMenuItem setFontLow = new JMenuItem("Smaller");
        JMenuBar mb = new JMenuBar();
        properties = new JMenu("Font");
        properties.add(setFont);
        properties.add(setFontLow);
        Hashtable<String, Theme> table = this.myClientShell.getThemes();

        for (String key: table.keySet()) {
            final String k = key;
            JMenuItem temp = new JMenuItem(key);            
            temp.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Theme t =  ClientShell.this.myClientShell.setTheme(ClientShell.this.myScreen.user,k);
                        ClientShell.this.myScreen.setTheme(t);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ClientShell.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            
            });
            themes.add(temp);
         }
        
        file = new JMenu("File");
        exit = new JMenuItem("Close");
        credits = new JMenuItem("Credits");
        credits.addActionListener(this);
        
        file.add(credits);
        file.add(exit);
        mb.add(file);
        mb.add(themes);
        //mb.add(properties);
        return mb;
    }

    public void actionPerformed(ActionEvent e) {
        
         if(e.getSource()==credits){
             Credits outCredits = new Credits();
             outCredits.setVisible(true);
         }
    
    }
    
    
}