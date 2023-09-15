package com.pbl05a;

public class Bebida {
	
	private String nomeFabricante;
	private double precoUnitario;
	private int quantidade;
	
	public Bebida(String nomeFabricante, double precoUnitario, int quantidade) {
		this.nomeFabricante = nomeFabricante;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
	}
	
	public void comprar(int quantidade) {
		this.quantidade += quantidade;
	}
	
	public void vender(int quantidade) {
		this.quantidade -= quantidade;
	}
	
	public void atualizarPreco(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	public boolean verifyNomeFabricante(String nomeFabricante) {
		if (this.nomeFabricante.contentEquals(nomeFabricante)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return nomeFabricante + "(R$" + precoUnitario + " - " + quantidade + ")";
	}

}
