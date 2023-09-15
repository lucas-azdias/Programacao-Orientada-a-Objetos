package com.pbl06;

public abstract class Funcionario {
	
	protected static final String INITIAL_CONTRA_CHEQUE_MSG = "contra-cheque de", VALOR_RECEBER_MSG = "valor a receber";
	
	protected String nome, funcao;
	
	public Funcionario(String nome, String funcao) {
		this.nome = nome;
		this.funcao = funcao;
	}
	
	public abstract String gerar_contra_cheque();

}
