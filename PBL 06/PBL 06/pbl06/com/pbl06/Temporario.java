package com.pbl06;

public abstract class Temporario extends Funcionario {
	
	protected int tempo_meses;

	public Temporario(String nome, String funcao, int tempo_meses) {
		super(nome, funcao);
		this.tempo_meses = tempo_meses;
	}

}
