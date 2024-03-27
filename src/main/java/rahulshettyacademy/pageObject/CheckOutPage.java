package rahulshettyacademy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css="a[class*='action__submit']")
	private WebElement placeOrder;
	
	@FindBy(css="[placeholder*='Country']")
	private WebElement Country;
	
	@FindBy(css=".ta-item")
	private List<WebElement> CountryOptions;
	
	By CountryOption=By.cssSelector(".ta-item");
	
	public void selectCountry(String country) {
		Country.sendKeys(country);
		waitForElementToAppear(CountryOption);
		CountryOptions.stream().filter(countryOption -> countryOption.getText().equalsIgnoreCase(country)).findFirst()
		.orElse(null).click();
		
	}
	public ConfirmationPage placeOrder() {
		placeOrder.click();
		
		return new ConfirmationPage(driver);
	}

	
}
