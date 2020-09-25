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

public class TCPSingletonJ2 extends Thread{

	
	
	private static TCPSingletonJ2 instace;
	
	private TCPSingletonJ2() {}
	
	public static TCPSingletonJ2 getInstance() {
		if(instace == null) {
			instace = new TCPSingletonJ2();
		}
		return instace;
	}
	
	
	
	
	
	private BufferedWriter writer;
	private Socket socket;
	private Servidor observer;
	
	
	//Metodo de suscripcion
	public void setServidor(Servidor observer) {
		this.observer = observer;
	}
	
	
	@Override
	public void run() {
		try {
			//Conexion
			System.out.println("Esperando en el 6000...");
			ServerSocket server = new ServerSocket(6000);
			socket = server.accept();
			System.out.println("Conectamos");
			
			
			//Declaraciones
			InputStream is = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			writer = new BufferedWriter(new OutputStreamWriter(out));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			
			//Recepcion
			while(true) {
				System.out.println("Esperando mensaje...");
				String lastMessage = reader.readLine();
				observer.cuandoLlegueElmensaje("J2", lastMessage);
			}
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void enviar(String msg) {
		new Thread(
				
				()->{
					try {
						writer.write(msg + "\n");
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
		).start();
		
		
	}
	
	
	
}
