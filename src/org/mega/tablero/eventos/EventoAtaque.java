package org.mega.tablero.eventos;

import org.mega.tablero.cartas.CartaEnJuego;

public class EventoAtaque extends Evento {
	
	private CartaEnJuego atacante;
	private CartaEnJuego recipiente;
	
	public EventoAtaque(CartaEnJuego atacante, CartaEnJuego recipiente) {
		this.atacante = atacante;
		this.recipiente = recipiente;
	}
	
	public EventoAtaque(TipoEvento tipo, CartaEnJuego atacante, CartaEnJuego recipiente) {
		super(tipo);
		this.atacante = atacante;
		this.recipiente = recipiente;
	}

	public CartaEnJuego getAtacante() {
		return atacante;
	}
	
	public CartaEnJuego getRecipiente() {
		return recipiente;
	}
}
