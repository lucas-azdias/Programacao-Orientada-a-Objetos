package com.main;

public class Municipio {

	private String nome;
	private int populacao;
	private double area;
	
	public Municipio(String nome, int populacao, double area) {
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getPopulacao() {
		return populacao;
	}
	
	public double getArea() {
		return area;
	}
	
	public double densidade() {
		return populacao / area;
	}
	
}
