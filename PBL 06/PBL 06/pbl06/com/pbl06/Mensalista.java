package com.pbl06;

public class Mensalista extends Temporario {
	
	private double salario_mensal;

	public Mensalista(String nome, String funcao, int tempo_meses, double salario_mensal) {
		super(nome, funcao, tempo_meses);
		this.salario_mensal = salario_mensal;
	}

	@Override
	public String gerar_contra_cheque() {
		String contra_cheque = "";
		
		contra_cheque += INITIAL_CONTRA_CHEQUE_MSG + " " + nome + "\n";
		contra_cheque += "função: " + funcao + "\n";
		contra_cheque += "tempo em meses: " + tempo_meses + "\n";
		contra_cheque += "salário mensal: " + salario_mensal + "\n";
		contra_cheque += VALOR_RECEBER_MSG + ": " + (salario_mensal * tempo_meses);
		
		return contra_cheque;
	}

}
