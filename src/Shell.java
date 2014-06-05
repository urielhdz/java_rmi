import java.rmi.*;
import java.util.Hashtable;
public interface Shell extends Remote{
      public String request(String req) throws RemoteException;
      public Theme setTheme(String user, String theme) throws RemoteException;
      public Screen getShell(String user) throws RemoteException;
      public Hashtable<String,Theme> getThemes() throws RemoteException;
      public String getCommand(String command) throws RemoteException;
     
}
