package org.mega.tablero;

import java.util.List;

import net.engio.mbassy.bus.BusFactory;
import net.engio.mbassy.bus.SyncMessageBus;
import net.engio.mbassy.bus.common.ISyncMessageBus;

import org.mega.tablero.cartas.Item;
import org.mega.tablero.eventos.Evento;

public final class Juego {
	private static Tablero tablero;
	private static ISyncMessageBus<Evento, SyncMessageBus<Evento>.SyncPostCommand> eventBus;
	
	@SuppressWarnings("unchecked")
	public static void empezarJuego(List<Item> mazo1, List<Item> mazo2) {
		tablero = new Tablero(mazo1, mazo2);
		eventBus = BusFactory.SynchronousOnly();
	}
	
	public static boolean publicarEvento(Evento ev, Runnable callback) {
		eventBus.publish(ev);
			if(ev.esValido()) {
				callback.run();
			ev.hacerPost();
			eventBus.post(ev);
			return true;
		}
		return false;
	}
	
	public static Tablero getTablero() {
		return tablero;
	}
	
	public static ISyncMessageBus<Evento, SyncMessageBus<Evento>.SyncPostCommand> getEventBus() {
		return eventBus;
	}
}