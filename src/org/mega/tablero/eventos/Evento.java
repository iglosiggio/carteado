package org.mega.tablero.eventos;

import org.mega.tablero.Tablero;

public class Evento {
	public enum TipoEvento {PRE, POST}
	TipoEvento tipo;
	Tablero tablero;
	boolean esValido;
	
	public Evento(TipoEvento tipo, Tablero tablero) {
		this.tipo = tipo;
		this.tablero = tablero;
		this.esValido = true;
	}
	
	public TipoEvento getTipo() {
		return tipo;
	}
	
	public Tablero getTablero() {
		return tablero;
	}
	
	public boolean esValido() {
		return esValido;
	}
	
	public void invalidarEvento() {
		esValido = false;
	}
}
