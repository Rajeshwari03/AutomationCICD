package rahulshettyacademy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement userName=driver.findElement(By.id("userEmail"));
	//PageFactory 
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;

	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductsList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByNames(String productName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod=getProductByNames(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisAppear(spinner);
		
	}
	
}
