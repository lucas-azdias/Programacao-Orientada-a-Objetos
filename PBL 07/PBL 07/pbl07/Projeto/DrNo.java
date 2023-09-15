package Projeto;

public class DrNo extends Terrorista {

	public DrNo(float posicao_x, float posicao_y, float posicao_z, int cor) {
		super(posicao_x, posicao_y, posicao_z, cor);
	}

	@Override
	public void correr() {
		posicao_x++;
		posicao_y++;
		
		System.out.println("DrNo correndo...");
	}

	@Override
	public void saltar() {
		posicao_z++;
		
		System.out.println("DrNo saltando...");
	}

	@Override
	public void atirar() {
		System.out.println("DrNo atirando...");
	}

}
