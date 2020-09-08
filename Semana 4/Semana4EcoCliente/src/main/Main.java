package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import processing.core.PApplet;

public class Main extends PApplet{

	public static void main(String[] args) {
		PApplet.main("main.Main");

	}
	
	public void settings() {
		size(500,500);
	}
	
	public void setup() {
		new Thread(
				
				()->{
					initConnection();
				}
				).start();
		
	}
	
	public void draw() {
		background(255,0,0);
	}

	
	
	public void initConnection() {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			String motox4 = inetAddress.getHostAddress();
			System.out.println(motox4);
			
			boolean conectado = inetAddress.isReachable(500);
			
			System.out.println("Conectado: "+ conectado);
			
			
			//2. Cliente manda solicitud
			System.out.println("Conectadome con el servidor...");
			Socket socket = new Socket("127.0.0.1", 6000); //Lanzar la conexion
			System.out.println("Conectado!");
			
			
			//3. Emepezar el intercambio
			
			//4. Definiciones
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			OutputStream os = socket.getOutputStream();
			BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os));
			
			//5 Esperando...
			System.out.println("Esperando mensaje...");
			String mensajeRecibido = reader.readLine();
			System.out.println("String recibido: "+mensajeRecibido);
			
			
			//8. Envio de mensaje
			String respuesta = "Hola de vuelta";
			writer.write(respuesta + "\n");
			writer.flush();
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
