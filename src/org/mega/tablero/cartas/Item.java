package org.mega.tablero.cartas;

import org.mega.tablero.SlotDeCampo;

public abstract class Item {
	String nombre;
	String descripcion;
	
	public abstract boolean checkSlot(SlotDeCampo slot);
	public abstract SlotDeCampo generarSlot(SlotDeCampo slot);
}
