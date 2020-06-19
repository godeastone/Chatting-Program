package chatting_program;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	ServerSocket serversocket;
	Socket socket;
	BufferedReader reader1, reader2;
	PrintWriter writer;
	Thread t1, t2;
	String in = "";
	String out = "";
	
	
	public static void main(String[] args) {
		new Server();
	}
	
	
	public Server() 
	{
		try {
			t1 = new Thread(this);
			t2 = new Thread(this);
			serversocket = new ServerSocket(5678);
			
			System.out.println("Connecting...");			
			socket = serversocket.accept();
			
			System.out.println("Connected!");
			
			t1.start();
			t2.start();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public void run()
	{
		try {
			if(Thread.currentThread() == t1) {
				while(!in.equals("exit")) {
					
					reader1 = new BufferedReader(new InputStreamReader(System.in));
					writer = new PrintWriter(socket.getOutputStream(), true);
					
					in = reader1.readLine();
				    writer.println(in);
				    
				}
			} else {
				while(!out.equals("END")) {
					
					reader2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = reader2.readLine();
					System.out.println("[From Client] " + out);
					
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