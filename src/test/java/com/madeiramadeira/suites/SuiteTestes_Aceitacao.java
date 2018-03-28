package com.madeiramadeira.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.WebDriver;

import com.madeiramadeira.utils.Property;
import com.madeiramadeira.utils.Selenium;
import com.madeiramadeira.utils.dbconnection;

@RunWith(Categories.class)
@IncludeCategory(SuiteTestes_Aceitacao.class)
@SuiteClasses({})

public class SuiteTestes_Aceitacao {
	protected static WebDriver driver;
	public static Boolean isSuiteTestExecution = false;

	@BeforeClass
	public static void beforeClass() throws Exception {
		isSuiteTestExecution = true;
		driver = Selenium.getDriver();
		driver.navigate().to(Property.SITE_ADDRESS);
		driver.manage().window().maximize();
		dbconnection db = new dbconnection();
		db.clean();
		System.out.println("Chrome Driver Iniciado Para Teste Negativos!");
	}

	@AfterClass
	public static void afterClass() throws Exception {
		driver.quit();
		System.out.println("Driver Finalizado! Suuite Testes Negativos");
	}

}
