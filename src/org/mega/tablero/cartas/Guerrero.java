package org.mega.tablero.cartas;

public class Guerrero {
	int nivel;
	String nombre;
	String descripcion;
	int ataque;
	int vida;
	int dañoRecibido;
	
	public Guerrero(int nivel, String nombre, String descripcion, int ataque, int vida, int dañoRecibido) {
		this.nivel = nivel;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ataque = ataque;
		this.vida = vida;
		this.dañoRecibido = dañoRecibido;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getAtaque() {
		return ataque;
	}
	
	public int getVida() {
		return vida;
	}
	
	public int getDañoRecibido() {
		return dañoRecibido;
	}
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	
	public void setVida(int vida) {
		this.vida = vida;
	}
	
	public void setDañoRecibido(int dañoRecibido) {
		this.dañoRecibido = dañoRecibido;
	}
	
	public boolean atacadoPor(Guerrero guerrero) {
		this.dañoRecibido += guerrero.ataque;
		return dañoRecibido >= vida;
	}
	
	public static Guerrero getCartaBase() {
		return new Guerrero(0, "Peón", "Un simple peón", 0, 10, 0);
	}
}
