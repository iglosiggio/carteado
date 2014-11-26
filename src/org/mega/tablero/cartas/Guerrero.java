package org.mega.tablero.cartas;

import org.mega.tablero.Juego;
import org.mega.tablero.eventos.PropiedadChanged;

public class Guerrero implements Carta {
	int nivel;
	String nombre;
	String descripcion;
	int ataque;
	int vida;
	int dañoRecibido;

	public Guerrero(int nivel, String nombre, String descripcion, int ataque,
			int vida, int dañoRecibido) {
		this.nivel = nivel;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ataque = ataque;
		this.vida = vida;
		this.dañoRecibido = dañoRecibido;
	}

	@Override
	public int getNivel() {
		return nivel;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	public int getAtaque() {
		return ataque;
	}

	public int getVida() {
		return vida;
	}

	public int getDañoRecibido() {
		return dañoRecibido;
	}

	@Override
	public void setNivel(final int nivel) {
		Juego.publicarEvento(new PropiedadChanged<Integer, Guerrero>(this,
				"nivel", this.nivel, nivel, Integer.class), new Runnable() {
			public void run() {
				Guerrero.this.nivel = nivel;
			}
		});
	}

	@Override
	public void setNombre(final String nombre) {
		Juego.publicarEvento(new PropiedadChanged<String, Guerrero>(this,
				"nombre", this.nombre, nombre, String.class), new Runnable() {
			public void run() {
				Guerrero.this.nombre = nombre;
			}
		});
	}

	@Override
	public void setDescripcion(final String descripcion) {
		Juego.publicarEvento(new PropiedadChanged<String, Guerrero>(this,
				"descripcion", this.descripcion, descripcion, String.class),
				new Runnable() {
					public void run() {
						Guerrero.this.descripcion = descripcion;
					}
				});
	}

	public void setAtaque(final int ataque) {
		Juego.publicarEvento(new PropiedadChanged<Integer, Guerrero>(this,
				"ataque", this.ataque, ataque, Integer.class), new Runnable() {
			public void run() {
				Guerrero.this.ataque = ataque;
			}
		});
	}

	public void setVida(final int vida) {
		Juego.publicarEvento(new PropiedadChanged<Integer, Guerrero>(this,
				"vida", this.vida, vida, Integer.class), new Runnable() {
			public void run() {
				Guerrero.this.vida = vida;
			}
		});
	}

	public void setDañoRecibido(final int dañoRecibido) {
		Juego.publicarEvento(
				new PropiedadChanged<Integer, Guerrero>(this, "dañoRecibido",
						this.dañoRecibido, dañoRecibido, Integer.class),
				new Runnable() {
					public void run() {
						Guerrero.this.dañoRecibido = dañoRecibido;
					}
				});
	}

	private void addDañoRecibido(final int dañoRecibido) {
		Juego.publicarEvento(new PropiedadChanged<Integer, Guerrero>(this,
				"dañoRecibido", this.dañoRecibido, this.dañoRecibido
						+ dañoRecibido, Integer.class), new Runnable() {
			public void run() {
				Guerrero.this.dañoRecibido += dañoRecibido;
			}
		});
	}

	public boolean atacadoPor(Guerrero guerrero) {
		addDañoRecibido(guerrero.ataque);
		return dañoRecibido >= vida;
	}

	public static Guerrero getCartaBase() {
		return new Guerrero(0, "Peón", "Un simple peón", 0, 10, 0);
	}
}
