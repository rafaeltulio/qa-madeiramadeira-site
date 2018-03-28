package com.madeiramadeira.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.madeiramadeira.utils.Property;
import com.madeiramadeira.utils.Selenium;

public class ResultProdutoPage {
	/**
	 * Inst�ncia privada do webDriver que vira da suite de teste
	 */

	private static WebDriver driver;
	private static WebDriverWait wait;
	/**
	 * Construtor que ira adicionar a inst�ncia do WebDriver para utilizacao dos
	 * metodos
	 */
	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 10);
	}

	/**
	 * Defini��o �nica dos locators utilizados na p�gina
	 */

	static By identificadorProduto = By.xpath("//ul[@id='neemu-breadcrumbs']/li[3]/a/h1");
	static By buttonCarrinho = By.xpath("//div[@id='wrapper']/article/div[3]/div[2]/div[2]/div/div/div[3]/div[2]/button");
	
	
	public ResultProdutoPage() {
	}
	
	public void validaUrlProduto() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(identificadorProduto));
		String getIdentificador = driver.findElement(identificadorProduto).getText();
		Assert.assertEquals("146874", getIdentificador);
		String URLatual = driver.getCurrentUrl();
		String URLexpected = Property.SITE_ADDRESS_PESQUISA+ "busca?q=146874";
		String getTitle = driver.getTitle();
		System.out.println("URL do Produto Pesquisado: "+Property.SITE_ADDRESS_PESQUISA+ "busca?q=146874");
		System.out.println("Titulo da Pagina: "+getTitle);
		Assert.assertEquals("146874 - Madeira Madeira", getTitle);
		Assert.assertEquals(URLexpected, URLatual);		
	}

	public void clicarProduto() {
		WebElement linkProd = driver.findElement(By.xpath("//img[@Painel para TV Capri Línea Brasil Chocolate Wood']"));
		linkProd.click();
		System.out.println("Clicado no Link do Produto para adicionar ao carrinho!");

	}

	public CarrinhoProdutoPage adicionarCarrinho() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCarrinho));
		WebElement buttonAdicionarCarrinho = driver.findElement(buttonCarrinho);
		buttonAdicionarCarrinho.click();
		System.out.println("Clicado no bot�o Adicionar Carrinho!");
		return new CarrinhoProdutoPage();
	}

	public CarrinhoProdutoPage preencherFrete(String frete) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCarrinho));
		WebElement inputFrete = driver.findElement(By.id("cep"));
		inputFrete.clear();
		inputFrete.sendKeys(frete);
		inputFrete.sendKeys(Keys.TAB);
		System.out.println("Preenchido Freete para Cotação!" +frete);		
		return new CarrinhoProdutoPage();
	}

}
