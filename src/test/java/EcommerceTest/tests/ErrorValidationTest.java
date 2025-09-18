package EcommerceTest.tests;

import static org.testng.Assert.assertTrue;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import EcommerceTest.TestComponents.BaseTest;
import EcommerceTest.TestComponents.Retry;
import EcommerceTest.pageobject.CartPage;
import EcommerceTest.pageobject.CheckOutPage;
import EcommerceTest.pageobject.ConfirmationPage;
import EcommerceTest.pageobject.LandingPage;
import EcommerceTest.pageobject.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException{
		
		String ProductName = "ZARA COAT 3";
		landingpage.loginApplication("utham@gmail.com", "Utham@456");
		String errmsg = landingpage.getErrorMessage();
		Assert.assertEquals("Incorrect emailor password.", errmsg);

	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException{
		
		String ProductName = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingpage.loginApplication("utham@gmail.com", "Utham@123456");
		List<WebElement>products = productcatalogue.getproductList();
		productcatalogue.addProductToCart(ProductName);
		CartPage cartpage= productcatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}


}
