package com.main;

import java.util.ArrayList;

public class Pais {

	private String nome;
	private ArrayList<Estado> estados;
	
	public Pais(String nome) {
		this.nome = nome;
		this.estados = new ArrayList<Estado>();
	}
	
	public Pais(String nome, ArrayList<Estado> estados) {
		this.nome = nome;
		this.estados = estados;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void addEstado(Estado estado) {
		estados.add(estado);
	}
	
	public Estado getEstadoByName(String nome) {
		for (Estado e : estados) {
			if (e.getNome() == nome) {
				return e;
			}
		}
		return null;
	}
	
	public int populacao() {
		int total_pops = 0;
		for (Estado e : estados) {
			total_pops += e.populacao();
		}
		
		return total_pops;
	}
	
	public double area() {
		int total_area = 0;
		for (Estado e : estados) {
			total_area += e.area();
		}
		
		return total_area;
	}
	
	public double densidade() {
		return populacao() / area();
	}
	
}
