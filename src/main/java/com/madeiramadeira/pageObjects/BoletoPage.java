package com.madeiramadeira.pageObjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.DecimalFormat;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.madeiramadeira.utils.Selenium;

public class BoletoPage {
	/**
	 * Instância privada do webDriver que vira da suite de teste
	 */

	private static WebDriver driver;
	private static WebDriverWait wait;
	/**
	 * Construtor que ira adicionar a instância do WebDriver para utilizacao dos metodos
	 */
	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 8);
	}
	/**
	 * Definição única dos locators utilizados na página
	 * 	 
	 */

	static By getUser = By.xpath("//div[@id='alignContent']/div/div/div[2]/div/div[2]/h1/strong");
	static By getPedido = By.xpath("//div[@id='alignContent']/div/div/div[2]/div/div[2]/p/strong");
	static By getConferePedido = By.xpath("//div[@id='alignContent']/div/div[3]/div/div/h1/strong");
	static By getDtEfetuada = By.xpath("");
	static By getValorTotal = By.xpath("//div[@id='alignContent']/div/div[3]/div/div/div/span[2]/strong");
	static By getValorDadosPagamento = By.xpath("//div[@id='alignContent']/div/div[4]/div[2]/div/div[2]/table/tbody/tr/td[3]/table/tbody/tr[2]/td[3]");
	static By getStatus = By.xpath("//div[@id='alignContent']/div/div[3]/div/div/div/span[4]/strong");
	static By getFrete = By.xpath("//td[2]/table/tbody/tr[2]/td[3]");
	static By getValorCompra = By.xpath("//div[@id='alignContent']/div/div[7]/div/table/tbody/tr/td[4]");
	static By getDesconto = By.xpath("//div[@id='alignContent']/div/div[4]/div[2]/div/div[2]/table/tbody/tr/td/table/tbody/tr[2]/td[2]");
	static By buttonVisualizarBoleto = By.xpath("//a[contains(text(),'visualizar boleto')]");
	static By tituloBoleto = By.xpath("//h2");
	static By getPagadorBoleto = By.xpath("//tr[2]/td/div[2]");
	static By getValorBoleto = By.xpath("//tr[4]/td[3]/div[2]");
	static By getCodigoBarras = By.xpath("//span");
	static By getDtVencimentoBoleto = By.xpath("//table[2]/tbody/tr[2]/td[2]/div[2]");
	private double somaTotal;

	public BoletoPage() {
	}
	
	public void resumoPedido() {
	
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(getUser));
				WebElement recuperaUser = driver.findElement(getUser); 
				WebElement recuperaGetPedido = driver.findElement(getPedido);
				WebElement recuperaConferePedido = driver.findElement(getConferePedido);
				WebElement recuperaStatus = driver.findElement(getStatus);
				WebElement recuperaValorTotal = driver.findElement(getValorTotal);
//				WebElement recuperaDadosValor = driver.findElement(getValorDadosPagamento);
				WebElement recuperaFrete = driver.findElement(getFrete);
				WebElement recuperaValorPedido = driver.findElement(getValorCompra);
				WebElement recuperaDesconto = driver.findElement(getDesconto);
			
			/**
			 * Definindo variavéis para cada um dos objetos 
			 */
			String valorPedido = recuperaGetPedido.getText().trim();
			String Valorfrete = recuperaFrete.getText().trim().replace(",", ".");
			String valorCompra = recuperaValorPedido.getText().trim().replace(",", ".");
			String valorDesconto = recuperaDesconto.getText().trim().replace(",", ".");			
			String valorTotalPedido = recuperaValorTotal.getText().trim();
			
			if (!Valorfrete.isEmpty()) Valorfrete = Valorfrete.substring(3);
			if (!valorCompra.isEmpty()) valorCompra = valorCompra.substring(3);
			if (!valorPedido.isEmpty()) valorPedido = valorPedido.substring (1);
			if (valorDesconto.isEmpty()) valorDesconto = "0.00";
			float somafrete = Float.parseFloat(Valorfrete);
			float somaCompra = Float.parseFloat(valorCompra);
			float subDesconto = !valorDesconto.isEmpty()?Float.parseFloat(valorDesconto):0;
			somaTotal = somafrete + somaCompra - subDesconto;
			
			Assert.assertEquals("Obrigado, Usuário teste MM!", recuperaUser.getText());
				System.out.println("Usu�rio que efeutou a compra: "+recuperaUser.getText());
			
			Assert.assertEquals(valorPedido, recuperaConferePedido.getText());
				System.out.println("Número Pedido Gerado: "+valorPedido);
			
			Assert.assertEquals("Realizado", recuperaStatus.getText());
				System.out.println("Status Pedido: "+recuperaStatus.getText());
			
				Assert.assertEquals(new DecimalFormat("#.00").format(somaTotal), valorTotalPedido);
				System.out.println("Valor Total Frete R$: "+somafrete);
				System.out.println("Valor Total Compra R$: "+somaCompra);
				System.out.println("Valor Total Desconto R$: "+subDesconto);
				System.out.println("Valor Total Soma Pedido R$:" +new DecimalFormat("#.00").format(somaTotal));
			
		} catch (AssertionError e) {
			
			System.out.println(e.getMessage()); 
		}
		
	}
	
	public void visualizarBoleto() {
		
		String windowHandleJanelaInicial = driver.getWindowHandle();
		System.out.println("Janela Inicial: "+windowHandleJanelaInicial);
		
		WebElement elementoQueAbreNovaJanela  = driver.findElement(buttonVisualizarBoleto);
		elementoQueAbreNovaJanela.click();
		System.out.println("Clicado no botão Visualizar Boleto");
		
		Set<String> windowHandles = driver.getWindowHandles();
		for(String windowHandle : windowHandles){
			if (!windowHandle.equals(windowHandleJanelaInicial)) {
				driver.switchTo().window(windowHandle);				
				System.out.println("Janela Atual:" +windowHandle);
				System.out.println("URL do Boleto Gerado: "+driver.getCurrentUrl());
				
			}				
		}
		
	}
	
	public void capturaDadosBoleto() {
			wait.until(ExpectedConditions.visibilityOfElementLocated(tituloBoleto));
			WebElement pagadorBoleto = driver.findElement(getPagadorBoleto);
			WebElement valorBoleto = driver.findElement(getValorBoleto);			
			
			assertThat("O Titulo da Página não está correto!",  driver.getTitle(), is("madeiramadeira.com.br")); 
			System.out.println("Titulo da Página Boleto: "+driver.getTitle());			
			
			assertThat("Pagador Inválido no Boleto!", pagadorBoleto.getText(), is("Usu�rio teste MM")); 
			System.out.println("Pagador do Boleto: "+pagadorBoleto.getText());
			
			assertThat("Valor exibido no boleto está diferente do Resumo do Pedido!", new DecimalFormat("#.00").format(somaTotal), is(valorBoleto.getText()));
			System.out.println("Validar o Valor do Boleto: "+valorBoleto.getText());			
			driver.close();
			
//		} catch (AssertionError e) {
//
//			System.out.println("Erro ao Executar o Teste capturaDadosBoleto: " +e.getMessage());		
//		}
		
	}
}
