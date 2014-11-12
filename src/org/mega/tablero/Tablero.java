package org.mega.tablero;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import net.engio.mbassy.bus.BusFactory;
import net.engio.mbassy.bus.SyncMessageBus;
import net.engio.mbassy.bus.common.ISyncMessageBus;

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
	@SuppressWarnings("unchecked")
	private ISyncMessageBus<Evento, SyncMessageBus<Evento>.SyncPostCommand> eventBus = BusFactory.SynchronousOnly();

	public class Jugador {
		boolean atacóEnEsteTurno = false;
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
		
		private void sacarCarta() {
			// TODO: Revolear eventos como un campeón
			mano.add(mazo.poll());
		}
		
		public void ponerCarta(int posicionMano) {
			if(!esTurnoDe(this)) {
				throw new RuntimeException("No es tu turno maquinola");
			}
			else {
				_ponerCarta(posicionMano);
			}
		}
		
		private void _ponerCarta(int posicionMano) {
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
			mano.remove(posicionMano);
		}
		
		public void atacar() {
			if(esTurnoDe(this)) {
				throw new RuntimeException("No es tu turno maquinola");
			}
			else if(atacóEnEsteTurno) {
				throw new RuntimeException("Ya atacaste lince");
			}
			else {
				atacóEnEsteTurno = true;
				_atacar();
			}
		}
		
		private void _atacar() {
			// TODO: Revolear eventos como un campeón
			SlotDeCampo slot = campo.peek();
			Guerrero guerrero = slot.getGuerrero();
			Jugador enemigo = Tablero.this.getJugadorEnemigo();
			Guerrero guerreroEnemigo = enemigo.getGuerreroActual();
			
			// Nuestro guerrero ataca al de ellos
			{
				EventoAtaque pre_evento = new EventoAtaque(Evento.TipoEvento.PRE, Tablero.this, guerrero, guerreroEnemigo);
				eventBus.post(pre_evento);
				if(pre_evento.esValido()) {
					guerreroEnemigo.atacadoPor(guerrero);
					EventoAtaque post_evento = new EventoAtaque(Evento.TipoEvento.POST, Tablero.this, guerrero, guerreroEnemigo);
					eventBus.post(post_evento);
				}
			}
			// Su guerrero nos ataca
			{
				EventoAtaque pre_evento = new EventoAtaque(Evento.TipoEvento.PRE, Tablero.this, guerreroEnemigo, guerrero);
				eventBus.post(pre_evento);
				if(pre_evento.esValido()) {
					guerrero.atacadoPor(guerreroEnemigo);
					EventoAtaque post_evento = new EventoAtaque(Evento.TipoEvento.POST, Tablero.this, guerreroEnemigo, guerrero);
					eventBus.post(post_evento);
				}
			}
		}
		
		private Guerrero getGuerreroActual() {
			return campo.peek().getGuerrero();
		}
		
		public Collection<SlotDeCampo> getCampo() {
			return Collections.unmodifiableCollection(campo);
		}
		
		public void pasarTurno() {
			cambiarTurno();
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
		turno = !turno;
		//eventBus.publish("Cambio de turno, ahora: " + Boolean.toString(turno));
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