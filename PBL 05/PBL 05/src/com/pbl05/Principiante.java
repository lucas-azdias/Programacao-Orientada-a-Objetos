package com.pbl05;

public class Principiante extends Jogador {
	
	private double bonus;
	
	public Principiante(String nome, int score, double bonus) {
		super(nome, score);
		this.bonus = bonus;
	}
	
	public void imprimir() {
		super.imprimir();
		System.out.println("Bônus: " + bonus);
	}
	
	public void ganhar(int p) {
		score += p;
		bonus += 0.1 * p;
	}
	
	public void perder(int p) {
		score -= p;
		bonus -= 0.1 * p;
	}
 
}
