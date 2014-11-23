package org.mega.tablero.cartas;

import org.mega.tablero.eventos.Evento;

public abstract class Sorpresa {
	int nivel;
	String nombre;
	String descripcion;
	
	public Sorpresa(int nivel, String nombre, String descripcion) {
		this.nivel = nivel;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public abstract boolean recibirEvento(Evento evento);
	
	public static Sorpresa getCartaBase() {
		return new Sorpresa(0, "Sorpresa nula", "") {
			@Override
			public boolean recibirEvento(Evento evento) {
				return false;
			}
		};
	}
}
