package org.mega.tablero.cartas;

public interface CartaEnJuego {

	public abstract int getNivel();

	public abstract String getNombre();

	public abstract String getDescripcion();

	public abstract void setNivel(int nivel);

	public abstract void setNombre(String nombre);

	public abstract void setDescripcion(String descripcion);

}