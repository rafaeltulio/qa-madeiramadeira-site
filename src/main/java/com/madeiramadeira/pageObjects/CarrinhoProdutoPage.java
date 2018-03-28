package com.madeiramadeira.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.madeiramadeira.utils.Selenium;


public class CarrinhoProdutoPage {

	/**
	 * Instância privada do webDriver que vira da suite de teste
	 */

	private static WebDriver driver;
	private static WebDriverWait wait;
	/**
	 * Construtor que ira adicionar a instância do WebDriver para utilizacao dos
	 * metodos
	 */
	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 10);
	}

	/**
	 * Definição única dos locators utilizados na página
	 */
	
	static By buttonFinalizarCompraCarrinho = By.id("btnFinalizarCompra");
	static By statusEntrega = By.id("shippingMethodName");
	static By statusPrazo = By.id("deliveryTime");
	static By statusValor = By.id("deliveryPrice");
	
		
	public CarrinhoProdutoPage(){
		}	
	
	/**
	 * Clicar no Botão Finalizar Compra
	 * @return FechamentoPage
	 */

	public FechamentoPage finalizarCompra() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(buttonFinalizarCompraCarrinho));
		WebElement buttonFinalizaCompra = driver.findElement(buttonFinalizarCompraCarrinho);
		buttonFinalizaCompra.click();
		System.out.println("Clicado no Botão Finalizar Compra na Página de Carrinho!");		
		return new FechamentoPage();
	}

	public void clicarButtonCalcularFrete() {
		WebElement btnCalculaFrete = driver.findElement(By.id("calc_frete"));
		btnCalculaFrete.click();
		System.out.println("Clicar no botão Calcular Frete!");
		
	}

	public void confereFreteStatus() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(statusEntrega));
		WebElement getEntrega = driver.findElement(statusEntrega);
		WebElement getStatusPrazo = driver.findElement(statusPrazo);
		WebElement getStatusValor = driver.findElement(statusValor);
		
		Assert.assertEquals("Transportadora", getEntrega.getText());
		Assert.assertEquals("11 dias úteis", getStatusPrazo.getText());
		Assert.assertEquals("R$ 31,82", getStatusValor.getText());
		
		System.out.println("Transportadora");
		System.out.println("Prazo 11 dias úteis");
		System.out.println("Valor Produto R$ 31,82");
		
	}

}
