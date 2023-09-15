package com.pbl07;

import Projeto.*;

public class Teste {

	public static void main(String[] args) {
		// Criando os personagens
		Batman batman = new Batman(0, 0, 0, 255);
		JamesBond jamesBond = new JamesBond(0, 0, 0, 144);
		Coringa coringa = new Coringa(0, 0, 0, 100);
		Pinguim pinguim = new Pinguim(0, 0, 0, 10);
		Goldfinger goldfinger = new Goldfinger(0, 0, 0, 89, jamesBond);
		DrNo drNo = new DrNo(0, 0, 0, 69);
		
		// Chamando os métodos particulares de cada um
		// BATMAN
		batman.correr();
		batman.saltar();
		batman.atirar();
		batman.camuflar(0);
		batman.morrer();
		System.out.println();
		
		// JAMESBOND
		jamesBond.correr();
		jamesBond.saltar();
		jamesBond.atirar();
		jamesBond.morrer();
		System.out.println();
		
		// CORINGA
		coringa.correr();
		coringa.saltar();
		coringa.atirar();
		coringa.morrer();
		System.out.println();
		
		// PINGUIM
		pinguim.correr();
		pinguim.saltar();
		pinguim.atirar();
		pinguim.morrer();
		System.out.println();
		
		// GOLDFINGER
		goldfinger.correr();
		goldfinger.saltar();
		goldfinger.atirar();
		goldfinger.camuflar(0);
		goldfinger.personificar(batman);
		goldfinger.morrer();
		System.out.println();
		
		// DRNO
		drNo.correr();
		drNo.saltar();
		drNo.atirar();
		drNo.morrer();
		System.out.println();
	}

}
