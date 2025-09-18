package EcommerceTest.tests;

import static org.testng.Assert.assertTrue;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import EcommerceTest.TestComponents.BaseTest;
import EcommerceTest.pageobject.CartPage;
import EcommerceTest.pageobject.CheckOutPage;
import EcommerceTest.pageobject.ConfirmationPage;
import EcommerceTest.pageobject.LandingPage;
import EcommerceTest.pageobject.OrderPage;
import EcommerceTest.pageobject.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	String ProductName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
		
		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products = productcatalogue.getproductList();
		productcatalogue.addProductToCart(input.get("products"));
		CartPage cartpage= productcatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("products"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage =cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		
		String ConfirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest() throws InterruptedException {
		ProductCatalogue productcatalogue = landingpage.loginApplication("utham@gmail.com", "Utham@123456");
		OrderPage orderPage= landingpage.goToOrderPage();
		Boolean match= orderPage.VerifyOrderDisplay(ProductName);
		Assert.assertTrue(match);
	}
	
	public List<HashMap<String, String>> getJasonDataToMap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});		
		
		return data;
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String,String>> data = getJasonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\EcommerceTest\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

}

//HashMap<String,String> map = new HashMap<String,String>();
//map.put("email", "utham@gmail.com");
//map.put("password", "Utham@123456");
//map.put("products", "ZARA COAT 3");
//
//HashMap<String,String> map1 = new HashMap<String,String>();
//map1.put("email", "shetty@gmail.com");
//map1.put("password", "Iamking@000");
//map1.put("products", "ADIDAS ORIGINAL");
