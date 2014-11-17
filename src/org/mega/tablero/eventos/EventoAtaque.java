package org.mega.tablero.eventos;

import org.mega.tablero.cartas.Guerrero;

public class EventoAtaque extends Evento {
	
	private Guerrero atacante;
	private Guerrero recipiente;
	
	public EventoAtaque(Guerrero atacante, Guerrero recipiente) {
		this.atacante = atacante;
		this.recipiente = recipiente;
	}
	
	public EventoAtaque(TipoEvento tipo, Guerrero atacante, Guerrero recipiente) {
		super(tipo);
		this.atacante = atacante;
		this.recipiente = recipiente;
	}

	public Guerrero getAtacante() {
		return atacante;
	}
	
	public Guerrero getRecipiente() {
		return recipiente;
	}
}
