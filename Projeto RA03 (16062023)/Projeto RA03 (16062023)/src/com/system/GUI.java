package com.system;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class GUI extends JFrame implements Serializable {

	private static final long serialVersionUID = -302240900615363795L;
	
	public static final int BORDER_SPACE = 40;

	private String title;
	private String iconPath;
	private Dimension dimension;
	private BrokerageFirm brokerageFirm;
	private String userIdCode;

	public GUI(String title, String iconPath, Dimension dimension, BrokerageFirm brokerageFirm) {
		this.title = title;
		this.iconPath = iconPath;
		this.dimension = dimension;
		this.brokerageFirm = brokerageFirm;

		userIdCode = null;

		// Configuração do JFrame
		setTitle(title);
		loadIcon();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setAutoRequestFocus(true);
		setResizable(false);
		setSize(dimension);
		setPreferredSize(dimension);
		setLocationRelativeTo(null);
		pack();
		setLayout(null);
		setVisible(false);
	}
	
	public void start() {
		if (userIdCode == null) {
			setPageLogin();
		} else {
			setPageMain();
		}
		setVisible(true);
	}
	
	public void loadIcon() {
		Image icon = null;
		try {
			icon = ImageIO.read(new File(iconPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setIconImage(icon);
	}
	
	private Container startNewPage() {
		Container container = new Container();
		container.setLayout(null);
		for (KeyListener k : this.getKeyListeners()) {;
			this.removeKeyListener(k);
		}
		return container;
	}
	
	private void loadNewPage(Container container) {
		for (Component c : container.getComponents()) {
			for (KeyListener k : this.getKeyListeners()) {
				c.addKeyListener(k);
			}
		}
		setContentPane(container);
		pack();
	}
	
	private JButton createLeftEndButton(String text, ActionListener actionListener) {
		JButton button = new JButton(text);
		button.setBounds(BORDER_SPACE, dimension.height - BORDER_SPACE * 2 - 25, 150, 25);
		button.addActionListener(actionListener);
		this.addKeyListener(new BasicKeyListener(KeyEvent.VK_ESCAPE, actionListener));
		return button;
	}
	
	private JButton createRightEndButton(String text, ActionListener actionListener) {
		JButton button = new JButton(text);
		button.setBounds(dimension.width - BORDER_SPACE - 150, dimension.height - BORDER_SPACE * 2 - 25, 150, 25);
		button.addActionListener(actionListener);
		this.addKeyListener(new BasicKeyListener(KeyEvent.VK_ENTER, actionListener));
		return button;
	}
	
	private JLabel createLabel(String text, int y) {
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setBounds(BORDER_SPACE, BORDER_SPACE + y, dimension.width - 2 * BORDER_SPACE, 20);
		return label;
	}
	
	private JTextField createTextField(int y) {
		JTextField textField = new JTextField();
		textField.setBounds(BORDER_SPACE, BORDER_SPACE + y, dimension.width - 2 * BORDER_SPACE, 25);
		return textField;
	}
	
	private JRadioButton[] createRadiusButtons(String[] options, int y_jump, int y_base) {
		ButtonGroup radiosGroup = new ButtonGroup();
		JRadioButton[] radios = new JRadioButton[options.length];
		for (int i = 0; i < options.length; i++) {
			radios[i] = new JRadioButton(options[i]);
			radios[i].setBounds(BORDER_SPACE, BORDER_SPACE + y_jump * (i + 1) + y_base, dimension.width - 2 * BORDER_SPACE, y_jump);
			radiosGroup.add(radios[i]);
		}
		return radios;
	}
	
	private TextInput[] createRegister(String[] labels, int y_base) {
		TextInput[] texts = new TextInput[labels.length];
		for (int i = 0; i < labels.length; i++) {
			int y = 30 * 2 * (i + 1) + y_base;
			texts[i] = new TextInput(createLabel(labels[i], y - 25), createTextField(y));
		}
		return texts;
	}
	
	private JScrollPane createScrollTextArea(String[] data, String lineSeparator, int y_base) {
		JTextArea dataListed = new JTextArea();
		dataListed.setEditable(false);
		for (String item : data) {
			dataListed.setText(dataListed.getText() + item + lineSeparator);
		}
		dataListed.setCaretPosition(0);
		
		JScrollPane scrollPane = new JScrollPane(dataListed);
		scrollPane.setBounds(BORDER_SPACE, BORDER_SPACE + y_base, dimension.width - 2 * BORDER_SPACE, dimension.height - 200);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return scrollPane;
	}

	private int createConfirmWindow(String title, String message) {
		return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE);
	}
	
	private void setPageMain() {
		Container container = startNewPage();

		container.add(createLabel("Escolha um opção:", 0));

		JRadioButton[] radios = createRadiusButtons(BrokerageFirm.MAIN_INTERFACE_OPTIONS, 40, 0);
		for (JRadioButton radio : radios) {
			container.add(radio);
		}
		
		container.add(createLeftEndButton("Deslogar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					userIdCode = null;
					createConfirmWindow(title, "Deslogado com sucesso.");
					setPageLogin();
				}
			}
		));
		
		container.add(createRightEndButton("Continuar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (JRadioButton radio : radios) {
						if (radio.isSelected()) {
							for (int i = 0; i < BrokerageFirm.MAIN_INTERFACE_OPTIONS.length; i++) {
								if (radio.getActionCommand().contentEquals(BrokerageFirm.MAIN_INTERFACE_OPTIONS[i]) ) {
									switch (i) {
										case 0:
											setPageAddStock();
											break;
										case 1:
											setPageAddBond();
											break;
										case 2:
											setPageListAssets();
											break;
										case 3:
											setPageInsertAssetToPortfolio();
											break;
										case 4:
											setPageListAssetsInPortfolio();
											break;
										case 5:
											setPageInsertOrderToPortfolio();
											break;
										case 6:
											setPageListOrdersInPortfolio();
											break;
										case 7:
											setPageGetReturnOfPortfolio();
											break;
										case 8:
											setPageSaveData();
											break;
									}
								}
							}
						}
					}
				}
			}
		));
		
		loadNewPage(container);
	}
	
	private void setPageLogin() {
		Container container = startNewPage();

		container.add(createLabel("Login de usuário", 0));
		
		container.add(createLabel("Digite o seu CPF:", 30 * 2));
		JTextField idCode = createTextField(30 * 2 + 25);
		container.add(idCode);
		
		
		container.add(createLeftEndButton("Cadastrar conta",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPageSignIn();
				}
			}
		));
		
		container.add(createRightEndButton("Entrar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (brokerageFirm.verifyClient(idCode.getText())) {
						userIdCode = idCode.getText();
						System.out.println("Logado com: " + userIdCode);
						createConfirmWindow(title, "Logado com sucesso.");
						setPageMain();
					} else {
						createConfirmWindow(title, "Credenciais inválidas.");
					}
				}
			}
		));
		
		loadNewPage(container);
	}
	
	private void setPageSignIn() {
		Container container = startNewPage();

		container.add(createLabel("Cadastro de usuário", 0));


		String[] labelsStrings = {"Nome", "CPF", "Ano do nascimento",
				"Mês do nascimento", "Dia do nascimento"};
		TextInput[] texts = createRegister(labelsStrings, 0);
		for (TextInput text : texts) {
			container.add(text.getLabel());
			container.add(text.getTextField());
		}
		
		
		container.add(createLeftEndButton("Entrar em conta",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPageLogin();
				}
			}
		));
		
		container.add(createRightEndButton("Cadastrar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean result = false;
					try {
						result = brokerageFirm.insertClient(
							texts[0].getTextField().getText(),
							texts[1].getTextField().getText(),
							Integer.parseInt(texts[2].getTextField().getText()),
							Integer.parseInt(texts[3].getTextField().getText()),
							Integer.parseInt(texts[4].getTextField().getText())
						);
					} catch (Exception ex) {
						System.out.println("Erro de conversão!");
					} finally {
						if (result) {
							createConfirmWindow(title, "Cadastrado com sucesso.");
							setPageLogin();
						} else {
							createConfirmWindow(title, "Parâmetros inválidos.");
						}
					}
				}
			}
		));
		
		loadNewPage(container);
	}

	private void setPageAddStock() {
		Container container = startNewPage();

		container.add(createLabel("Cadastro de ação", 0));

		String[] labelsStrings = {"Nome", "Código do ativo", "Preço"};
		TextInput[] texts = createRegister(labelsStrings, 0);
		for (TextInput text : texts) {
			container.add(text.getLabel());
			container.add(text.getTextField());
		}
		
		container.add(createLeftEndButton("Voltar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPageMain();
				}
			}
		));
		
		container.add(createRightEndButton("Cadastrar ação",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean result = false;
					try {
						result = brokerageFirm.addStock(
							texts[0].getTextField().getText(),
							texts[1].getTextField().getText(),
							Double.parseDouble(texts[2].getTextField().getText())
						);
					} catch (Exception ex) {
						System.out.println("Erro de conversão!");
					}  finally {
						if (result) {
							createConfirmWindow(title, "Foi cadastrado com sucesso.");
							setPageMain();
						} else {
							createConfirmWindow(title, "Erro ao cadastrar.");
						}
					}
				}
			}
		));
		
		loadNewPage(container);
	}
	
	private void setPageAddBond() {
		Container container = startNewPage();

		container.add(createLabel("Cadastro de título", 0));
		
		container.add(createLabel("Tipo do título", 30 * 2 - 25));
		String[] bondTypeOptions = {"Título público", "Título privado"};
		JRadioButton[] radios = createRadiusButtons(bondTypeOptions, 25, 30);
		for (JRadioButton radio : radios) {
			container.add(radio);
		}

		String[] labelsStrings = {"Nome", "Código do ativo", "Preço", "Preço no vencimento",
				"Ano do vencimento", "Mês do vencimento", "Dia do vencimento"};
		TextInput[] texts = createRegister(labelsStrings, 50 + 30);
		for (TextInput text : texts) {
			container.add(text.getLabel());
			container.add(text.getTextField());
		}
		
		container.add(createLeftEndButton("Voltar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPageMain();
				}
			}
		));
		
		container.add(createRightEndButton("Cadastrar título",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean result = false;
					try {
						if (result) {
							result = brokerageFirm.addTreasuryBond(
								texts[0].getTextField().getText(),
								texts[1].getTextField().getText(),
								Double.parseDouble(texts[2].getTextField().getText()),
								Double.parseDouble(texts[3].getTextField().getText()),
								Integer.parseInt(texts[4].getTextField().getText()),
								Integer.parseInt(texts[5].getTextField().getText()),
								Integer.parseInt(texts[6].getTextField().getText())
							);
						} else {
							result = brokerageFirm.addCorporateBond(texts[0].getTextField().getText(), texts[1].getTextField().getText(),
									Double.parseDouble(texts[2].getTextField().getText()), Double.parseDouble(texts[3].getTextField().getText()),
									Integer.parseInt(texts[4].getTextField().getText()), Integer.parseInt(texts[5].getTextField().getText()),
									Integer.parseInt(texts[6].getTextField().getText()));
						}
					} catch (Exception ex) {
						System.out.println("Erro de conversão!");
					} finally {
						if (result) {
							createConfirmWindow(title, "Foi cadastrado com sucesso.");
							setPageMain();
						} else {
							createConfirmWindow(title, "Erro ao cadastrar.");
						}
					}
				}
			}
		));
		
		loadNewPage(container);
	}
	
	private void setPageListAssets() {
		Container container = startNewPage();

		container.add(createLabel("Listagem dos ativos em bolsa", 0));

		String[] assets = brokerageFirm.listAssets();
		container.add(createScrollTextArea(assets, "\n", 40));
		
		container.add(createLeftEndButton("Voltar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPageMain();
				}
			}
		));
		
		loadNewPage(container);
	}
	
	private void setPageInsertAssetToPortfolio() {
		Container container = startNewPage();

		container.add(createLabel("Inserção de ativo no portfolio do cliente", 0));

		String[] labelsStrings = {"Código do ativo"};
		TextInput[] texts = createRegister(labelsStrings, 0);
		for (TextInput text : texts) {
			container.add(text.getLabel());
			container.add(text.getTextField());
		}
		
		container.add(createLeftEndButton("Voltar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPageMain();
				}
			}
		));
		
		container.add(createRightEndButton("Inserir ativo",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean result = false;
					try {
						result = brokerageFirm.insertAssetOnPortfolio(
							userIdCode,
							texts[0].getTextField().getText()
						);
					} catch (Exception ex) {
						System.out.println("Erro de conversão!");
					}  finally {
						if (result) {
							createConfirmWindow(title, "Foi inserido com sucesso.");
							setPageMain();
						} else {
							createConfirmWindow(title, "Erro ao inserir.");
						}
					}
				}
			}
		));
		
		loadNewPage(container);
	}
	
	private void setPageListAssetsInPortfolio() {
		Container container = startNewPage();

		container.add(createLabel("Listagem dos ativos em carteira", 0));

		String[] assets = {"Erro ao encontrar a carteira."};
		try {
			assets = brokerageFirm.listAssetsOnPortfolio(userIdCode);
		} catch (PortfolioNotFound e) {
			e.printStackTrace();
		}
		container.add(createScrollTextArea(assets, "\n", 40));
		
		container.add(createLeftEndButton("Voltar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPageMain();
				}
			}
		));
		
		loadNewPage(container);
	}

	private void setPageInsertOrderToPortfolio() {
		Container container = startNewPage();

		container.add(createLabel("Inserção de operação no portfolio do cliente", 0));

		String[] labelsStrings = {"Código do ativo", "Quantidade", "Preço da operação",
				"Ano da operação", "Mês da operação", "Dia da operação"};
		TextInput[] texts = createRegister(labelsStrings, 0);
		for (TextInput text : texts) {
			container.add(text.getLabel());
			container.add(text.getTextField());
		}
		
		container.add(createLeftEndButton("Voltar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPageMain();
				}
			}
		));
		
		container.add(createRightEndButton("Inserir operação",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean result = false;
					try {
						result = brokerageFirm.insertOrderOnPortfolio(
							userIdCode,
							texts[0].getTextField().getText(),
							Integer.parseInt(texts[1].getTextField().getText()),
							Double.parseDouble(texts[2].getTextField().getText()),
							Integer.parseInt(texts[3].getTextField().getText()),
							Integer.parseInt(texts[4].getTextField().getText()),
							Integer.parseInt(texts[5].getTextField().getText())
						);
					} catch (Exception ex) {
						System.out.println("Erro de conversão!");
					}  finally {
						if (result) {
							createConfirmWindow(title, "Foi inserido com sucesso.");
							setPageMain();
						} else {
							createConfirmWindow(title, "Erro ao inserir.");
						}
					}
				}
			}
		));
		
		loadNewPage(container);
	}
	
	private void setPageListOrdersInPortfolio() {
		Container container = startNewPage();

		container.add(createLabel("Listagem das ordens em carteira", 0));

		String[] orders = {"Erro ao encontrar a carteira."};
		try {
			orders = brokerageFirm.listOrdersOnPortfolio(userIdCode);
		} catch (PortfolioNotFound e) {
			e.printStackTrace();
		}
		container.add(createScrollTextArea(orders, "\n\n", 40));
		
		container.add(createLeftEndButton("Voltar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPageMain();
				}
			}
		));
		
		loadNewPage(container);
	}
	
	private void setPageGetReturnOfPortfolio() {
		Container container = startNewPage();

		container.add(createLabel("Retorno do portfólio", 0));

		JTextArea dataListed = new JTextArea();
		dataListed.setBounds(BORDER_SPACE, BORDER_SPACE + 40, dimension.width - 2 * BORDER_SPACE, dimension.height - 200);
		dataListed.setEditable(false);
		try {
			dataListed.setText(
				"Retorno do portfólio: " + Utils.round(brokerageFirm.getPortfolioReturn(userIdCode), 2) + "%\n" +
				"Retorno do portfólio anualizado: " + Utils.round(brokerageFirm.getPortfolioReturnAnualized(userIdCode), 2) + "%"
			);
		} catch (PortfolioNotFound e) {
			e.printStackTrace();
			dataListed.setText("Erro ao encontrar a carteira.");
		}
		container.add(dataListed);
		
		container.add(createLeftEndButton("Voltar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPageMain();
				}
			}
		));
		
		loadNewPage(container);
	}
	
	private void setPageSaveData() {
		Container container = startNewPage();

		container.add(createLabel("Salvamento de dados (Null = DEFAULT)", 0));
		
		String[] labelsStrings = {
			"Caminho para salvar dados",
			"Caminho para salvar ativos separadamente como CSV",
			"Caminho para salvar clientes separadamente como TXT"
		};
		TextInput[] texts = createRegister(labelsStrings, 0);
		for (TextInput text : texts) {
			container.add(text.getLabel());
			container.add(text.getTextField());
		}
		
		container.add(createLeftEndButton("Voltar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setPageMain();
				}
			}
		));
		
		container.add(createRightEndButton("Salvar",
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String pathStr = texts[0].getTextField().getText();
					String assetsPathStr = texts[1].getTextField().getText();
					String personsPathStr = texts[2].getTextField().getText();
					if (pathStr.contentEquals("")) {
						pathStr = BrokerageFirm.DEFAULT_INTERFACE_SAVE_DATA_PATH;
					}
					if (assetsPathStr.contentEquals("")) {
						assetsPathStr = BrokerageFirm.DEFAULT_INTERFACE_SAVE_ASSETS_PATH;
					}
					if (personsPathStr.contentEquals("")) {
						personsPathStr = BrokerageFirm.DEFAULT_INTERFACE_SAVE_PERSONS_PATH;
					}
					try {
						String tempUserIdCode = userIdCode;
						userIdCode = null;
						brokerageFirm.saveData(pathStr);
						brokerageFirm.getAssetsToCSV(assetsPathStr);
						brokerageFirm.getPersonsToTxt(personsPathStr);
						userIdCode = tempUserIdCode;
						createConfirmWindow(title, "Dados salvos com sucesso.");
						setPageMain();
					} catch (IOException ex) {
						ex.printStackTrace();
						createConfirmWindow(title, "Erro no salvamento dos dados.");
					}
				}
			}
		));
		
		loadNewPage(container);
	}
	
	private class TextInput {
		private JLabel label;
		private JTextField textField;
		
		public TextInput(JLabel label, JTextField textField) {
			this.label = label;
			this.textField = textField;
		}

		public JLabel getLabel() {
			return label;
		}

		public JTextField getTextField() {
			return textField;
		}
	}
	
	private class BasicKeyListener implements KeyListener {
		
		private int keyCode;
		private ActionListener action;
		
		public BasicKeyListener(int keyCode, ActionListener action) {
			this.keyCode = keyCode;
			this.action = action;
		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == keyCode) {
				action.actionPerformed(null);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}

}
