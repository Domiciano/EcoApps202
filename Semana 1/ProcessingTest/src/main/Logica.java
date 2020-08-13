package main;

import java.util.ArrayList;

import processing.core.PApplet;

public class Logica {
	
	private PApplet app;
	
	
	private ArrayList<Figura> figuras;
	
	public Logica(PApplet app) {
		this.app = app;
		
		
		figuras = new ArrayList<>();
		
		
		Cuadrado d = new Cuadrado();
		Circulo f = new Circulo();
		Triangulo t = new Triangulo();
		
		figuras.add(d);
		figuras.add(f);
		figuras.add(t);
		
		
		
		
		Figura interfaceFigura = ()->{
			
		};
		
		figuras.add(interfaceFigura);
		
		
		
		
		//d -> Cuadrado
		//d -> Figura
		//d -> Object
		
		//f -> Circulo, Figura, Object
		
		
		//app -> PApplet
		//app -> Main
		//app -> Object
		
		//this -> Logica
		//this -> Object
		
	}

}
