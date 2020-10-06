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

import com.google.gson.Gson;

import model.Movimiento;
import model.Nombre;
import processing.core.PApplet;

public class Cliente extends PApplet {
	
	private BufferedWriter writer;
	private Socket socket;

	public static void main(String[] args) {
		PApplet.main("main.Cliente");

	}

	public void settings() {
		size(500, 500);
	}

	public void setup() {
		initConnection();
	}

	public void draw() {
		background(255, 0, 0);
	}

	public void initConnection() {
		new Thread(

				() -> {
					try {

						System.out.println("Conectadome con el servidor...");
						socket = new Socket("127.0.0.1", 6000); // Lanzar la conexion
						System.out.println("Conectado!");

						InputStream is = socket.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));

						OutputStream os = socket.getOutputStream();
						writer = new BufferedWriter(new OutputStreamWriter(os));

						while (true) {
							String line = reader.readLine();
							System.out.println("Recibido: " + line);
						}

					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}).start();
	}
	
	
	public void enviar(String mensaje) {
		new Thread(
				()->{
					try {
						writer.write(mensaje+"\n");
						writer.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		).start();
	}
	
	
	public void keyPressed() {
		if(key == 'a' || key == 'A') {
			Movimiento m = new Movimiento(150,123);
			Gson gson = new Gson();
			String json = gson.toJson(m);
			enviar(json);
		}
		
		if(key == 'd' || key == 'D') {
			Nombre n = new Nombre("Jhon Sebastian");
			Gson gson = new Gson();
			String json = gson.toJson(n);
			enviar(json);
		}
	}
	

}
