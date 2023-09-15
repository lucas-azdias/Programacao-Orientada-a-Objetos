package Projeto;

public class Goldfinger extends Terrorista implements Personificacao {
	
	private Heroi heroi;

	public Goldfinger(float posicao_x, float posicao_y, float posicao_z, int cor, Heroi heroi) {
		super(posicao_x, posicao_y, posicao_z, cor);
		
		this.heroi = heroi;
	}
	
	public Heroi getHeroi() {
		return heroi;
	}

	@Override
	public void saltar() {
		posicao_z++;
		
		System.out.println("Goldfinger saltando...");
	}

	@Override
	public void camuflar(int cor) {
		this.cor = cor;
		
		System.out.println("Goldfinger camuflando...");
	}

	@Override
	public void personificar(Heroi h) {
		heroi = h;
		
		System.out.println("Goldfinger personificando...");
	}

}
