package com.qual.episodio.nerdcast.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Nerdcast implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private ArrayList<Fala> falas;
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public ArrayList<Fala> getFalas() {
		return falas;
	}
	
	public void setFalas(ArrayList<Fala> falas) {
		this.falas = falas;
	}
	
	public String getTituloFormatado() {
		String formatado = null;
		if(getTitulo() != null) {
			String[] titulos = getTitulo().split("-");
			if(titulos.length > 0) {
				formatado = titulos[0].trim();
			}
		}
		
		return formatado;
	}
}
