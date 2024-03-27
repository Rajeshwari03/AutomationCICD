package rahulshettyacademy.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement userName=driver.findElement(By.id("userEmail"));
	//PageFactory 
	
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	public ProductCatalogue loginApplication(String email, String password) {
		userName.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalogue pc=new ProductCatalogue(driver);
		return pc;
	}
	public String getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
		
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
