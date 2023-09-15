package com.pbl06;

public class Comissionado extends Concursado {

	private String cargo;
	private double comissao;

	public Comissionado(String nome, String funcao, int ano_ingresso, double salario_mensal, double comissao, String cargo) {
		super(nome, funcao, ano_ingresso, salario_mensal);
		this.cargo = cargo;
		this.comissao = comissao;
	}
	
	@Override
	public String gerar_contra_cheque() {
		String[] super_contra_cheque = super.gerar_contra_cheque().split("\n");
		String[] super_contra_cheque_noend = new String[super_contra_cheque.length - 1];
		for (int i = 0; i < super_contra_cheque.length - 1; i++) {
			super_contra_cheque_noend[i] = super_contra_cheque[i];
		}
		String contra_cheque = String.join("\n", super_contra_cheque_noend) + "\n";
		
		contra_cheque += "cargo: " + cargo + "\n";
		contra_cheque += "comissão: " + comissao + "\n";
		contra_cheque += VALOR_RECEBER_MSG + ": " + (salario_mensal + comissao);
		
		return contra_cheque;
	}

}
