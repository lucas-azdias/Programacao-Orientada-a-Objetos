package com.pbl05;

public class Senior extends Profissional {
	
	private double premio;
	
	public Senior(String nome, int score, double capital, double premio) {
		super(nome, score, capital);
		this.premio = premio;
	}
	
	public void imprimir() {
		super.imprimir();
		System.out.println("Prêmio: " + premio);
	}
	
	public void ganhar(int p) {
		super.ganhar(p);
		premio *= 2;
	}
	
	public void perder(int p) {
		super.perder(p);
		premio /= 2;
	}

}
