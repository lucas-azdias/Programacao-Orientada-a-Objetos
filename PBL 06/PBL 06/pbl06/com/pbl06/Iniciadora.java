package com.pbl06;

public class Iniciadora {

	public static void main(String[] args) {
		Empresa petrobras = new Empresa("Petrobras");
		
		petrobras.incluir(new Concursado("Joao Silva", "Engenheiro Químico", 1998, 8000.00));
		petrobras.incluir(new Comissionado("Carmem Luz", "Engenheiro Civil", 2007, 20000.00, 6000.00, "Diretor Geral"));
		petrobras.incluir(new Horista("Manoel de Barros", "Auxiliar de Limpeza", 12, 500, 40.00));
		petrobras.incluir(new Mensalista("Tereza Alves", "Secretaria", 24, 2500.00));
		
		petrobras.gerar_folha();
	}

}
