package com.pbl05a;

public class Vinho extends Bebida {
	
	private int anoFabricacao;
	private String paisOrigem;
	private boolean isTinto;
	private String tipoUva;
	

	public Vinho(String nomeFabricante, double precoUnitario, int quantidade, int anoFabricacao, String paisOrigem, boolean isTinto, String tipoUva) {
		super(nomeFabricante, precoUnitario, quantidade);
		this.anoFabricacao = anoFabricacao;
		this.paisOrigem = paisOrigem;
		this.isTinto = isTinto;
		this.tipoUva = tipoUva;
	}
	
	public String toString() {
		return super.toString() + " [" + anoFabricacao + " - " + paisOrigem + "(Vinho " + (isTinto ? "tinto" : "branco") + " - tipo " + tipoUva + ")]";
	}
	
}
