package rahulshettyacademy.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageObject.CartPage;
import rahulshettyacademy.pageObject.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement myOrder;
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(5));
		ww.until(ExpectedConditions.visibilityOf(findBy));
	}
	

	public void waitForElementToAppear(By findBy) {
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(5));
		ww.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisAppear(WebElement element) throws InterruptedException {
		Thread.sleep(2000);
//		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(5));
//		ww.until(ExpectedConditions.invisibilityOf(element));
	}
	public CartPage goToCart() {
		cart.click();
		CartPage cp=new CartPage(driver);
		return cp;
	}
	public OrderPage goToOrderPage() {
		myOrder.click();
		OrderPage op=new OrderPage(driver);
		return op;
	}
	
}
