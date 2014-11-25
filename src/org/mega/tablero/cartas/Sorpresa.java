package org.mega.tablero.cartas;

import org.mega.tablero.Juego;
import org.mega.tablero.eventos.Evento;

public abstract class Sorpresa implements CartaEnJuego {
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

    public void setNivel(final int nivel) {
	Juego.publicarEvento(new PropiedadChanged<Integer, Sorpresa>(this,
		"nivel", this.nivel, nivel, Integer.class), new Runnable() {
	    public void run() {
		Sorpresa.this.nivel = nivel;
	    }
	});
    }

    @Override
    public void setDescripcion(final String descripcion) {
	Juego.publicarEvento(new PropiedadChanged<String, Sorpresa>(this,
		"descripcion", this.descripcion, descripcion, String.class),
		new Runnable() {
		    public void run() {
			Sorpresa.this.descripcion = descripcion;
		    }
		});
    }

    public void setNombre(final String nombre) {
	Juego.publicarEvento(new PropiedadChanged<Object, Sorpresa>(this,
		"nombre", this.nombre, nombre, Object.class), new Runnable() {
	    public void run() {
		Sorpresa.this.nombre = nombre;
	    }
	});
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
