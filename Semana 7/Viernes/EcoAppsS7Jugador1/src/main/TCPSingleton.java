package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPSingleton extends Thread{

	
	
	private static TCPSingleton instace;
	
	private TCPSingleton() {}
	
	public static TCPSingleton getInstance() {
		if(instace == null) {
			instace = new TCPSingleton();
		}
		return instace;
	}
	
	
	
	
	
	private BufferedWriter writer;
	private Socket socket;
	private String lastMessage = "0,0";
	private Cliente observer;
	
	
	//Metodo de suscripcion
	public void setCliente(Cliente observer) {
		this.observer = observer;
	}
	
	
	@Override
	public void run() {
		try {
			//Conexion
			System.out.println("Enviando solicitud de conexion...");
			socket = new Socket("127.0.0.1", 5000);
			System.out.println("Conectamos");
			
			
			//Declaraciones
			InputStream is = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			writer = new BufferedWriter(new OutputStreamWriter(out));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			
			//Recepcion
			while(true) {
				System.out.println("Esperando mensaje...");
				lastMessage = reader.readLine();
				System.out.println("Mensaje recibido: " + lastMessage);
				observer.cuandoLlegueElmensaje(lastMessage);
				
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

	public String getLastMessage() {
		return lastMessage;
	}
	
	
	
	
}
