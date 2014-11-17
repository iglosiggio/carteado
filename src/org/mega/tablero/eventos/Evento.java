package org.mega.tablero.eventos;

public class Evento {
	public enum TipoEvento {PRE, POST}
	TipoEvento tipo;
	boolean esValido;
	
	public Evento() {
		this.tipo = TipoEvento.PRE;
		this.esValido = true;
	}
	
	public Evento(TipoEvento tipo) {
		this.tipo = tipo;
		this.esValido = true;
	}
	
	public TipoEvento getTipo() {
		return tipo;
	}
	
	public boolean esValido() {
		return esValido;
	}
	
	public void invalidarEvento() {
		esValido = false;
	}
	
	public void hacerPost() {
		this.tipo = TipoEvento.POST;
	}
}
