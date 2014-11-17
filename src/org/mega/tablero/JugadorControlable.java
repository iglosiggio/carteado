package org.mega.tablero;

import java.util.Collection;
import java.util.List;

import org.mega.tablero.cartas.Item;

public interface JugadorControlable {
	public void ponerCarta(int posicionMano);
	public void atacar();
	public void pasarTurno();
	public Collection<SlotDeCampo> getCampo();
	public List<Item> getMano();
}
