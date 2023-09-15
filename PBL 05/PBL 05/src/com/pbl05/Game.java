package com.pbl05;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	private ArrayList<Jogador> jogadores;
	
	public Game() {
		jogadores = new ArrayList<Jogador>();
	}

	public static void main(String[] args) {
		Game game = new Game();
		
		game.jogadores.add(new Principiante("Alberto", 5, 1));
		game.jogadores.add(new Principiante("Fábio", 10, 2));
		game.jogadores.add(new Principiante("Roberto", 15, 3));
		
		game.jogadores.add(new Profissional("Eduardo", 4, 3));
		game.jogadores.add(new Profissional("Afonso", 12, 2));
		game.jogadores.add(new Profissional("Albertim", 8, 1));

		game.jogadores.add(new Senior("Juliano", 9, 5, 2));
		game.jogadores.add(new Senior("Albertisson", 18, 6, 7));
		game.jogadores.add(new Senior("Júlio", 21, 7, 9));

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Menu:\n0. Sair do programa\n1. Add Principiante\n2. Add Profissional\n3. Add Senior\n4. Aplicar Ganhar via nome\n5. Aplicar Perder via nome\n6. Aplicar Imprimir via nome\n7. Aplicar Imprimir a todos");
			System.out.print(" > ");
			int option = scanner.nextInt();
			
			String nome;
			int score;
			double bonus, capital, premio;
			switch (option) {
				case 0:
					System.out.println("Encerrando...");
					System.exit(0);
				case 1:
					System.out.print("Digite o nome do principiante: ");
					nome = scanner.next();
					System.out.print("Digite o score do principiante: ");
					score = scanner.nextInt();
					System.out.print("Digite o bônus do principiante: ");
					bonus = scanner.nextDouble();
					
					game.jogadores.add(new Principiante(nome, score, bonus));
					break;
				case 2:
					System.out.print("Digite o nome do profissional: ");
					nome = scanner.next();
					System.out.print("Digite o score do profissional: ");
					score = scanner.nextInt();
					System.out.print("Digite o capital do profissional: ");
					capital = scanner.nextDouble();
					
					game.jogadores.add(new Profissional(nome, score, capital));
					break;
				case 3:
					System.out.print("Digite o nome do sênior: ");
					nome = scanner.next();
					System.out.print("Digite o score do sênior: ");
					score = scanner.nextInt();
					System.out.print("Digite o capital do sênior: ");
					capital = scanner.nextDouble();
					System.out.print("Digite o prêmio do sênior: ");
					premio = scanner.nextDouble();
					
					game.jogadores.add(new Senior(nome, score, capital, premio));
					break;
				case 4:
					System.out.print("Digite o nome do jogador: ");
					nome = scanner.next();
					System.out.print("Digite o incremento no score do jogador: ");
					score = scanner.nextInt();
					for (Jogador jogador : game.jogadores) {
						if (nome.contentEquals(jogador.getNome())) {
							jogador.ganhar(score);
						}
					}
					break;
				case 5:
					System.out.print("Digite o nome do jogador: ");
					nome = scanner.next();
					System.out.print("Digite o decremento no score do jogador: ");
					score = scanner.nextInt();
					for (Jogador jogador : game.jogadores) {
						if (nome.contentEquals(jogador.getNome())) {
							jogador.perder(score);
						}
					}
					break;
				case 6:
					System.out.print("Digite o nome do jogador: ");
					nome = scanner.next();
					for (Jogador jogador : game.jogadores) {
						if (nome.contentEquals(jogador.getNome())) {
							jogador.imprimir();
						}
					}
					break;
				case 7:
					System.out.println("Imprimindo todos...");
					for (Jogador jogador : game.jogadores) {
						System.out.println("");
						jogador.imprimir();
					}
					break;
				default:
					System.out.println("Opção inválida.");
			}
			
			System.out.println("");
		}

	}

}
