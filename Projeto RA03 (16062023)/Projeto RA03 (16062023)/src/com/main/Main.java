package com.main;

import java.io.IOException;

import com.system.*;

public class Main {

	public static void main(String[] args) {
		BrokerageFirm b = null;
		try {
			b = BrokerageFirm.loadData(BrokerageFirm.DEFAULT_INTERFACE_SAVE_DATA_PATH);
			System.out.println("Objeto carregado com sucesso.");
		} catch (IOException | ClassNotFoundException e) {
			b = new BrokerageFirm();
			System.out.println("Objeto não encontrado.");
		}

		try {
			b.addAssetsFromCSV(BrokerageFirm.DEFAULT_INTERFACE_SAVE_ASSETS_PATH);
			b.addPersonsFromTxt(BrokerageFirm.DEFAULT_INTERFACE_SAVE_PERSONS_PATH);
		} catch (IOException e) {
			System.out.println("Arquivos de dados não foram encontrados.");
			try {
				b.addAssetsFromCSV("data/assets.csv");
				b.addPersonsFromTxt("data/persons.txt");
			} catch (IOException e1) {
				System.out.println("Arquivos de dados não foram carregados corretamente.");
			}
		}
		
		// Inserção do usuário de admin (para teste)
		b.insertClient("admin", "admin", 2000, 1, 1);
		
		b.startGUI();
	}

}
