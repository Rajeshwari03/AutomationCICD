package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.pageObject.CartPage;
import rahulshettyacademy.pageObject.CheckOutPage;
import rahulshettyacademy.pageObject.ConfirmationPage;
import rahulshettyacademy.pageObject.ProductCatalogue;
import rahulshettyacademy.testComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {

	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = rahulshettyacademy.testComponents.Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		lp.loginApplication("rsk@gmail.com", "RSKathar@111");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMsg());
		

	}
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue pc = lp.loginApplication("rsk@gmail.com", "RSKathar@11");
		List<WebElement> products = pc.getProductsList();
		pc.addProductToCart(productName);
		CartPage cp = pc.goToCart();
		Boolean match = cp.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
