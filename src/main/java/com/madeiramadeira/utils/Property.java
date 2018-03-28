/**
 * 
 */
package com.madeiramadeira.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * @author ti-16 
 * Define o caminho do driver dos diferentes browsers Acessa as
 * configura��es definidas no config.properties e retorna a informa��o
 */
public class Property {

	public static String CHROME_DRIVE_PATH;
	public static String IE64_DRIVE_PATH;
	public static String FIREFOX_DRIVE_PATH;
	public static final String BROWSER_NAME;
	public static final String SITE_ADDRESS;
	public static final String SITE_ADDRESS_PESQUISA;	
	public static final String ADDRESS_GERADORCPF;
	public static final String CSV_CPF;
	public static final String CSV_CNPJ;
	

	private static final String PROPERTIES_FILE = "com/madeiramadeira/properties/config.properties";

	static {
		
		System.out.println(CHROME_DRIVE_PATH = new File("").getAbsolutePath() + "\\src\\main\\resources\\chromedriver.exe");
		IE64_DRIVE_PATH = new File("").getAbsolutePath() + "\\src\\main\\resources\\IEDriverServer.exe";
		FIREFOX_DRIVE_PATH = new File("").getAbsolutePath() + "%PROGRAMFILES%\\Mozilla Firefox\\firefox.exe";
		BROWSER_NAME = get("browser.name");
		SITE_ADDRESS = get("site.address");
		SITE_ADDRESS_PESQUISA = get("site.address.pesquisa");
		ADDRESS_GERADORCPF = get("address.geradorCPF");
		CSV_CPF = get("csv.cpf");
		CSV_CNPJ = get("csv.cnjp");

	}

	
	/**
	 * Metodo para pegar o valor de alguma propriedade no arquivo de
	 * configuracao do Selenium O caminho e o nome do arquivo pode ser trocados
	 */
	private static String get(String name) {
		Properties properties = new Properties();
		String value = null;
		try {
			properties.load(Property.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
			value = properties.getProperty(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}
