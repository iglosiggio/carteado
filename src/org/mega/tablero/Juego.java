package org.mega.tablero;

import java.util.List;

import net.engio.mbassy.bus.BusFactory;
import net.engio.mbassy.bus.SyncMessageBus;
import net.engio.mbassy.bus.common.ISyncMessageBus;

import org.mega.tablero.cartas.Item;
import org.mega.tablero.eventos.Evento;

public final class Juego {
    @SuppressWarnings("unchecked")
    private static ISyncMessageBus<Evento, SyncMessageBus<Evento>.SyncPostCommand> eventBus = BusFactory.SynchronousOnly();
    private static Tablero tablero;
    
    public static void empezarJuego(List<Item> mazo1, List<Item> mazo2) {
	tablero = new Tablero(mazo1, mazo2);
    }

    public static boolean publicarEvento(Evento ev) {
	return publicarEvento(ev, null);
    }

    public static boolean publicarEvento(Evento ev, Runnable callback) {
	eventBus.publish(ev);
	if (ev.esValido()) {
	    if (callback != null)
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
