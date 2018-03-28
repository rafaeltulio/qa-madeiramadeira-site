package com.madeiramadeira.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.madeiramadeira.utils.Selenium;

public class AreaVipPage {

	/**
	 * Instancia privada do webDriver que vira da suite de teste
	 */

	private static WebDriver driver;
	private static WebDriverWait wait;
	/**
	 * Construtor que ira adicionar a instancia do WebDriver para utilizacao dos
	 * metodos
	 */
	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 10);
	}

	/**
	 * Defini��o �nica dos locators utilizados na p�gina
	 */
	
	static By linkSair = By.xpath("//a[contains(text(),'Sair')]");

	public AreaVipPage() {
	}

	public CadastroPage clicarButtonSair() {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(linkSair));
		driver.findElement(linkSair).click();	
		System.out.println("Bot�o Sair �rea Vip!");
		return new CadastroPage();
	}
}
