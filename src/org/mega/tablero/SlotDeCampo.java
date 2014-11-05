package org.mega.tablero;

import org.mega.tablero.cartas.Guerrero;
import org.mega.tablero.cartas.Pergamino;
import org.mega.tablero.cartas.Sorpresa;

public class SlotDeCampo {
	Guerrero guerrero;
	Pergamino pergamino;
	Sorpresa sorpresa;
	
	public SlotDeCampo(Guerrero guerrero, Pergamino pergamino, Sorpresa sorpresa) {
		this.guerrero = guerrero;
		this.pergamino = pergamino;
		this.sorpresa = sorpresa;
	}
	
	public Guerrero getGuerrero() {
		return guerrero;
	}
	
	public Pergamino getPergamino() {
		return pergamino;
	}
	
	public Sorpresa getSorpresa() {
		return sorpresa;
	}
	
	public void setGuerrero(Guerrero guerrero) {
		this.guerrero = guerrero;
	}
	
	public void setPergamino(Pergamino pergamino) {
		this.pergamino = pergamino;
	}
	
	public void setSorpresa(Sorpresa sorpresa) {
		this.sorpresa = sorpresa;
	}
	
	static public SlotDeCampo getSlotBase() {
		return new SlotDeCampo(Guerrero.getCartaBase(), Pergamino.getCartaBase(), Sorpresa.getCartaBase()); 
	}
}
