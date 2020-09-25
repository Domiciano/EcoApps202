package main;

public class Jugador {
	
	//CONSTANTES
	public static final int CONSTANTE = 10;
	
	
	
	//VARIABLES DE CLASE
	public static int numeroObjetos = 0;
	

	
	
	//METODOS DE CLASE
	public static Jugador newInstance(String nombre) {
		Jugador ejemplo = new Jugador(nombre);
		return ejemplo;
	}
	
	
	
	
	//OBJETOS
	
	//CONSTRUCTOR
	
	private Jugador(String nombre) {
		this.nombre = nombre;
		numeroObjetos ++;
	}
	
	
	//ATRIBUTOS
	private String nombre;
	
	
	
	//METODOS
	
	public void disparar() {
		System.out.println(nombre + " pateo");
	}
	
	public void correr() {
		System.out.println("Hola Eco");
		TCPSingleton tcp = TCPSingleton.getInstance();
	}

}
