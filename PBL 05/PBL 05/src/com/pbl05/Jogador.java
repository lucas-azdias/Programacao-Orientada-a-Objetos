package com.pbl05;

public abstract class Jogador {

	private String nome;
	protected int score;
	
	public Jogador(String nome, int score) {
		this.nome = nome;
		this.score = score;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void imprimir() {
		System.out.println("Nome: " + nome);
		System.out.println("Score: " + score);
	}
	
	public abstract void ganhar(int p);
	public abstract void perder(int p);
	
}
