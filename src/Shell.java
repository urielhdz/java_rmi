import java.rmi.*;
public interface Shell extends Remote{
      public String request(String req) throws RemoteException;
      public boolean setFontColor(int color_code) throws RemoteException;
      public boolean setBackgroundColor(int color_code) throws RemoteException;
      
}
