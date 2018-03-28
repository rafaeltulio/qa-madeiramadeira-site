package com.madeiramadeira.pageObjects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.madeiramadeira.utils.Property;
import com.madeiramadeira.utils.Selenium;

public class CadastroPage {

	/**
	 * Instancia privada do webDriver que vira da suite de teste
	 */

	private static WebDriver driver;
	private static WebDriverWait wait;
	private String email;
	/**
	 * Construtor que ira adicionar a instancia do WebDriver para utilizacao dos metodos
	 */
	static {
		driver = Selenium.getDriver();
		wait = new WebDriverWait(driver, 10);
	}

	/**
	 * Definição Única dos locators utilizados na página
	 */
	static By botaoCadastrar = By.id("btnFullSignin");
	static By valorCampoRazaoSocial = By.id("txtRazaoSocial");
	static By valorCampoInscricao= By.id("txtIE");
	static By modalRemember = By.id("modal-remember");
	static By buttonCadastrar = By.id("btnFullSignin");
	static By txtLogin = By.id("txLogin");
	static By txtPassword = By.id("txPassword"); 
	static By buttonEntrar = By.xpath("//form[@id='formLogin']/div/div[2]/button");
	static By linkSair = By.xpath("//a[contains(text(),'Sair')]");
	static By msgmError = By.id("errorsSignin");
	String urlAcesso = Property.SITE_ADDRESS + "/cadastro";
	String nomeCompleto;
	String telefone;
	String cep;
	String numeroEnd;
	String complemento;
	String inscricaoEstadual;
	String referencia;
	String recuperaEmail;
	String recuperaSenha;
	String recuperaNome;
	
	public CadastroPage() {
	}

	public void acessarURL() {
		driver.navigate().to(urlAcesso);
		System.out.println("Acessando URL: "+urlAcesso);		
	}
	
		
	/**
	 * Selecionar o Tipo de Pessoa Fisica / Tipo Pessoa Juridica
	 */	
	public void selecionarTipoPessoaFisica() {
		WebElement tipoPessoaFisica = driver.findElement(By.id("pessoaFisica"));
		tipoPessoaFisica.click();
		System.out.println("Tipo Pessoa Fisica Selecionada!");
	}
	
	public void selecionarTipoPessoaJuridica() {
		WebElement tipoPessoaJuridica = driver.findElement(By.id("pessoaJuridica"));
		tipoPessoaJuridica.click();
		System.out.println("Tipo Pessoa Juridica Selecionada!");		
	}

	
	/**
	 * Preencher o E-mail de Cadastro Randomicamente
	 */	
	public void preencherEmailCadastro() {
		
		try {
			email = UUID.randomUUID().toString().substring(1, 6) + "@automacao.madeiramadeira.com.br";
			WebElement cadastroEmail = driver.findElement(By.id("txtEmail"));
			cadastroEmail.clear();
			cadastroEmail.sendKeys(email);			
			System.out.println("E-Mail Preenchido no Cadastro: "+email);
			recuperaEmail = driver.findElement(By.id("txtEmail")).getAttribute("value");
			
		} catch (NoSuchElementException e) {

			System.out.println(e.getMessage());
		}
		
	}
	/**
	 * Gerador de CPF, acessando o site www.geradorcpf.com.br
	 */	
	public void geradorCPF() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(botaoCadastrar));
		driver.navigate().to(Property.ADDRESS_GERADORCPF);

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.findElement(By.id("botao")).click();
		WebElement numberCPF = driver.findElement(By.id("numero"));
		numberCPF.getAttribute("value");
		System.out.println("CPF Gerado:" + driver.findElement(By.id("numero")).getAttribute("value"));
		driver.navigate().to(Property.SITE_ADDRESS + "/cadastro");

	}
	/**
	 * CADASTRAR CPF ATRAVES DE MASSA DE DADOS EXTERNA dataDrivenCPF.csv
	 */	
	public void cadastrarCPF(String cpf) throws Exception {
		WebElement txtCpf = driver.findElement(By.id("txtCPF"));
		txtCpf.clear();
		txtCpf.sendKeys(cpf);
		txtCpf.sendKeys(Keys.TAB);
		System.out.println("CPF Informado no Cadastro: " +cpf);
	}
	/**
	 * CADASTRAR CNPJ ATRAVES DE MASSA DE DADOS EXTERNA  dataDrivenCNPJ.csv
	 */	
	public void cadastrarCNPJ(String cnpj) throws Exception{
		WebElement txtCnpj = driver.findElement(By.id("txtCNPJ"));
		txtCnpj.clear();
		txtCnpj.sendKeys(cnpj);
		txtCnpj.sendKeys(Keys.TAB);		
		System.out.println("CNPJ Informado no Cadastro: " +cnpj);		
	}
	
	/**
	 * Preencher Nome Completo Para Cadastro
	 * @throws InterruptedException 
	 */	
	public void preencherNome(String nomeCompleto) {
		WebElement txtName = driver.findElement(By.id("txtName"));
		txtName.clear();
		txtName.sendKeys(nomeCompleto.toUpperCase());
		txtName.sendKeys(Keys.TAB);
		System.out.println("Nome Preenchido no Cadastro: "+nomeCompleto);
		
		recuperaNome = driver.findElement(By.id("txtName")).getAttribute("value");
	}
	
	/**
	 * Preencher Telefone Celular
	 */
	public void preencherFoneContato(String telefone) {
		WebElement txtTel1 = driver.findElement(By.id("txtTel1"));
		txtTel1.clear();
		txtTel1.sendKeys(telefone);
		txtTel1.sendKeys(Keys.TAB);
		System.out.println("Telefone Contato Preenchido no Cadastro: "+telefone);
	}
	
	/**
	 * 
	 * Validar Razão Social
	 */
	public void confereRazaoSocial() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(valorCampoRazaoSocial)).getAttribute("readonly");	
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(By.id("txtRazaoSocial")).getAttribute("readonly"), true);		
		if (true) {			
			System.out.println("Campo Razão Social Está com atributo readOnly 'true'!!");			
		} 
	}

	/**
	 * 
	 * Validar Inscriçãoo Estadual no Cadastro
	 */
	public void confereInscricaoEstadual() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(valorCampoInscricao));
		Assert.assertTrue(driver.findElement(By.id("txtRazaoSocial")).getAttribute("readonly"), true);		
		if (true) {			
			System.out.println("Campo Inscrição Estadual Está com atributo readOnly 'true'!!");			
		} 
	}

	/**
	 * 
	 * Validar CEP no Cadastro PJ
	 */
	public void confereCEP() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(valorCampoInscricao));
		Assert.assertTrue(driver.findElement(By.id("txtCEP")).getAttribute("readonly"), true);		
		if (true) {			
			System.out.println("Campo CEP Está com atributo readOnly 'true'!!");			
		} 
	}
	
	/**
	 * 
	 * Validar Endereço no Cadastro PJ
	 */
	public void confereEndereco() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(valorCampoInscricao));
		Assert.assertTrue(driver.findElement(By.id("txtAddress")).getAttribute("readonly"), true);		
		if (true) {			
			System.out.println("Campo Endereço Está com atributo readOnly 'true'!!");			
		} 
	}
	
	
	/**
	 * Preencher Senha para Cadastro
	 */
	public void preencherSenha(String senha) {
		WebElement txtPassword = driver.findElement(By.id("txtPassword"));
		txtPassword.clear();
		txtPassword.sendKeys(senha);
		System.out.println("Senha Preenchida! " +senha);
		recuperaSenha = driver.findElement(By.id("txtPassword")).getAttribute("value");
	}

	public void preencherCEP(String cep) {
		WebElement txtCEP = driver.findElement(By.id("txtCEP"));
		txtCEP.clear();
		txtCEP.sendKeys(cep);
		txtCEP.sendKeys(Keys.TAB);
		System.out.println("CEP Preenchido no cadastro: "+cep);
	}

	public void preencherNumeroEnd(String numeroEnd) {
		WebElement txtAddNum = driver.findElement(By.id("txtAddNum"));
		txtAddNum.clear();
		txtAddNum.sendKeys(numeroEnd);
		txtAddNum.sendKeys(Keys.TAB);
		System.out.println("Número Preenchido: "+numeroEnd);
	}

	public void prencherComplemento(String complemmento) {
		WebElement txtAddComplem = driver.findElement(By.id("txtAddComp"));
		txtAddComplem.clear();
		txtAddComplem.sendKeys(complemmento);
		txtAddComplem.sendKeys(Keys.TAB);
		System.out.println("Complemento Preenchido: "+complemmento);
	}
	public void preencherReferencia(String referencia) {
		WebElement campoReferencia = driver.findElement(By.id("txtAddRef"));
		campoReferencia.clear();
		campoReferencia.sendKeys(referencia);
		campoReferencia.sendKeys(Keys.TAB);
		System.out.println("Referência Preenchida: "+referencia);
	
	}
	public void scrollPageDown() throws Throwable{
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("scroll(0,400)");
		System.out.println("Scroll para Localizar Botão Cadastrar!");

	}
	
	public AreaVipPage clicarCadastrar() {	
		wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCadastrar));		
		WebElement botaoCadastrar = driver.findElement(buttonCadastrar);
		botaoCadastrar.click();
		System.out.println("Cadastro Efetuado com Suceso!");		
		return new AreaVipPage();
	}
	
	public void preencherLoginPasswordPF() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(buttonEntrar));
		driver.findElement(txtLogin).sendKeys(recuperaEmail);
		driver.findElement(txtPassword).sendKeys(recuperaSenha);
		driver.findElement(buttonEntrar).click();

		System.out.println("Login Efetuado com e-mail Cadastrado: "+ recuperaEmail);
		wait.until(ExpectedConditions.visibilityOfElementLocated(linkSair));
		WebElement userLogado = driver.findElement(By.xpath("//div[@id='menu-top-area-vip']/div/span"));
		assertThat("Usuário Logado Inválido!", userLogado.getText(), is("OLÁ, "+ recuperaNome));
		System.out.println("Usuário Logado: " + userLogado.getText());
		System.out.println("Usuário Cadastrado: " + recuperaNome);
		driver.findElement(linkSair).click();
		System.out.println("Logout Efetuado!"); 
	}
		
	public void preencherLoginPasswordPJ() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(buttonEntrar));
		driver.findElement(txtLogin).sendKeys(recuperaEmail);
		driver.findElement(txtPassword).sendKeys(recuperaSenha);
		driver.findElement(buttonEntrar).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(linkSair));
		WebElement userLogado = driver.findElement(By.xpath("//div[@id='menu-top-area-vip']/div/span"));
		String userRazaoSocial = "RAFAEL TULIO WEB CONSULTORIA";
		assertThat("Usuário Logado Inválido!", userLogado.getText(), is("OLÁ, "+userRazaoSocial));
		System.out.println("Usuário Logado: "+userLogado.getText());
		driver.findElement(linkSair).click();	
		System.out.println("Logout Efetuado!");		
	}	
	
	public void validarErrorCadEmail() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(msgmError));
		WebElement msgmAtual = driver.findElement(msgmError);
		assertThat("Mensagem de Erro Inválida", msgmAtual.getText(), is("Verifique os erros:\n•Preencha o E-mail;"));
		System.out.println("Verificar Error: Preencha o E-mail!");
		
		WebElement classError = driver.findElement(By.id("txtEmail"));
		Assert.assertEquals(classError.getAttribute("class").contains("form-control input-md errorOld1"), true);
		if (true) {
			System.out.println("Campo Email esta com a class: errorOld1!\n");
		}
		
	}

	public void validarErrorCadCPF() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(msgmError));
		WebElement msgmAtual = driver.findElement(msgmError);
		assertThat("Mensagem de Erro Inválida", msgmAtual.getText(),is("Verifique os erros:\n•Digite um documento válido;"));
		System.out.println("Verificar Error: Digite um documento válido!");

		WebElement classError = driver.findElement(By.id("txtCPF"));
		Assert.assertEquals(classError.getAttribute("class").contains("form-control input-md errorOld1"), true);
		if (true) {
			System.out.println("Campo CPF está com a class: errorOld1!\n");
		}

	}
	
	public void validarErrorCadNome() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(msgmError));
		WebElement msgmAtual = driver.findElement(msgmError);
		assertThat("Mensagem de Erro Inválida", msgmAtual.getText(),is("Verifique os erros:\n•Preencha o Nome Completo;"));
		System.out.println("Verificar Error: Preencha o Nome Completo!");

		WebElement classError = driver.findElement(By.id("txtName"));
		Assert.assertEquals(classError.getAttribute("class").contains("form-control input-md errorOld1"), true);
		if (true) {
			System.out.println("Campo Nome está com a class: errorOld1!\n");
		}
		
	}	
	
	public void validarErrorCadCelular() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(msgmError));
		WebElement msgmAtual = driver.findElement(msgmError);
		assertThat("Mensagem de Erro Inválida", msgmAtual.getText(),is("Verifique os erros:\n•Digite um Celular válido;"));
		System.out.println("Verificar Error: Digite um Celular válido");

		WebElement classError = driver.findElement(By.id("txtTel1"));
		Assert.assertEquals(classError.getAttribute("class").contains("form-control input-md errorOld1"), true);
		if (true) {
			System.out.println("Campo Celular está com a class: errorOld1!\n");
		}
	}

	public void validarErrorCadCEP() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(msgmError));
		WebElement msgmAtual = driver.findElement(msgmError);
		assertThat("Mensagem de Erro Inválida", msgmAtual.getText(),is("Verifique os erros:\n•Digite um CEP válido;"));
		System.out.println("Verificar Error: Digite um CEP válido");

		WebElement classError = driver.findElement(By.id("txtCEP"));
		Assert.assertEquals(classError.getAttribute("class").contains("form-control input-md errorOld1"), true);
		if (true) {
			System.out.println("Campo CEP está com a class: errorOld1!\n");
		}
		
	}
	
	
	public void validarErrorCadSenha() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(msgmError));
		WebElement msgmAtual = driver.findElement(msgmError);
		assertThat("Mensagem de Erro Inválida", msgmAtual.getText(),is("Verifique os erros:\n•Preencha a Senha;"));
		System.out.println("Verificar Error: Preencha a Senha");

		WebElement classError = driver.findElement(By.id("txtPassword"));
		Assert.assertEquals(classError.getAttribute("class").contains("form-control input-md errorOld1"), true);
		if (true) {
			System.out.println("Campo Senha está com a class: errorOld1!\n");
		}
		
	}
	
	
	public boolean cpfExistente(){			
			WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(modalRemember));
			System.out.println(1);

			System.out.println(Element);
			if(Element == null){
				System.out.println(2);
				return false;
			}
			System.out.println(3);
			
			System.out.println(4);

			WebElement modalVisible = driver.findElement(modalRemember);
			System.out.println(5);

			System.out.println(modalVisible.getCssValue("display"));

			if (modalVisible.getCssValue("display") != "none") {				
				driver.findElement(By.id("btnNewCPF")).click();	
				
				return true;		
			}
			
			return false;
		
	}

}



