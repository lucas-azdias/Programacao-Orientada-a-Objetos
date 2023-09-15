package com.pbl06;

import java.util.ArrayList;

public class Empresa {
	
	private static final String CONTRA_CHEQUE_SEPARATOR = "==================================================";
	
	private String nome;
	private ArrayList<Funcionario> funcionarios;
	
	public Empresa(String nome) {
		this.nome = nome;
		funcionarios = new ArrayList<Funcionario>();
	}
	
	public String getNome() {
		return nome;
	}

	public void incluir(Funcionario funcionario) {
		funcionarios.add(funcionario);
	}
	
	public void gerar_folha() {
		String folha = "";
		for (Funcionario funcionario : funcionarios) {
			folha += funcionario.gerar_contra_cheque();
			folha += "\n" + CONTRA_CHEQUE_SEPARATOR + "\n";
		}
		System.out.println(folha);
	}

}
