package main;

import processing.core.PApplet;

public class Main extends PApplet{

	public static void main(String[] args) {
		PApplet.main("main.Main");
	}
	
	
	Logica app;
	
	public void settings() {
		size(500,500);
	}
	
	public void setup() {
		app = new Logica(this);
		
		//this -> Main, PApplet, Object
		
		
		
		/*
		Thread h = new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						
						
					}
				}
		);
		h.start();
		*/
		
		System.out.println("Inicio");
		
		new Thread(
				() -> {
					for(int i=0 ; i< 16 ; i++) {
						System.out.println(""+i);
					}
				}
				).start();
		
		System.out.println("DMI");
		
		
		
	}
	
	public void draw() {
		background(0,0,0);
		
		
		
		
		
		
	}

}
