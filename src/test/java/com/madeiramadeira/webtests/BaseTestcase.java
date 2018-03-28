/**
 * 
 */
package com.madeiramadeira.webtests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import com.madeiramadeira.pageObjects.HomePage;
import com.madeiramadeira.suites.SuiteTestes_Aceitacao;
import com.madeiramadeira.suites.SuiteTestes_Funcional;
import com.madeiramadeira.utils.Selenium;
import com.madeiramadeira.utils.dbconnection;

/**
 * CLASSE BASE DE TESTES, QUE INICIALIZA O DRIVER NO INICIO DOS TESTES E ENCERRA
 * O DRIVER AO FINAL DA EXECUÇÃO.
 * 
 * @author RafaelTulio
 * 
 */

public class BaseTestcase {
	protected static HomePage homePage;
	protected static WebDriver driver;
	static long startTime;
	static long endTime;
	static long totalTime;

	@BeforeClass
	public static void beforeClass() throws Exception {		
		startTime = System.currentTimeMillis();		
		if (!SuiteTestes_Funcional.isSuiteTestExecution && !SuiteTestes_Aceitacao.isSuiteTestExecution) 
		{
			driver = Selenium.getDriver();
			driver.manage().window().maximize();
			dbconnection db = new dbconnection();
			db.clean();
			System.out.println("Chrome Driver Iniciado!");
		}
	}

	@AfterClass
	public static void afterClass() throws Exception {
		dbconnection db = new dbconnection();
		db.clean();
		if (!SuiteTestes_Funcional.isSuiteTestExecution && !SuiteTestes_Aceitacao.isSuiteTestExecution)
		{
			driver.quit();
			endTime = System.currentTimeMillis();
			totalTime = endTime - startTime;
			System.out.println("Tempo de Execução Suite Teste: " + totalTime+ "Segundos");
			System.out.println("Chrome Driver Finalizado!\n");
		}
	}

}
