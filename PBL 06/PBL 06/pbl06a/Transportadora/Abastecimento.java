package Transportadora;

import java.util.Calendar;

public class Abastecimento {
	
	private int kmRegistered;
	private double litros;
	private double precoLitroCombustivel;
	private Combustivel combustivel;
	private Veiculo veiculo;
	private Calendar data;
	
	public Abastecimento(Veiculo veiculo, Combustivel combustivel, double litros, double precoLitroCombustivel, Calendar data) {
		this.veiculo = veiculo;
		this.kmRegistered = veiculo.getKm();
		this.combustivel = combustivel;
		this.litros = litros;
		this.precoLitroCombustivel = precoLitroCombustivel;
		this.data = data;
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public int getKmRegistered() {
		return kmRegistered;
	}
	
	public double getLitros() {
		return litros;
	}
	
	public double getPrecoLitroCombustivel() {
		return precoLitroCombustivel;
	}
	
	public Combustivel getCombustivel() {
		return combustivel;
	}
	
	public Calendar getData() {
		return data;
	}

	public double getValorPago() {
		return precoLitroCombustivel * litros;
	}

}
