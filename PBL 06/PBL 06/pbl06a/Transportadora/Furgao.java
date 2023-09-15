package Transportadora;

public class Furgao extends Veiculo {

	public static final String TIPO_NAME = "furgão";
	public static final Combustivel[] COMBUSTIVEIS_POSSIVEIS = {Combustivel.DIESEL, Combustivel.GASOLINA};
	public static int MAX_KM_JORNADA = 800;
	public static double RENDIMENTO_ESPERADO_KM_L = 8;
	
	public Furgao(String placa, String modelo, String marca, int km, double tanque) {
		super(placa, modelo, marca, km, tanque);
		this.tipo = TIPO_NAME;
		this.combustiveisPossiveis = Furgao.COMBUSTIVEIS_POSSIVEIS;
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
