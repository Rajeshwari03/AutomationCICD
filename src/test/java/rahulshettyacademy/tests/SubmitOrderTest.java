package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageObject.CartPage;
import rahulshettyacademy.pageObject.CheckOutPage;
import rahulshettyacademy.pageObject.ConfirmationPage;
import rahulshettyacademy.pageObject.LandingPage;
import rahulshettyacademy.pageObject.OrderPage;
import rahulshettyacademy.pageObject.ProductCatalogue;
import rahulshettyacademy.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
		
		String Country = "India";
		ProductCatalogue pc = lp.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = pc.getProductsList();
		pc.addProductToCart(input.get("productName"));
		CartPage cp = pc.goToCart();
		Boolean match = cp.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage cop = cp.gotoCheckout();
		cop.selectCountry(Country);
		ConfirmationPage cpp = cop.placeOrder();
		
		Assert.assertTrue(cpp.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTesst() {
		ProductCatalogue pc = lp.loginApplication("rsk@gmail.com", "RSKathar@11");
		OrderPage op=pc.goToOrderPage();
		Assert.assertTrue(op.verifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//purchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
//	@DataProvider
//	public Object[][] getData() {
//	HashMap<String, String> map=new HashMap<String, String>();
//	map.put("email", "rsk@gmail.com");
//	map.put("password", "RSKathar@11");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String, String> map1=new HashMap<String, String>();
//	map1.put("email", "rak@gmail.com");
//	map1.put("password", "RSKathar@11");
//	map1.put("productName", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{"rsk@gmail.com","RSKathar@11","ZARA COAT 3"},{"rak@gmail.com","RSKathar@11","ADIDAS ORIGINAL"}};
//	}
}
