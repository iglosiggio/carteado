package org.mega.tablero.cartas;

import org.mega.tablero.eventos.Evento;

public abstract class Pergamino {
	int nivel;
	String nombre;
	String descripcion;
	
	public Pergamino(int nivel, String nombre, String descripcion) {
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

	public abstract boolean aplicarEfecto(Evento evento);
	
	public static Pergamino getCartaBase() {
		return new Pergamino(0, "Pergamino nulo", "") {
			@Override
			public boolean aplicarEfecto(Evento evento) {
				return false;
			}
		};
	}
}
