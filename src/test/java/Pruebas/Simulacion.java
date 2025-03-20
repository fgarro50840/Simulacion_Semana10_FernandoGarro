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

	// Prueba de inicio de sesion
	@Test
	public void prueba1() {
		WebElement txtUsername = driver.findElement(By.id("user-name"));
		WebElement txtPassword = driver.findElement(By.id("password"));
		WebElement btnLogin = driver.findElement(By.id("login-button"));
		if (txtUsername.isDisplayed() && txtUsername.isEnabled()) {
			txtUsername.sendKeys("standard_user");
		}
		if (txtPassword.isDisplayed() && txtPassword.isEnabled()) {
			txtPassword.sendKeys("secret_sauce");
		}
		btnLogin.click();
		WebElement lblProducto = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[1]/div[3]/div"));
		String txtProducto = lblProducto.getText();
		assertEquals("Prueba de label Producto", "Products", txtProducto);
		System.out.println("Prueba 1: Inicio de sesión correcto.");
	}

	// Prueba de cerrado sesion
	@Test
	public void prueba2() {
		WebElement txtUsername = driver.findElement(By.id("user-name"));
		WebElement txtPassword = driver.findElement(By.id("password"));
		WebElement btnLogin = driver.findElement(By.id("login-button"));
		txtUsername.sendKeys("standard_user");
		txtPassword.sendKeys("secret_sauce");
		btnLogin.click();
		WebElement btnMenu = driver.findElement(By.xpath("/html/body/div/div[1]/div/div[3]/div/button"));
		btnMenu.click();
		// Se utiliza el wait para asegurar que el boton ya se encuentra disponible para
		// ser utilizado
		WebElement btnLogout = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
		btnLogout.click();
		WebElement lblUsersInfo = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/h4"));
		String txtUsersInfo = lblUsersInfo.getText();
		assertEquals("Prueba de label Informacion de usuarios", "Accepted usernames are:", txtUsersInfo);
		System.out.println("Prueba 2: Cerrado de sesión correcto.");
	}

	// Prueba de orden de elementos
	@Test
	public void prueba3() {
		WebElement txtUsername = driver.findElement(By.id("user-name"));
		WebElement txtPassword = driver.findElement(By.id("password"));
		WebElement btnLogin = driver.findElement(By.id("login-button"));
		txtUsername.sendKeys("standard_user");
		txtPassword.sendKeys("secret_sauce");
		btnLogin.click();
		WebElement selectSortBy = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[1]/div[3]/select"));
		selectSortBy.click();
		WebElement selectSortByPrice = driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[1]/div[3]/select/option[3]"));
		selectSortByPrice.click();
		WebElement lblLowestPrice = driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div[1]/div[3]/div"));
		String txtLowestPrice = lblLowestPrice.getText();
		assertEquals("Prueba de label Precio menor", "$7.99", txtLowestPrice);
		WebElement lblHiguestPrice = driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div[6]/div[3]/div"));
		String txtHiguestPrice = lblHiguestPrice.getText();
		assertEquals("Prueba de label Precio mayor", "$49.99", txtHiguestPrice);
		System.out.println("Prueba 3: Ordena correctamente los elementos por precio en orden ascendente.");
	}

	// Prueba de agregar elementos al carrito
	@Test
	public void prueba4() {
		WebElement txtUsername = driver.findElement(By.id("user-name"));
		WebElement txtPassword = driver.findElement(By.id("password"));
		WebElement btnLogin = driver.findElement(By.id("login-button"));
		txtUsername.sendKeys("standard_user");
		txtPassword.sendKeys("secret_sauce");
		btnLogin.click();
		WebElement btnAddToCart1 = driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div[1]/div[3]/button"));
		btnAddToCart1.click();
		WebElement btnAddToCart2 = driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div[4]/div[3]/button"));
		btnAddToCart2.click();
		WebElement btnCart = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/a/span"));
		btnCart.click();
		WebElement lblFisrtAddedElement = driver
				.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div[1]/div[3]/div[2]/a/div"));
		String txtFisrtAddedElement = lblFisrtAddedElement.getText();
		assertEquals("Prueba de primer elemento agregado al carrito", "Sauce Labs Backpack", txtFisrtAddedElement);
		WebElement lblSecondAddedElement = driver
				.findElement(By.xpath("/html/body/div/div[2]/div[3]/div/div[1]/div[4]/div[2]/a/div"));
		String txtSecondAddedElement = lblSecondAddedElement.getText();
		assertEquals("Prueba de segundo elemento agregado al carrito", "Sauce Labs Fleece Jacket",
				txtSecondAddedElement);
		System.out.println("Prueba 4: Agrega correctamente los elementos seleccionados al carrito.");
	}

}
