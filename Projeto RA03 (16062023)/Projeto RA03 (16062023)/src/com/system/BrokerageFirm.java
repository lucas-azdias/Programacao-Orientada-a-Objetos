package com.system;

import java.awt.Dimension;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import com.iosystem.IOBufferedCSV;
import com.iosystem.IOBufferedObjects;
import com.iosystem.IOBufferedText;

public class BrokerageFirm implements Serializable {

	private static final long serialVersionUID = -607703662365884551L;

	public static final String DEFAULT_INTERFACE_TITLE = "Sistema de portfólio";
	public static final String DEFAULT_INTERFACE_ICON_PATH = "data/imgs/dolar-sign.png";
	public static final Dimension DEFAULT_INTERFACE_DIMENSION = new Dimension(480, 720);
	public static final String DEFAULT_INTERFACE_SAVE_DATA_PATH = "data/output/data.dat";
	public static final String DEFAULT_INTERFACE_SAVE_ASSETS_PATH = "data/output/assets.csv";
	public static final String DEFAULT_INTERFACE_SAVE_PERSONS_PATH = "data/output/persons.txt";
	
	public static final String[] MAIN_INTERFACE_OPTIONS = {
		"Adicionar ação", "Adicionar título",
		"Listar ativos na bolsa",
		"Adicionar ativos à carteira",
		"Listar ativos na carteira",
		"Adicionar ordens a um ativo",
		"Listar ordens na carteira",
		"Ver rendimento da carteira",
		"Salvar dados"
	};

	public ArrayList<Asset> assets;
	public ArrayList<Portfolio> portfolios;
	private GUI gui;

	public BrokerageFirm() {
		this.assets = new ArrayList<>();
		this.portfolios = new ArrayList<>();
		this.gui = new GUI(DEFAULT_INTERFACE_TITLE, DEFAULT_INTERFACE_ICON_PATH, DEFAULT_INTERFACE_DIMENSION, this);
	}

	public BrokerageFirm(String title, String iconPath, Dimension dimension) {
		this.assets = new ArrayList<>();
		this.portfolios = new ArrayList<>();
		this.gui = new GUI(title, iconPath, dimension, this);
	}

	public static void saveData(BrokerageFirm brokerageFirm, String path) throws IOException {
		(new IOBufferedObjects(path)).write(brokerageFirm);
	}

	public static BrokerageFirm loadData(String path) throws IOException, ClassNotFoundException {
		BrokerageFirm brokerageFirm =(BrokerageFirm) (new IOBufferedObjects(path)).read();
		brokerageFirm.loadIconGUI();
		return brokerageFirm;
	}

	public void saveData(String path) throws IOException {
		BrokerageFirm.saveData(this, path);
	}

	public void startGUI() {
		gui.start();
	}
	
	public void loadIconGUI() {
		gui.loadIcon();
	}

	public boolean insertClient(String name, String idCode, int year, int month, int day) {
		if (getPortfolio(idCode) == null) {
			portfolios.add(new Portfolio(name, idCode, year, month, day));
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyClient(String idCode) {
		if (getPortfolio(idCode) != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addStock(String name, String code, double price) {
		return insertAsset(new Stock(name, code, price), code);
	}

	public boolean addTreasuryBond(String name, String code, double price, double maturityPrice, int year, int month,
			int day) {
		return insertAsset(new TreasuryBond(name, code, price, maturityPrice, year, month, day), code);
	}

	public boolean addTreasuryBond(double yield, String name, String code, double maturityPrice, int year, int month,
			int day) {
		return insertAsset(new TreasuryBond(yield, name, code, maturityPrice, year, month, day), code);
	}

	public boolean addCorporateBond(String name, String code, double price, double maturityPrice, int year, int month,
			int day) {
		return insertAsset(new CorporateBond(name, code, price, maturityPrice, year, month, day), code);
	}

	public boolean addCorporateBond(double yield, String name, String code, double maturityPrice, int year, int month,
			int day) {
		return insertAsset(new CorporateBond(yield, name, code, maturityPrice, year, month, day), code);
	}

	public String[] listAssets() {
		String[] assets = new String[this.assets.size()];
		for (int i = 0; i < this.assets.size(); i++) {
			assets[i] = this.assets.get(i).toString();
		}
		Arrays.sort(assets);
		return assets;
	}

	public boolean insertAssetOnPortfolio(String idCode, String assetCode) {
		Portfolio portfolio = getPortfolio(idCode);
		if (portfolio != null) {
			Asset asset = getAsset(assetCode);
			if (asset != null) {
				return portfolio.insertPortfolioItem(asset);
			}
		}
		return false;
	}

	public String[] listAssetsOnPortfolio(String idCode) throws PortfolioNotFound {
		Portfolio portfolio = getPortfolio(idCode);
		if (portfolio != null) {
			return portfolio.assetsToString();
		} else {
			throw new PortfolioNotFound();
		}
	}

	public boolean insertOrderOnPortfolio(String idCode, String assetCode, int amount, double price, int year,
			int month, int day) {
		Portfolio portfolio = getPortfolio(idCode);
		if (portfolio != null) {
			return portfolio.insertOrder(assetCode, amount, price, year, month, day);
		} else {
			return false;
		}
	}

	public String[] listOrdersOnPortfolio(String idCode) throws PortfolioNotFound {
		Portfolio portfolio = getPortfolio(idCode);
		if (portfolio != null) {
			return portfolio.ordersToString();
		} else {
			throw new PortfolioNotFound();
		}
	}

	public double getPortfolioReturn(String idCode) throws PortfolioNotFound {
		Portfolio portfolio = getPortfolio(idCode);
		if (portfolio != null) {
			return portfolio.getReturn();
		} else {
			throw new PortfolioNotFound();
		}
	}

	public double getPortfolioReturnAnualized(String idCode) throws PortfolioNotFound {
		Portfolio portfolio = getPortfolio(idCode);
		if (portfolio != null) {
			return portfolio.getReturnAnualized();
		} else {
			throw new PortfolioNotFound();
		}
	}

	public int addPersonsFromTxt(String path) throws IOException {
		int personCount = 0;

		String[] lines = (new IOBufferedText(path)).readLines();

		for (String line : lines) {
			String[] values = line.split(",");
			String name = values[0];
			String idCode = values[1];
			int year = Integer.parseInt(values[2]);
			int month = Integer.parseInt(values[3]);
			int day = Integer.parseInt(values[4]);
			if (insertClient(name, idCode, year, month, day)) {
				personCount++;
			}
		}

		return personCount;
	}

	public int addAssetsFromCSV(String path) throws IOException {
		int assetCount = 0;

		String[][] lines = (new IOBufferedCSV(path)).readLinesCSV();

		for (String[] line : lines) {
			String assetType = line[0].toLowerCase();
			String name = line[1];
			String code = line[2].toUpperCase();
			double price = Double.parseDouble(line[3]);

			boolean result = false;
			if (assetType.contentEquals("stock")) {
				result = addStock(name, code, price);
			} else if (assetType.contentEquals("tbond")) {
				double maturityPrice = Double.parseDouble(line[4]);
				int year = Integer.parseInt(line[5]);
				int month = Integer.parseInt(line[6]);
				int day = Integer.parseInt(line[7]);
				result = addTreasuryBond(name, code, price, maturityPrice, year, month, day);
			} else if (assetType.contentEquals("cbond")) {
				double maturityPrice = Double.parseDouble(line[4]);
				int year = Integer.parseInt(line[5]);
				int month = Integer.parseInt(line[6]);
				int day = Integer.parseInt(line[7]);
				result = addCorporateBond(name, code, price, maturityPrice, year, month, day);
			}

			if (result) {
				assetCount++;
			}
		}

		return assetCount;
	}

	public void getPersonsToTxt(String path) throws IOException {
		String[] lines = new String[portfolios.size()];

		for (int i = 0; i < portfolios.size(); i++) {
			lines[i] = portfolios.get(i).ownerDataInString(",");
		}

		(new IOBufferedText(path)).writeLines(lines);
	}

	public void getAssetsToCSV(String path) throws IOException {
		String[] lines = new String[assets.size()];

		for (int i = 0; i < assets.size(); i++) {
			lines[i] = assets.get(i).dataInString(IOBufferedCSV.DEFAULT_SEPARATOR);
		}

		(new IOBufferedText(path)).writeLines(lines);

	}

	private boolean insertAsset(Asset asset, String code) {
		if (getAsset(code) == null) {
			assets.add(asset);
			return true;
		} else {
			return false;
		}
	}

	private Asset getAsset(String code) {
		for (Asset asset : assets) {
			if (asset.verifyCode(code)) {
				return asset;
			}
		}
		return null;
	}

	private Portfolio getPortfolio(String idCode) {
		for (Portfolio portfolio : portfolios) {
			if (portfolio.verifyIdentity(idCode)) {
				return portfolio;
			}
		}
		return null;
	}

}
