package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.pageObject.CartPage;
import rahulshettyacademy.pageObject.CheckOutPage;
import rahulshettyacademy.pageObject.ConfirmationPage;
import rahulshettyacademy.pageObject.LandingPage;
import rahulshettyacademy.pageObject.ProductCatalogue;
import rahulshettyacademy.testComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage lp;
	public ProductCatalogue pc;
	public ConfirmationPage cpp;
	public CartPage cp;
	public CheckOutPage cop;
	String Country = "India";

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_ecommerce_Page() throws IOException {
		lp = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		pc = lp.loginApplication(username, password);
	}

	@When("^I add the product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = pc.getProductsList();
		pc.addProductToCart(productName);
	}

	@When("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		cp = pc.goToCart();
		Boolean match = cp.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		cop = cp.gotoCheckout();
		cop.selectCountry(Country);
		cpp = cop.placeOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string) {
		Assert.assertTrue(cpp.getConfirmationMessage().equalsIgnoreCase(string));
		driver.close();
	}
	@Then("{string} message is displayed")
	public void message_displayed(String string) {
		Assert.assertEquals(string, lp.getErrorMsg());
		driver.close();
	}
	

}
