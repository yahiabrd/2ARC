import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPrinter extends Thread{
	
	public static void main(String[] args) {
		new ServerPrinter().start();
	}
	
	@Override
	public void run() {
		synchronized(this){
			try {
				ServerSocket ss = new ServerSocket(5555);
				System.out.println("Waiting for printing");
				while(true) {
					Socket s = ss.accept();
					InetAddress ip = s.getInetAddress();
					if(s.isConnected()) 
						System.out.println(ip + " is connected");
					
					new ClientService(s).start();
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
