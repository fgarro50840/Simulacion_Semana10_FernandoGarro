package Pruebas;

import static org.junit.Assert.assertEquals;
import java.time.Duration;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Simulacion {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@Before
	public void setUp() {		
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=");
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Driver/chromedriver.exe");
		driver = new ChromeDriver(co);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/v1/");	
	}
	
	//Prueba de inicio de sesion
	@Test
	public void prueba1() {			
		WebElement txtUsername = driver.findElement(By.id("user-name"));
		WebElement txtPassword = driver.findElement(By.id("password"));
		WebElement btnLogin = driver.findElement(By.id("login-button"));
		if(txtUsername.isDisplayed() && txtUsername.isEnabled()) {
			txtUsername.sendKeys("standard_user");
		}		
		if(txtPassword.isDisplayed() && txtPassword.isEnabled()) {
			txtPassword.sendKeys("secret_sauce");
		}		
		btnLogin.click();		
		WebElement lblProducto = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[1]/div[3]/div"));
		String txtProducto = lblProducto.getText();
		assertEquals("Prueba de label Producto", "Products", txtProducto);	
		System.out.println("Prueba 1: Inicio de sesión correcto.");
	}
	
	
	
}
