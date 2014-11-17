package org.mega.tablero;

import java.util.List;
import java.util.Deque;
import java.util.LinkedList;


import org.mega.tablero.cartas.Item;
import org.mega.tablero.eventos.EventoTurno;

public class Tablero {
	private boolean turno;
	private Jugador J1;
	private Jugador J2;
	public Tablero(List<Item> mazo1, List<Item> mazo2) {
		turno = true;
		
		Deque<SlotDeCampo> campo1 = new LinkedList<>();
		campo1.add(SlotDeCampo.getSlotBase());
		campo1.add(SlotDeCampo.getSlotBase());
		campo1.add(SlotDeCampo.getSlotBase());
		campo1.add(SlotDeCampo.getSlotBase());
		
		J1 = new Jugador(mazo1, campo1);
		J1.sacarCarta();
		J1.sacarCarta();
		J1.sacarCarta();
		J1.sacarCarta();
		
		Deque<SlotDeCampo> campo2 = new LinkedList<>();
		campo2.add(SlotDeCampo.getSlotBase());
		campo2.add(SlotDeCampo.getSlotBase());
		campo2.add(SlotDeCampo.getSlotBase());
		campo2.add(SlotDeCampo.getSlotBase());
		
		J2 = new Jugador(mazo2, campo2);
		J2.sacarCarta();
		J2.sacarCarta();
		J2.sacarCarta();
		J2.sacarCarta();
	}
	
	public Jugador getJugadorEnemigo() {
		if(turno) {
			return J2;
		}
		else {
			return J1;
		}
	}
	
	public Jugador getJugadorActual() {
		if(turno) {
			return J1;
		}
		else {
			return J2;
		}
	}
	
	public void cambiarTurno() {
		Juego.publicarEvento(new EventoTurno(), new Runnable() {
			@Override
			public void run() {
				turno = !turno;
			}
		});
	}
	
	public boolean esTurnoDe(Jugador jugador) {
		if(jugador == J1 && turno) {
			return true;
		} else if(jugador == J2 && !turno) {
			return true;
		} else {
			return false;
		}
	}
}