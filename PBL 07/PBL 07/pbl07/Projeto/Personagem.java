package Projeto;

public abstract class Personagem {
	
	private boolean vivo;
	
	protected float posicao_x;
	protected float posicao_y;
	protected float posicao_z;
	protected int cor;
	
	
	public Personagem(float posicao_x, float posicao_y, float posicao_z, int cor) {
		this.posicao_x = posicao_x;
		this.posicao_y = posicao_y;
		this.posicao_z = posicao_z;
		this.cor = cor;
		
		vivo = true;
	}
	
	public boolean getVivo() {
		return vivo;
	}
	
	public abstract void correr();
	public abstract void saltar();
	public abstract void atirar();
	
	public void morrer() {
		vivo = false;
		
		System.out.println("Morto");
	}
	
}
