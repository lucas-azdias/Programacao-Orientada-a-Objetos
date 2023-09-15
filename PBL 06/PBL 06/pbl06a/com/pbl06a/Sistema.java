package com.pbl06a;

import java.util.Calendar;
import java.util.Random;
import java.util.random.RandomGenerator;

import Transportadora.Automovel;
import Transportadora.Caminhao;
import Transportadora.Combustivel;
import Transportadora.Empresa;
import Transportadora.Furgao;
import Transportadora.Motocicleta;
import Transportadora.StatusVeiculo;

public class Sistema {

	public static void main(String[] args) {
		
		Empresa empresa = new Empresa("Empresa Genérica SA");
		
		
		// Inserção e exclusão
		empresa.inserirVeiculo(new Caminhao("AAA0A00", "Cursor 450e33 2012", "Iveco", 150000, 56.9));
		empresa.inserirVeiculo(new Furgao("BBB9B99", "Fiorino 2013", "Fiat", 250879, 34.7));
		empresa.inserirVeiculo(new Automovel("CCC8C88", "Mobi 2019", "Fiat", 129450, 56.1));
		empresa.inserirVeiculo(new Automovel("ZZZ1Z11", "Mobi 2020", "Fiat", 100782, 10.4));
		empresa.inserirVeiculo(new Motocicleta("DDD7D77", "Biz 2016", "Honda", 199730, 4.3));
		
		empresa.removerVeiculo("ZZZ1Z11");

		empresa.printFrota();

		
		// Fazer abastecimento
		Calendar data1 = Calendar.getInstance();
		data1.set(2023, 04, 20);
		System.out.println(empresa.abastecerVeiculo("AAA0A00", Combustivel.GASOLINA, 13, 6.1, data1));

		Calendar data2 = Calendar.getInstance();
		data2.set(2023, 04, 21);
		System.out.println(empresa.abastecerVeiculo("AAA0A00", Combustivel.DIESEL, 14, 5.7, data2));
		System.out.println(empresa.abastecerVeiculo("0", Combustivel.DIESEL, 14, 5.7, data2));
		
		System.out.println();
		
		
		// Valor pago em um período
		Random rand = Random.from(RandomGenerator.getDefault());
		double valor_pago = 0;
		for (int i = 0; i < 10; i++) {
			int day = rand.nextInt(1, 31);
			double litros = rand.nextInt(100, 150) / 10;
			double preco = rand.nextInt(500, 600) / 100;
			Calendar data = Calendar.getInstance();
			data.set(2023, 04, day);
			System.out.println(i + ": " + empresa.abastecerVeiculo("CCC8C88", Combustivel.GASOLINA, litros, preco, data));
			if (day >= 10 && day <= 20) {
				valor_pago += litros * preco;
			}
		}
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.set(2023, 04, 10);
		Calendar dataFim = Calendar.getInstance();
		dataFim.set(2023, 04, 20);
		System.out.println(empresa.getValorPagoPeriod(dataInicio, dataFim) + " == " + valor_pago + "\n");
		
		
		// Alterar status do veículo
		empresa.printVeiculo("BBB9B99");
		empresa.setStatusVeiculo("BBB9B99", StatusVeiculo.IS_BEING_USE);
		empresa.printVeiculo("BBB9B99");
		
		
		// Listar veículos com rendimento abaixo da média
		System.out.println(empresa.registrarJornadaKmVeiculo("BBB9B99", 1, 5));
		System.out.println(empresa.registrarJornadaKmVeiculo("BBB9B99", 2, 6));
		System.out.println(empresa.registrarJornadaKmVeiculo("BBB9B99", 5, 10));
		System.out.println();
		empresa.listVeiculosLowRendimento();
		
		
		// Listar veículos com rendimento abaixo da média
		int toRoad = 10001;
		for (int i = 0; i < (toRoad / Caminhao.MAX_KM_JORNADA) + 1; i++) {
			System.out.println(i + ": " + empresa.registrarJornadaKmVeiculo("AAA0A00", Caminhao.MAX_KM_JORNADA, 1));
		}
		System.out.println();
		empresa.listVeiculosRevisao();
		
	}

}
