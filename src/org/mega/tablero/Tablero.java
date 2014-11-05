package org.mega.tablero;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import org.mega.tablero.cartas.Guerrero;
import org.mega.tablero.cartas.Item;
import org.mega.tablero.cartas.Pergamino;
import org.mega.tablero.cartas.Sorpresa;
import org.mega.tablero.eventos.Evento;
import org.mega.tablero.eventos.EventoAtaque;

public class Tablero {
	private boolean turno;
	private Jugador J1;
	private Jugador J2;

	public class Jugador {
		Deque<Item> mazo;
		Deque<SlotDeCampo> campo;
		List<Item> mano;
		public Jugador(List<Item> mazo, Deque<SlotDeCampo> campo) {
			// TODO: Revolear eventos como un campeón
			Collections.shuffle(mazo);
			this.mazo = new LinkedList<Item>(mazo);
			this.campo = campo;
			this.mano = new ArrayList<Item>();
		}
		
		public void sacarCarta() {
			// TODO: Revolear eventos como un campeón
			mano.add(mazo.poll());
		}
		
		public void ponerCarta(int posicionMano) {
			Item carta = mano.get(posicionMano);
			SlotDeCampo slot = campo.peek();
			if(carta.checkSlot(slot)) {
				// TODO: Revolear eventos como un campeón
				SlotDeCampo nuevoSlot = carta.generarSlot(slot);
				if(nuevoSlot.getGuerrero() != null) {
					slot.setGuerrero(nuevoSlot.getGuerrero());
				}
				if(nuevoSlot.getPergamino() != null) {
					slot.setPergamino(nuevoSlot.getPergamino());
				}
				if(nuevoSlot.getSorpresa() != null) {
					slot.setSorpresa(nuevoSlot.getSorpresa());
				}
			}
		}
		
		public void atacar() {
			// TODO: Revolear eventos como un campeón
			SlotDeCampo slot = campo.peek();
			Guerrero guerrero = slot.getGuerrero();
			Jugador enemigo = Tablero.this.getJugadorEnemigo();
			Guerrero guerreroEnemigo = enemigo.getGuerreroActual();
			
			// TODO: Tener los [PRE/POST]
			if(despacharEvento(new EventoAtaque(Evento.TipoEvento.PRE, Tablero.this, guerrero, guerreroEnemigo))) {
				guerreroEnemigo.atacadoPor(guerrero);
				despacharEvento(new EventoAtaque(Evento.TipoEvento.POST, Tablero.this, guerrero, guerreroEnemigo));
			}
			if(despacharEvento(new EventoAtaque(Evento.TipoEvento.PRE, Tablero.this, guerrero, guerreroEnemigo))) {
				guerrero.atacadoPor(guerreroEnemigo);
				despacharEvento(new EventoAtaque(Evento.TipoEvento.POST, Tablero.this, guerrero, guerreroEnemigo));
			}
		}
		
		public Guerrero getGuerreroActual() {
			return campo.peek().getGuerrero();
		}
		
		public Collection<SlotDeCampo> getCampo() {
			return Collections.unmodifiableCollection(campo);
		}
		
		public boolean despacharEvento(Evento evento) {
			for(SlotDeCampo slot : campo) {
				Sorpresa sorpresa = slot.getSorpresa();
				Pergamino pergamino = slot.getPergamino();
				if(sorpresa.recibirEvento(evento)) {
					if(pergamino.aplicarEfecto(evento)) {
						return false;
					}
				}
			}
			return true;
		}
	}
	
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
	
	private Jugador getJugadorEnemigo() {
		if(turno) {
			return J2;
		}
		else {
			return J1;
		}
	}
}