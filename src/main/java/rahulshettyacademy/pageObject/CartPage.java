package rahulshettyacademy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement userName=driver.findElement(By.id("userEmail"));
	
	//PageFactory 
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	public CheckOutPage gotoCheckout() {
		checkoutButton.sendKeys(Keys.ENTER);
		return new CheckOutPage(driver);
		
	}
	
}
