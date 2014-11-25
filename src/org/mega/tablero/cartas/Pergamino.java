package org.mega.tablero.cartas;

import org.mega.tablero.Juego;
import org.mega.tablero.eventos.Evento;

public abstract class Pergamino implements CartaEnJuego {
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

	public void setNivel(final int nivel) {
		Juego.publicarEvento(new PropiedadChanged<Integer, Pergamino>(this,
				"nivel", this.nivel, nivel, Integer.class),
				new Runnable() {
					public void run() {
						Pergamino.this.nivel = nivel;
					}
				});
	}

	@Override
	public void setNombre(final String nombre) {
		Juego.publicarEvento(new PropiedadChanged<Object, Pergamino>(this,
				"this.nombre", this.nombre, nombre, Object.class),
				new Runnable() {
					public void run() {
						Pergamino.this.nombre = nombre;
					}
				});
	}

	@Override
	public void setDescripcion(final String descripcion) {
		Juego.publicarEvento(new PropiedadChanged<String, Pergamino>(this,
				"descripcion", this.descripcion, descripcion, String.class),
				new Runnable() {
					public void run() {
						Pergamino.this.descripcion = descripcion;
					}
				});

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
