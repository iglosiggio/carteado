package org.mega.tablero.eventos;


public class PropiedadChanged<T, O> extends Evento {
    /**
	 * 
	 */
    final O objeto;
    final String propiedad;
    final T oldValue;
    final T newValue;
    final Class<T> tipoDato;

    public PropiedadChanged(O objeto, String propiedad, T oldValue, T newValue,
	    Class<T> tipoDato) {
	super();
	this.objeto = objeto;
	this.propiedad = propiedad;
	this.oldValue = oldValue;
	this.newValue = newValue;
	this.tipoDato = tipoDato;
    }

    public String getPropiedad() {
	return propiedad;
    }

    public T getOldValue() {
	return oldValue;
    }

    public T getNewValue() {
	return newValue;
    }

    public Class<T> getTipoDato() {
	return tipoDato;
    }

    public O getSender() {
	return objeto;
    }
}