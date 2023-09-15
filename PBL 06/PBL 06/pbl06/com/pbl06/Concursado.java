package com.pbl06;

public class Concursado extends Funcionario {
	
	protected int ano_ingresso;
	protected double salario_mensal;

	public Concursado(String nome, String funcao, int ano_ingresso, double salario_mensal) {
		super(nome, funcao);
		this.ano_ingresso = ano_ingresso;
		this.salario_mensal = salario_mensal;
	}

	@Override
	public String gerar_contra_cheque() {
		String contra_cheque = "";
		
		contra_cheque += INITIAL_CONTRA_CHEQUE_MSG + " " + nome + "\n";
		contra_cheque += "função: " + funcao + "\n";
		contra_cheque += "ano de ingresso: " + ano_ingresso + "\n";
		contra_cheque += "salario mensal: " + salario_mensal + "\n";
		contra_cheque += VALOR_RECEBER_MSG + ": " + salario_mensal;
		
		return contra_cheque;
	}
	
}
