package chatting_program;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable
{

	BufferedReader reader1, reader2;
	PrintWriter writer;
	Socket socket;
	Thread t1, t2;
	String in = "";
	String out = "";
	
	
	public static void main(String[] args)
	{
		new Client();
	}
	
	
	public Client()
	{
		try {
			
			t1 = new Thread(this);
			t2 = new Thread(this);
			socket = new Socket("172.30.1.51", 5678);
			t1.start();
			t2.start();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	public void run(){
		try{
			if (Thread.currentThread() == t2){
				while (!in.equals("END")){

					reader1 = new BufferedReader(new InputStreamReader(System.in));
					writer = new PrintWriter(socket.getOutputStream(), true);
					
					in = reader1.readLine();
					writer.println(in);

				}
			} else {
				while (!out.equals("END")) {

					reader2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = reader2.readLine();
					System.out.println("[From Server] " + out);
				}
			}
			
			writer.close();
			reader1.close();
			reader2.close();
			socket.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
}