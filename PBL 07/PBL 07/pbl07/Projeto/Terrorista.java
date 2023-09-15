package Projeto;

public abstract class Terrorista extends Vilao {

	public Terrorista(float posicao_x, float posicao_y, float posicao_z, int cor) {
		super(posicao_x, posicao_y, posicao_z, cor);
	}

	@Override
	public void atirar() {
		System.out.println("Terrorista atirando...");
	}

}
