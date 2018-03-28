package com.madeiramadeira.webtests;

import java.io.FileReader;

import org.junit.experimental.categories.Category;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.madeiramadeira.pageObjects.AreaVipPage;
import com.madeiramadeira.pageObjects.CadastroPage;
import com.madeiramadeira.suites.SuiteTestes_Aceitacao;
import com.madeiramadeira.suites.SuiteTestes_Funcional;
import com.madeiramadeira.utils.Property;
import com.opencsv.CSVReader;

/**
 * @author ti-16 CLASSE PARA VALIDAR CADASTRO DE CLIENTES
 */

public class CadastroPageValidationTests extends BaseTestcase {

	CadastroPage cadastroPage = new CadastroPage();
	AreaVipPage areaVipPage = new AreaVipPage();
	String nomeCompleto;
	String cpf;
	String cnpj;

	@BeforeSuite
	public void before() throws Exception {
	}

	/**
	 * VALIDAR OBRIGATORIEDADE DO CAMPO E-MAIL
	 * @throws Exception
	 */
		
	@Test
	@Category(SuiteTestes_Funcional.class)
	public void testValidarObrigatoriedadeEmail() throws Exception {
		CSVReader reader;
		String[] line;
		String cpf;
		CSVReader csvReader = reader = new CSVReader(new FileReader(Property.CSV_CPF), ';');
		reader.readNext();
		while ((line = reader.readNext()) != null) {
			cpf = line[0];
			System.out.println("TC_validarObrigatoriedadeEmail");
			cadastroPage.acessarURL();
			cadastroPage.selecionarTipoPessoaFisica();
			cadastroPage.cadastrarCPF(cpf);
			cadastroPage.preencherNome("Cadastro_Pessoa Fisica");
			cadastroPage.preencherFoneContato("41991679999");
			cadastroPage.preencherSenha("madeirateste");
			cadastroPage.preencherCEP("80020320");
			cadastroPage.preencherNumeroEnd("717");
			areaVipPage = cadastroPage.clicarCadastrar();
			cadastroPage.validarErrorCadEmail();
		}
	}
	/**
	 * VALIDAR OBRIGATORIEDADE DO CAMPO CPF
	 * @throws Exception
	 */
	@Test
	@Category(SuiteTestes_Funcional.class)
	public void testValidarObrigatoriedadeCPF() {
		System.out.println("TC_validarObrigatoriedadeCPF");
		cadastroPage.acessarURL();
		cadastroPage.selecionarTipoPessoaFisica();
		cadastroPage.preencherEmailCadastro();
		cadastroPage.preencherNome("Cadastro_Pessoa Fisica");
		cadastroPage.preencherFoneContato("41991679999");
		cadastroPage.preencherSenha("madeirateste");
		cadastroPage.preencherCEP("80020320");
		cadastroPage.preencherNumeroEnd("717");
		areaVipPage = cadastroPage.clicarCadastrar();
		cadastroPage.validarErrorCadCPF();
	}
	/**
	 * VALIDAR OBRIGATORIEDADE DO CAMPO NOME
	 * @throws Exception
	 */

	@Test
	@Category(SuiteTestes_Funcional.class)
	public void testValidarObrigatoriedadeNome() throws Exception {
		CSVReader reader;
		String[] line;
		String cpf;
		CSVReader csvReader = reader = new CSVReader(new FileReader(Property.CSV_CPF), ';');
		reader.readNext();
		while ((line = reader.readNext()) != null) {
			cpf = line[0];
			System.out.println("TC_validarObrigatoriedadeNome");
			cadastroPage.acessarURL();
			cadastroPage.selecionarTipoPessoaFisica();
			cadastroPage.preencherEmailCadastro();
			cadastroPage.cadastrarCPF(cpf);
			cadastroPage.preencherNome("");
			cadastroPage.preencherFoneContato("41991679999");
			cadastroPage.preencherSenha("madeirateste");
			cadastroPage.preencherCEP("80020320");
			cadastroPage.preencherNumeroEnd("717");
			areaVipPage = cadastroPage.clicarCadastrar();
			cadastroPage.validarErrorCadNome();
		}
	}
	/**
	 * VALIDAR OBRIGATORIEDADE DO CAMPO CELULAR
	 * @throws Exception
	 */
	@Test
	@Category(SuiteTestes_Funcional.class)
	public void testValidarObrigatoriedadeCelular() throws Exception {
		CSVReader reader;
		String[] line;
		String cpf;
		CSVReader csvReader = reader = new CSVReader(new FileReader(Property.CSV_CPF), ';');
		reader.readNext();
		while ((line = reader.readNext()) != null) {
			cpf = line[0];
			System.out.println("TC_validarObrigatoriedadeCelular");
			cadastroPage.acessarURL();
			cadastroPage.selecionarTipoPessoaFisica();
			cadastroPage.preencherEmailCadastro();
			cadastroPage.cadastrarCPF(cpf);
			cadastroPage.preencherNome("Cadastro_Pessoa Fisica");
			cadastroPage.preencherFoneContato("");
			cadastroPage.preencherSenha("madeirateste");
			cadastroPage.preencherCEP("80020320");
			cadastroPage.preencherNumeroEnd("717");
			areaVipPage = cadastroPage.clicarCadastrar();
			cadastroPage.validarErrorCadCelular();
		}
	}
	/**
	 * VALIDAR OBRIGATORIEDADE DO CAMPO SENHA
	 * @throws Exception
	 */
	@Test
	@Category(SuiteTestes_Funcional.class)
	public void testValidarObrigatoriedadeSenha() throws Exception {
		CSVReader reader;
		String[] line;
		String cpf;
		CSVReader csvReader = reader = new CSVReader(new FileReader(Property.CSV_CPF), ';');
		reader.readNext();
		while ((line = reader.readNext()) != null) {
			cpf = line[0];
			System.out.println("TC_validarObrigatoriedadeSenha");
			cadastroPage.acessarURL();
			cadastroPage.selecionarTipoPessoaFisica();
			cadastroPage.preencherEmailCadastro();
			cadastroPage.cadastrarCPF(cpf);
			cadastroPage.preencherNome("Cadastro_Pessoa Fisica");
			cadastroPage.preencherFoneContato("41991679999");
			cadastroPage.preencherSenha("");
			cadastroPage.preencherCEP("80020320");
			cadastroPage.preencherNumeroEnd("717");
			areaVipPage = cadastroPage.clicarCadastrar();
			cadastroPage.validarErrorCadSenha();
		}
	}
	
	/**
	 * OBJETIVO: VALIDAR CADASTRO PESSOA FISICA COM SUCESSO
	 * @throws Exception
	 */

	@Test
	@Category(SuiteTestes_Aceitacao.class)
	public void testEfetuarCadastroPessoaFisica() throws Exception {
		CSVReader reader;
		String[] line;
		String cpf;
		CSVReader csvReader = reader = new CSVReader(new FileReader(Property.CSV_CPF), ';');
		reader.readNext();
		while ((line = reader.readNext()) != null) {
			cpf = line[0];
			cadastroPage.acessarURL();
			cadastroPage.selecionarTipoPessoaFisica();
			cadastroPage.preencherEmailCadastro();
			cadastroPage.cadastrarCPF(cpf);
			cadastroPage.preencherNome("Cadastro_Pessoa Fisica");
			cadastroPage.preencherFoneContato("41991679999");
			cadastroPage.preencherSenha("madeirateste");
			cadastroPage.preencherCEP("80020320");
			cadastroPage.preencherNumeroEnd("717");
			cadastroPage.prencherComplemento("3/4/5 andares");
			cadastroPage.preencherReferencia("CENTRO");
			areaVipPage = cadastroPage.clicarCadastrar();
			areaVipPage.clicarButtonSair();
			cadastroPage.preencherLoginPasswordPF();
			System.out.println("\n");		
		}
	}

	/**
	 * OBJETIVO: VALIDAR CADASTRO PESSOA JURIDICA COM SUCESSO
	 * 
	 * @throws Exception
	 */

	@Test
	@Category(SuiteTestes_Aceitacao.class)
	public void testEfetuarCadastroPessoaJuridica() throws Throwable {
		CSVReader reader;
		String[] line;
		String cnpj;
		CSVReader csvReader = reader = new CSVReader(new FileReader(Property.CSV_CNPJ), ';');
		reader.readNext();
		while ((line = reader.readNext()) != null) {
			cnpj = line[0];
			cadastroPage.acessarURL();
			cadastroPage.selecionarTipoPessoaJuridica();
			cadastroPage.preencherEmailCadastro();
			cadastroPage.cadastrarCNPJ(cnpj);
			cadastroPage.confereRazaoSocial();
			cadastroPage.confereInscricaoEstadual();
			cadastroPage.preencherFoneContato("41991679999");
			cadastroPage.preencherSenha("madeirateste");
			cadastroPage.confereCEP();
			cadastroPage.confereEndereco();
			cadastroPage.preencherReferencia("DETRAN");
			cadastroPage.scrollPageDown();
			areaVipPage = cadastroPage.clicarCadastrar();
			areaVipPage.clicarButtonSair();
			cadastroPage.preencherLoginPasswordPJ();
			System.out.println("\n");
		}
	}
}
