package EcommerceTest.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceTest.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement Submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	
	public ProductCatalogue loginApplication(String Email, String Password) throws InterruptedException{
		userEmail.sendKeys(Email);
		password.sendKeys(Password);
		Submit.click();
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	public void goTo() {
		driver.get("http://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage(){
		waitForWebElementToAppear(errormessage);
		return errormessage.getText();
	}
	

}
