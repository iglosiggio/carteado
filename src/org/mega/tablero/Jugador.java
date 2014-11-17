package org.mega.tablero;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.mega.tablero.cartas.Guerrero;
import org.mega.tablero.cartas.Item;
import org.mega.tablero.eventos.EventoAtaque;

public class Jugador {
	boolean atacóEnEsteTurno = false;
	Deque<Item> mazo;
	Deque<SlotDeCampo> campo;
	List<Item> mano;
	
	public class JugadorControlador implements JugadorControlable {
		public void ponerCarta(int posicionMano) {
			Jugador.this.ponerCarta(posicionMano);
		}
		
		public void atacar() {
			Jugador.this.atacar();
		}
		
		public void pasarTurno() {
			Jugador.this.pasarTurno();
		}
		
		public Collection<SlotDeCampo> getCampo() {
			return Collections.unmodifiableCollection(Jugador.this.getCampo());
		}
		
		public List<Item> getMano() {
			return Collections.unmodifiableList(Jugador.this.getMano());
		}
	}
	
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
		if(!Juego.getTablero().esTurnoDe(this)) {
			throw new RuntimeException("No es tu turno maquinola");
		}
		else {
			_ponerCarta(posicionMano);
		}
	}
	
	public void _ponerCarta(int posicionMano) {
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
		if(!Juego.getTablero().esTurnoDe(this)) {
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
	
	public void _atacar() {
		// TODO: Revolear eventos como un campeón
		SlotDeCampo slot = campo.peek();
		final Jugador enemigo = Juego.getTablero().getJugadorEnemigo();
		final Guerrero guerrero = slot.getGuerrero();
		final Guerrero guerreroEnemigo = enemigo.getGuerreroActual();
		
		// Nuestro guerrero ataca al de ellos
		Juego.publicarEvento(new EventoAtaque(guerrero, guerreroEnemigo), new Runnable() {
			public void run() {
				guerreroEnemigo.atacadoPor(guerrero);
			}
		});
		// Su guerrero nos ataca
		Juego.publicarEvento(new EventoAtaque(guerreroEnemigo, guerrero), new Runnable() {
			public void run() {
				guerrero.atacadoPor(guerreroEnemigo);
			}
		});
	}
	
	public Guerrero getGuerreroActual() {
		return campo.peek().getGuerrero();
	}
	
	public Deque<SlotDeCampo> getCampo() {
		return campo;
	}

	public List<Item> getMano() {
		return mano;
	}
	
	public void pasarTurno() {
		if(!Juego.getTablero().esTurnoDe(this)) {
			throw new RuntimeException("No es tu turno maquinola");
		}
		atacóEnEsteTurno = false;
		Juego.getTablero().cambiarTurno();
	}
	
	public JugadorControlador getControlador() {
		return new JugadorControlador();
	}
}