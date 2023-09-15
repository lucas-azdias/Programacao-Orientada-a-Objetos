package Transportadora;

import java.util.ArrayList;
import java.util.Calendar;

public class Empresa {
	
	public static final String VEICULOS_SEPARATOR = "==============================";
	
	private String razaoSocial;
	private ArrayList<Veiculo> frota;
	
	public Empresa(String razaoSocial) {
		this.razaoSocial = razaoSocial;
		this.frota = new ArrayList<Veiculo>();
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public void inserirVeiculo(Veiculo veiculo) {
		frota.add(veiculo);
	}
	
	public void removerVeiculo(String placa) {
		for (Veiculo veiculo : frota) {
			if (veiculo.getPlaca().contentEquals(placa)) {
				frota.remove(veiculo);
			}
		}
	}
	
	public void setStatusVeiculo(String placa, StatusVeiculo status) {
		for (Veiculo veiculo : frota) {
			if (veiculo.getPlaca().contentEquals(placa)) {
				veiculo.setStatus(status);
			}
		}
	}
	
	public boolean registrarJornadaKmVeiculo(String placa, int jornadaKm, double jornadaLitrosGastas) {
		for (Veiculo veiculo : frota) {
			if (veiculo.getPlaca().contentEquals(placa)) {
				return veiculo.registrarJornadaKm(jornadaKm, jornadaLitrosGastas);
			}
		}
		return false;
	}
	
	public boolean abastecerVeiculo(String placa, Combustivel combustivel, double litros, double precoLitroCombustivel, Calendar data) {
		for (Veiculo veiculo : frota) {
			if (veiculo.getPlaca().contentEquals(placa)) {
				return veiculo.abastecer(combustivel, litros, precoLitroCombustivel, data);
			}
		}
		return false;
	}
	
	public void listVeiculosRevisao() {
		String msg = "";
		for (Veiculo veiculo : frota) {
			if (veiculo.needsRevisao()) {
				msg += veiculo.toString();
				msg += "PRECISA DE REVISÃO\n";
				msg += VEICULOS_SEPARATOR + "\n";
			}
		}
		System.out.println(msg);
	}
	
	public void listVeiculosLowRendimento() {
		String msg = "";
		for (Veiculo veiculo : frota) {
			if (veiculo.compareRendimento() > 0) {
				msg += veiculo.toString();
				msg += "POSSUI BAIXO RENDIMENTO\n";
				msg += VEICULOS_SEPARATOR + "\n";
			}
		}
		System.out.println(msg);
	}
	
	public double getValorPagoPeriod(Calendar initialData, Calendar endData) {
		double valorPagoPeriod = 0;
		for (Veiculo veiculo : frota) {
			valorPagoPeriod += veiculo.getValorPagoPeriod(initialData, endData);
		}
		return valorPagoPeriod;
	}
	
	public void restartJornadaVeiculo(String placa) {
		for (Veiculo veiculo : frota) {
			if (veiculo.getPlaca().contentEquals(placa)) {
				veiculo.restartJornada();
			}
		}
	}
	
	public void restartJornadaFrota() {
		for (Veiculo veiculo : frota) {
			veiculo.restartJornada();
		}
	}
	
	public void printVeiculo(String placa) {
		String msg = "";
		for (Veiculo veiculo : frota) {
			if (veiculo.getPlaca().contentEquals(placa)) {
				msg += veiculo.toString();
				msg += VEICULOS_SEPARATOR + "\n";
			}
		}
		System.out.println(msg);
	}
	
	public void printFrota() {
		String msg = "";
		for (Veiculo veiculo : frota) {
			msg += veiculo.toString();
			msg += VEICULOS_SEPARATOR + "\n";
		}
		System.out.println(msg);
	}

}
