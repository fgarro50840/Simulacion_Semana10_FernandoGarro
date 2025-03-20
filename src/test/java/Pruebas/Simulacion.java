package Pruebas;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Simulacion {
	
	private WebDriver driver;
	
	@Before
	public void setUp() {
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=");
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Driver/chromedriver.exe");
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/v1/");
	}
	
	@Test
	public void simulacionPrueba1() {
		
	}

}
