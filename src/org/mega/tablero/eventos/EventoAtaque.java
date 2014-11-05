package org.mega.tablero.eventos;

import org.mega.tablero.Tablero;
import org.mega.tablero.cartas.Guerrero;

public class EventoAtaque extends Evento {
	
	private Guerrero atacante;
	private Guerrero recipiente;
	
	public EventoAtaque(TipoEvento tipo, Tablero tablero, Guerrero atacante, Guerrero recipiente) {
		super(tipo, tablero);
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
