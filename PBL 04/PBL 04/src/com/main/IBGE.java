package com.main;

public class IBGE {

	public static void main(String[] args) {
		
		// Estado -> Paraná
		Estado parana = new Estado("Paraná");
		parana.addMunicipio(new Municipio("Curitiba", 2400, 400));
		parana.addMunicipio(new Municipio("Guarapuava", 200, 100));
		parana.addMunicipio(new Municipio("Londrina", 800, 300));
		parana.addMunicipio(new Municipio("Maringá", 600, 200));
		
		
		// Estado -> Bahia
		Estado bahia = new Estado("Bahia");
		bahia.addMunicipio(new Municipio("Salvador", 3000, 400));
		bahia.addMunicipio(new Municipio("Juazeiro", 400, 100));
		bahia.addMunicipio(new Municipio("Ilhéus", 280, 200));
		bahia.addMunicipio(new Municipio("Itabuna", 320, 300));
		
		
		// País -> Brasil
		Pais brasil = new Pais("Brasil");
		brasil.addEstado(parana);
		brasil.addEstado(bahia);
		
		
		// Prints
		System.out.println(brasil.getEstadoByName("Paraná").getMunicipioByName("Curitiba").densidade()); // Quadro 1
		
		System.out.println(brasil.getEstadoByName("Paraná").densidade()); // Quadro 2
		System.out.println(brasil.getEstadoByName("Bahia").densidade()); // Quadro 3
		
		System.out.println(brasil.populacao()); // Quadro 4
		System.out.println(brasil.densidade()); // Quadro 5
		
	}

}
