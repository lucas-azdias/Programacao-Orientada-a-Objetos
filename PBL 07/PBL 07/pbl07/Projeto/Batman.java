package Projeto;

public class Batman extends Heroi implements Camuflagem {
	
	public Batman(float posicao_x, float posicao_y, float posicao_z, int cor) {
		super(posicao_x, posicao_y, posicao_z, cor);
	}

	@Override
	public void atirar() {
		System.out.println("Batman atirando...");
	}

	@Override
	public void camuflar(int cor) {
		this.cor = cor;
		
		System.out.println("Batman camuflando...");
	}

}
