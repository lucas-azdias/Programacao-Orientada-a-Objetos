package Projeto;

public abstract class Heroi extends Personagem {
	
	public Heroi(float posicao_x, float posicao_y, float posicao_z, int cor) {
		super(posicao_x, posicao_y, posicao_z, cor);
	}

	@Override
	public void correr() {
		posicao_x++;
		posicao_y++;
		
		System.out.println("Heroi correndo...");
	}

	@Override
	public void saltar() {
		posicao_z++;
		
		System.out.println("Heroi saltando...");
	}
	
}
