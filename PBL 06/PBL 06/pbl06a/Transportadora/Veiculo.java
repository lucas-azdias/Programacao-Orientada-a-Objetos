package Transportadora;

import java.util.ArrayList;
import java.util.Calendar;

public abstract class Veiculo {
	
	public static final int KM_REVISAO = 10000;
	
	private String placa;
	private String modelo;
	private String marca;
	private int lastRevisaoKm;
	private int lastAbastecimentoKm;
	private double lastAbastecimentoTanque;
	private StatusVeiculo status;
	private ArrayList<Abastecimento> abastecimentos;
	protected String tipo;
	protected int km;
	protected int initialJornadaKm;
	protected double tanque;
	protected Combustivel[] combustiveisPossiveis;
	
	public Veiculo(String placa, String modelo, String marca, int km, double tanque) {
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.km = km;
		this.tanque = tanque;
		lastRevisaoKm = km;
		lastAbastecimentoKm = km;
		lastAbastecimentoTanque = tanque;
		status = StatusVeiculo.IS_AVAILABLE;
		abastecimentos = new ArrayList<Abastecimento>();
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getKm() {
		return km;
	}
	
	public StatusVeiculo getStatus() {
		return status;
	}
	
	public double getRendimento() {
		return (lastAbastecimentoKm - km) / (lastAbastecimentoTanque - tanque);
	}
	
	public double getValorPagoPeriod(Calendar initialData, Calendar endData) {
		double valorPagoPeriod = 0;
		for (Abastecimento abastecimento: abastecimentos) {
			if (abastecimento.getData().compareTo(initialData) >= 0 && abastecimento.getData().compareTo(endData) <= 0) {
				valorPagoPeriod += abastecimento.getValorPago();
			}
		}
		return valorPagoPeriod;
	}
	
	public void setStatus(StatusVeiculo status) {
		this.status = status;
	}
	
	public boolean needsRevisao() {
		if ((km - lastRevisaoKm) >= KM_REVISAO) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean abastecer(Combustivel combustivel, double litros, double precoLitroCombustivel, Calendar data) {
		boolean isCombustivelFound = false;
		for (Combustivel combustivelPossivel : combustiveisPossiveis) {
			if (combustivelPossivel == combustivel) {
				isCombustivelFound = true;
			}
		}
		
		if (isCombustivelFound) {
			abastecimentos.add(new Abastecimento(this, combustivel, litros, precoLitroCombustivel, data));
			tanque += litros;
			lastAbastecimentoKm = km;
			lastAbastecimentoTanque = tanque;
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		String msg = "Veículo (" + tipo.toUpperCase() + ")\n";
		msg += "Modelo: " + modelo + "\n";
		msg += "Marca: " + marca + "\n";
		msg += "KM atual: " + km + "\n";
		msg += "KM da última revisão: " + lastRevisaoKm + "\n";
		msg += "KM da última revisão: " + lastRevisaoKm + "\n";
		msg += "KM do último abastecimento: " + lastAbastecimentoKm + "\n";
		msg += "Tanque: " + tanque + "L\n";
		msg += "Tanque no último abastecimento: " + lastAbastecimentoTanque + "L\n";
		msg += "Status do veicúlo: " + status.toString() + "\n";
		return msg;
	}
	
	public void restartJornada() {
		initialJornadaKm = 0;
	}
	
	public abstract double compareRendimento();
	
	public abstract boolean registrarJornadaKm(int jornadaKm, double jornadaLitrosGastos);
	
}
