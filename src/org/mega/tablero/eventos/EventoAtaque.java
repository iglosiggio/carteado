package org.mega.tablero.eventos;

import org.mega.tablero.cartas.Carta;

public class EventoAtaque extends Evento {
	
	private Carta atacante;
	private Carta recipiente;
	
	public EventoAtaque(Carta atacante, Carta recipiente) {
		this.atacante = atacante;
		this.recipiente = recipiente;
	}
	
	public EventoAtaque(TipoEvento tipo, Carta atacante, Carta recipiente) {
		super(tipo);
		this.atacante = atacante;
		this.recipiente = recipiente;
	}

	public Carta getAtacante() {
		return atacante;
	}
	
	public Carta getRecipiente() {
		return recipiente;
	}
}
