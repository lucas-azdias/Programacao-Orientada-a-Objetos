package com.main;

import java.util.ArrayList;

public class Estado {

	private String nome;
	public ArrayList<Municipio> municipios;
	
	public Estado(String nome) {
		this.nome = nome;
		this.municipios = new ArrayList<Municipio>();
	}
	
	public Estado(String nome, ArrayList<Municipio> municipios) {
		this.nome = nome;
		this.municipios = municipios;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void addMunicipio(Municipio municipio) {
		municipios.add(municipio);
	}
	
	public Municipio getMunicipioByName(String nome) {
		for (Municipio m : municipios) {
			if (m.getNome() == nome) {
				return m;
			}
		}
		return null;
	}
	
	public int populacao() {
		int total_pops = 0;
		for (Municipio m : municipios) {
			total_pops += m.getPopulacao();
		}
		
		return total_pops;
	}
	
	public double area() {
		int total_area = 0;
		for (Municipio m : municipios) {
			total_area += m.getArea();
		}
		
		return total_area;
	}
	
	public double densidade() {
		return populacao() / area();
	}
	
}
