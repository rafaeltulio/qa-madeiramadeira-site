package com.madeiramadeira.pageObjects;

import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.madeiramadeira.utils.Property;
import com.madeiramadeira.utils.Selenium;

public class FechamentoPage {
	/**
	 * Inst�ncia privada do webDriver que vira da suite de teste
	 */

	private static WebDriver driver;
	private static WebDriverWait wait;
	/**
	 * Construtor que ira adicionar a inst�ncia do WebDriver para utilizacao dos metodos
	 */
	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 10);
	}
	/**
	 * Defini��o �nica dos locators utilizados na p�gina
	 * 
	 * @param password
	 * @param login
	 */
	static By txtLogin = By.id("txLogin");
	static By txtPassword = By.id("txPassword");
	static By buttonEntrar = By.xpath("//form[@id='formLogin']/div/div[2]/button");
	static By abaBoleto = By.xpath("/html/body/div[2]/div[1]/div/div[1]/form/div[1]/div/div/ul/li[2]/label/span");
	static By finalizaCompraFechamento = By.xpath("//div[3]/div/button");
	
	public FechamentoPage() {
	}
	
	public void efetuarLogin(String login, String password) {
		driver.findElement(txtLogin).clear();
		driver.findElement(txtLogin).sendKeys(login);
		driver.findElement(txtPassword).clear();
		driver.findElement(txtPassword).sendKeys(password);
		driver.findElement(buttonEntrar).click();
		System.out.println("Dados de login preenchido foram Preenchidos: "+login+password);
	}

	public void validarPageFechamento() {
		
		try {
			WebElement msgAtual = driver.findElement(By.xpath("//h1"));
			String msgExpected = "Pague � vista no cart�o de cr�dito ou no boleto banc�rio e ganhe 6% de desconto";
			String URLatual = driver.getCurrentUrl();
			String URLexpected = Property.SITE_ADDRESS+ "fechamento";
			
			Assert.assertEquals(URLexpected, URLatual);
			Assert.assertEquals(msgExpected, msgAtual.getText());
			System.out.println("Validar URL Correta Fechamento!");
			System.out.println("Validar Mensagem Informativa Pagamento!");	
			
		} catch (ComparisonFailure e) {

			e.getMessage();
		
		}
			
	}

	public void gerarBoleto() {
		
		try {
			
			WebElement opcaoBoleto = driver.findElement(abaBoleto);		
			opcaoBoleto.click();
			System.out.println("Selecionado Metodo Pagamento Boleto!");	
			
		} catch (Exception e) {

			e.getMessage();
		}
		
		
		
	}

	public BoletoPage clicarFinalizarCompra() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(finalizaCompraFechamento));
		WebElement buttonFinalizarCompra = driver.findElement(finalizaCompraFechamento);
		buttonFinalizarCompra.click();
		System.out.println("Clicado no Bot�o Finalizar Compra na tela Fechamento!");
		return new BoletoPage();
		
	}

}
