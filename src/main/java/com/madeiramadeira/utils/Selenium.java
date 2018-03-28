/**
 * 
 */
package com.madeiramadeira.utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Classe Utils para o Selenium Identifica qual o browser escolhido no arquivo
 * de configuracao 'config.properties' e inicializa o webdriver correspondente *
 * 
 * @author ti-16
 * 
 */
public class Selenium {

	private static WebDriver driver = null;

	/**
	 * Verifica qual o browser escolhido no arquivo de propriedades inicializa o
	 * driver apropriado e o retorna
	 * 
	 * @return retorna inst�ncia do WebDriver
	 */

	public static WebDriver getDriver() {
		String browser = Property.BROWSER_NAME;
		if (driver == null) {
			if (Browser.CHROME.equals(browser)) {
				File file = new File(Property.CHROME_DRIVE_PATH);
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				driver = new ChromeDriver();
			}

		}
		return driver;
	}

}
