import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientService extends Thread{
	
	private Socket s;
	
	public ClientService(Socket s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		synchronized(this){
			try {		
				//definir les I/O
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				
				//traitement
				int nb = is.read();
				System.out.println("Printing in progress");
				
				
				try {
					
					for (int i = 1; i <= nb; i++) {
						System.out.println("Printing n° " + i);
						sleep(6000);
					}
					os.write(1);
				}catch(Exception e) {
					os.write(0);
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				System.out.println(s.getInetAddress() + " is disconnected");
			}
		}
	}
}
