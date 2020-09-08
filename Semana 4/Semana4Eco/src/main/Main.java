package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
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
			
			
			
			//1. Fase de espera de cliente
			ServerSocket server = new ServerSocket(6000);   //El servidor
			
			System.out.println("Esperando cliente en el canal 6000...");
			Socket socket = server.accept();
			System.out.println("Cliente conectado!");
			
			
			//3. Emepezar el intercambio
			
			//Servidor envia dato
			
			
			//4. Definiciones
			
			OutputStream os = socket.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
			
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			
			Thread.sleep(10000);
			
			
			//6. Servidor envio hola mundo
			writer.write("Hola mundo\n");
			writer.flush();
			
			//7. Servidor en estado espera de mensajes
			String recibido = reader.readLine();
			System.out.println("Respuesta: "+recibido);
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
