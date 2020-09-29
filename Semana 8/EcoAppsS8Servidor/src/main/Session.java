package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.UUID;

public class Session extends Thread{
	
	private Coordenada coordenada;
	private String id;
	private BufferedWriter writer;
	private Socket socket;
	private Servidor observer;
	
	
	public Session(Socket socket) {
		this.socket = socket;
		this.id = UUID.randomUUID().toString();
		coordenada = new Coordenada(0, 0);
	}
	
	
	@Override
	public void run() {
		try {
			//Declaraciones
			InputStream is = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			writer = new BufferedWriter(new OutputStreamWriter(out));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			
			//Recepcion
			while(true) {
				System.out.println("Esperando mensaje...");
				String lastMessage = reader.readLine();
				observer.cuandoLlegueElmensaje(this, lastMessage);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
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
	
	//Metodo de suscripcion
	public void setObserver(Servidor observer) {
		this.observer = observer;
	}
	
	public String getID() {
		return this.id;
	}
	
	public Coordenada getCoordenada() {
		return this.coordenada;
	}


	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
		
	}

}
