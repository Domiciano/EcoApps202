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

public class TCPSingletonJ1 extends Thread{

	
	
	private static TCPSingletonJ1 instace;
	
	private TCPSingletonJ1() {}
	
	public static TCPSingletonJ1 getInstance() {
		if(instace == null) {
			instace = new TCPSingletonJ1();
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
			System.out.println("Esperando en el 5000...");
			ServerSocket server = new ServerSocket(5000);
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
				observer.cuandoLlegueElmensaje("J1", lastMessage);
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
