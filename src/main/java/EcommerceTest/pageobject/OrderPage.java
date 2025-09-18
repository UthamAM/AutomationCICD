package EcommerceTest.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import EcommerceTest.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
    
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ProductNames;
	
	public Boolean VerifyOrderDisplay(String ProductName) {
		Boolean match = ProductNames.stream().anyMatch(prods->prods.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	


}
