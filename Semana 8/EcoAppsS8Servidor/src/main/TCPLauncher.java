package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class TCPLauncher extends Thread{

	
	
	private static TCPLauncher instace;
	
	private TCPLauncher() {}
	
	public static TCPLauncher getInstance() {
		if(instace == null) {
			instace = new TCPLauncher();
		}
		return instace;
	}
	
	
	private Servidor observer;
	private ServerSocket server;
	private ArrayList<Session> sessions;
	
	
	//Metodo de suscripcion
	public void setServidor(Servidor observer) {
		this.observer = observer;
	}
	
	
	@Override
	public void run() {
		try {
			//Conexion
			sessions = new ArrayList<Session>();
			server = new ServerSocket(5000);
			
			
			while(true) {
				System.out.println("Esperando en el 5000...");
				Socket socket = server.accept();
				Session session = new Session(socket);
				session.setObserver(observer);
				session.start();
				
				sessions.add(session);
				
				System.out.println("Cliente conectado");
			}
			
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Session> getSessions() {
		return this.sessions;
	}
	
	
	
	
	
	
}
