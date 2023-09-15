package Projeto;

public class Pinguim extends Ladrao {

	public Pinguim(float posicao_x, float posicao_y, float posicao_z, int cor) {
		super(posicao_x, posicao_y, posicao_z, cor);
	}

	@Override
	public void correr() {
		posicao_x++;
		posicao_y++;
		
		System.out.println("Pinguim correndo...");
	}

	@Override
	public void atirar() {
		System.out.println("Pinguim atirando...");
	}
}
