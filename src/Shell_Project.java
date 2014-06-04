
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Shell_Project {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String user = JOptionPane.showInputDialog("Who are you?");
        try {
            ClientShell myCliente = new ClientShell(user);
            myCliente.showScreen();
        } catch (RemoteException ex) {
            Logger.getLogger(ClientShell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
