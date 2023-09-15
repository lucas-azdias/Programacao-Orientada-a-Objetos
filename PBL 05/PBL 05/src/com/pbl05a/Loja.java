package com.pbl05a;

import java.util.ArrayList;

public class Loja {
	
	@SuppressWarnings("unused")
	private String nomeLoja;
	private ArrayList<Bebida> bebidas;
	
	public Loja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
		bebidas = new ArrayList<Bebida>();
	}
	
	public void addCerveja(String nomeFabricante, double precoUnitario, int quantidade) {
		bebidas.add(new Cerveja(nomeFabricante, precoUnitario, quantidade));
	}
	
	public void addVinho(String nomeFabricante, double precoUnitario, int quantidade, int anoFabricacao, String paisOrigem, boolean isTinto, String tipoUva) {
		bebidas.add(new Vinho(nomeFabricante, precoUnitario, quantidade, anoFabricacao, paisOrigem, isTinto, tipoUva));
	}
	
	public void comprarBebida(String nomeFabricante, int quantidade) {
		for (Bebida bebida : bebidas) {
			if (bebida.verifyNomeFabricante(nomeFabricante)) {
				bebida.comprar(quantidade);
			}
		}
	}
	
	public void venderBebida(String nomeFabricante, int quantidade) {
		for (Bebida bebida : bebidas) {
			if (bebida.verifyNomeFabricante(nomeFabricante)) {
				bebida.vender(quantidade);
			}
		}
	}
	
	public void atualizarPrecoBebida(String nomeFabricante, double precoUnitario) {
		for (Bebida bebida : bebidas) {
			if (bebida.verifyNomeFabricante(nomeFabricante)) {
				bebida.atualizarPreco(precoUnitario);
			}
		}
	}
	
	public void imprimirBebida(String nomeFabricante) {
		for (Bebida bebida : bebidas) {
			if (bebida.verifyNomeFabricante(nomeFabricante)) {
				System.out.println(bebida.toString());
			}
		}
	}
	
	public void imprimirBebidas() {
		for (Bebida bebida : bebidas) {
			System.out.println(bebida.toString());
		}
	}

}
