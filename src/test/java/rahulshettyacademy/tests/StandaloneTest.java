package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {
	public static void main(String[] args) throws InterruptedException {
//		WebDriverManager.chromedriver().setup();
		String productName = "ZARA COAT 3";
		String Country = "India";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("rsk@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("RSKathar@11");
		driver.findElement(By.id("login")).click();
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(5));
		ww.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		ww.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		ww.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		WebElement button=driver.findElement(By.cssSelector(".totalRow button"));
		button.sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector("[placeholder*='Country']")).sendKeys(Country);
		ww.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		List<WebElement> countryOptions = driver.findElements(By.cssSelector(".ta-item"));
		countryOptions.stream().filter(countryOption -> countryOption.getText().equalsIgnoreCase(Country)).findFirst()
				.orElse(null).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		driver.findElement(By.tagName("h1")).getText().equalsIgnoreCase(" Thankyou for the order. ");
		driver.close();
	}

}
