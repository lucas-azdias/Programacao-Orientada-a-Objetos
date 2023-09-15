package com.pbl05a;

import java.util.Scanner;

public class AppBeb {

	public static void main(String[] args) {
		Loja loja = new Loja("Loja");
		
		loja.addCerveja("Skol", 8, 50);
		loja.addCerveja("Antártica", 9, 86);
		loja.addCerveja("Itaipava", 9.6, 100);
		loja.addVinho("Porto", 105.6, 23, 1986, "Portugal", true, "Uva boa");
		loja.addVinho("Chileno", 56.7, 11, 2001, "Chile", true, "Uva mais boa ainda");
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Menu:\n0. Sair do programa\n1. Visualizar produtos\n2. Procurar produto pela marca\n3. Add Cerveja\n4. Add Vinho\n5. Comprar bebida via nome\n6. Vender bebida via nome\n7. Atualizar preço via nome");
			System.out.print(" > ");
			int option = scanner.nextInt();
			
			String nomeFabricante;
			double precoUnitario;
			int quantidade;
			int anoFabricacao;
			String paisOrigem;
			int isTinto;
			String tipoUva;
			switch (option) {
				case 0:
					System.out.println("Encerrando...");
					System.exit(0);
				case 1:
					System.out.println("Imprimindo todos...");
					loja.imprimirBebidas();
					break;
				case 2:
					System.out.print("Digite o nome da fabricante da bebida: ");
					nomeFabricante = scanner.next();
					loja.imprimirBebida(nomeFabricante);
					break;
				case 3:
					System.out.print("Digite o nome da fabricante da cerveja: ");
					nomeFabricante = scanner.next();
					System.out.print("Digite o preço unitário: ");
					precoUnitario = scanner.nextDouble();
					System.out.print("Digite a quantidade em estoque: ");
					quantidade = scanner.nextInt();
					
					loja.addCerveja(nomeFabricante, precoUnitario, quantidade);
					break;
				case 4:
					System.out.print("Digite o nome da fabricante do vinho: ");
					nomeFabricante = scanner.next();
					System.out.print("Digite o preço unitário: ");
					precoUnitario = scanner.nextDouble();
					System.out.print("Digite a quantidade em estoque: ");
					quantidade = scanner.nextInt();
					System.out.print("Digite o ano de fabricação: ");
					anoFabricacao = scanner.nextInt();
					System.out.print("Digite o país de origem: ");
					paisOrigem = scanner.next();
					System.out.print("Digite se é tinto [0: false; !0: true]: ");
					isTinto = scanner.nextInt();
					System.out.print("Digite o tipo de uva: ");
					tipoUva = scanner.next();
					
					loja.addVinho(nomeFabricante, precoUnitario, quantidade, anoFabricacao, paisOrigem, (isTinto != 0), tipoUva);
					break;
				case 5:
					System.out.print("Digite o nome da fabricante do vinho: ");
					nomeFabricante = scanner.next();
					System.out.print("Digite a quantidade comprada: ");
					quantidade = scanner.nextInt();
					
					loja.comprarBebida(nomeFabricante, quantidade);
					break;
				case 6:
					System.out.print("Digite o nome da fabricante do vinho: ");
					nomeFabricante = scanner.next();
					System.out.print("Digite a quantidade vendida: ");
					quantidade = scanner.nextInt();
					
					loja.venderBebida(nomeFabricante, quantidade);
					break;
				case 7:
					System.out.print("Digite o nome da fabricante do vinho: ");
					nomeFabricante = scanner.next();
					System.out.print("Digite o novo preço unitário: ");
					precoUnitario = scanner.nextDouble();
					
					loja.atualizarPrecoBebida(nomeFabricante, precoUnitario);
					break;
				default:
					System.out.println("Opção inválida.");
			}
			
			System.out.println("");
		}

	}

}
