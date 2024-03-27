package rahulshettyacademy.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="td:nth-child(3)")
	List<WebElement> OrderedProducts;
	
	public Boolean verifyOrderDisplay(String productName) {
		return OrderedProducts.stream().anyMatch(countryOption -> countryOption.getText().equalsIgnoreCase(productName));
	}

}
