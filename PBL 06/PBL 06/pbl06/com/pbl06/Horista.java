package com.pbl06;

public class Horista extends Temporario {
	
	private int horas_trabalhadas;
	private double valor_da_hora;

	public Horista(String nome, String funcao, int tempo_meses, int horas_trabalhadas, double valor_da_hora) {
		super(nome, funcao, tempo_meses);
		this.horas_trabalhadas = horas_trabalhadas;
		this.valor_da_hora = valor_da_hora;
	}

	@Override
	public String gerar_contra_cheque() {
		String contra_cheque = "";
		
		contra_cheque += INITIAL_CONTRA_CHEQUE_MSG + " " + nome + "\n";
		contra_cheque += "função: " + funcao + "\n";
		contra_cheque += "tempo em meses: " + tempo_meses + "\n";
		contra_cheque += "horas trabalhadas: " + horas_trabalhadas + "\n";
		contra_cheque += "valor da hora: " + valor_da_hora + "\n";
		contra_cheque += VALOR_RECEBER_MSG + ": " + (valor_da_hora * horas_trabalhadas);
		
		return contra_cheque;
	}

}
