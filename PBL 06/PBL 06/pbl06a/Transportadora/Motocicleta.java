package Transportadora;

public class Motocicleta extends Veiculo {

	public static final String TIPO_NAME = "motocicleta";
	public static final Combustivel[] COMBUSTIVEIS_POSSIVEIS = {Combustivel.GASOLINA};
	public static int MAX_KM_JORNADA = 500;
	public static double RENDIMENTO_ESPERADO_KM_L = 20;
	
	public Motocicleta(String placa, String modelo, String marca, int km, double tanque) {
		super(placa, modelo, marca, km, tanque);
		this.tipo = TIPO_NAME;
		this.combustiveisPossiveis = Motocicleta.COMBUSTIVEIS_POSSIVEIS;
	}

	@Override
	public double compareRendimento() {
		return RENDIMENTO_ESPERADO_KM_L - getRendimento();
	}

	@Override
	public boolean registrarJornadaKm(int jornadaKm, double jornadaLitrosGastos) {
		if (initialJornadaKm + jornadaKm <= MAX_KM_JORNADA && (tanque - jornadaLitrosGastos) >= 0) {
			km += jornadaKm;
			tanque -= jornadaLitrosGastos;
			return true;
		} else {
			return false;
		}
	}

}
