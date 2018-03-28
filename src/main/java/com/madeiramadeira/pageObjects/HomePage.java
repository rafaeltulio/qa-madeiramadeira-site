package com.madeiramadeira.pageObjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.madeiramadeira.utils.Property;
import com.madeiramadeira.utils.Selenium;


/**
 * Página que disponibiliza os serviçoss já existentes na homepage do site
 * MADEIRAMADEIRA.COM.BR
 * 
 * @author ti-16 
 * 
 */
public class HomePage {

	/**
	 * Instância privada do webDriver que vira da suite de teste
	 */

	private static WebDriver driver;
	private static WebDriverWait wait;	
	/**
	 * Construtor que ira adicionar a Instância do WebDriver para utilizacao dos metodos
	 */
	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 10);
	}
	/**
	 * Definição única dos locators utilizados na página
	 */
	static By carrinhoMenuItem = By.linkText("Carrinho");
	static By facaLogin = By.xpath("//a[contains(text(),'Fa�a seu Login')]");
	static By cadastreSe = By.xpath("//a[contains(text(),'Cadastre-se')]");
	static By minhaConta = By.xpath("//a[contains(text(),'Minha Conta')]");
	static By txtLogin = By.id("txLogin");
	static By txtPassword = By.id("txPassword"); 
	static By buttonEntrar = By.xpath("//form[@id='formLogin']/div/div[2]/button");
	static By linkSair = By.xpath("//a[contains(text(),'Sair')]");
	static By titleLinkcorrect = By.xpath("/html/body/div[2]/header/div[2]/div/div/div/div/span[2]/a");
	String urlAcessoDefault = Property.SITE_ADDRESS;

	public HomePage() {
	}

	/**
	 * Clicar no link Carrinho do menu
	 */

	public void clicarHomeMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(carrinhoMenuItem));
		driver.findElement(carrinhoMenuItem).click();

	}

	/**
	 * Cllicar no menu Façaa Login
	 */

	public static void clicarFacaLogin() {
		driver.findElement(facaLogin).click();
	}

	public static void preencherLoginPassword() {
		driver.findElement(txtLogin).sendKeys("teste@madeiramadeira.com.br");
		driver.findElement(txtPassword).sendKeys("teste");
		driver.findElement(buttonEntrar).click();
	}

	public static void redirectURL() {
		driver.navigate().to("https://www.madeiramadeira.com.br");
	}

	public static void isLinkCorrect(String expectedTitle) {
		assertThat("Link Incorreto: " + titleLinkcorrect, driver.findElement(titleLinkcorrect).getText(), is(expectedTitle));
	}

	public static CadastroPage clicarCadastrar() {		
		WebElement linkCadastrar = driver.findElement(cadastreSe);
		linkCadastrar.click();
		System.out.println("Clicar no Lilnk Cadastre-Se!");
		return new CadastroPage();
	}

	public void minhaConta() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(minhaConta));
		WebElement linkMinhaConta = driver.findElement(minhaConta);
		linkMinhaConta.click();
	}

	public static ResultProdutoPage preencherFiltroPesquisa(String identificador) {
		WebElement campoBusca = driver.findElement(By.id("neemu-widget-input-box"));
		campoBusca.clear();
		campoBusca.sendKeys(identificador);
		campoBusca.sendKeys(Keys.ENTER);
		System.out.println("Preencher campo de Pesquisa com Identificador do Produto! "+identificador);		
		return new ResultProdutoPage();
	}

	public void navegarURLProduto() {
		driver.navigate().to(urlAcessoDefault+"/painel-para-tv-capri-linea-brasil-146874.html");
		System.out.println("Acessando URL: "+urlAcessoDefault);			
	}
	
}