package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelePhone;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmpwd;
	@FindBy(xpath="//input[@name='agree']")
	WebElement checkpolicy;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);;
	}
		public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelePhone(String tel) {
		txtTelePhone.sendKeys(tel); 
	}
		public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
		public void setConfirmpwd(String pwd) {
		txtConfirmpwd.sendKeys(pwd);
	}
	
	public void setprivacyPolicy() {
		checkpolicy.click();
	}
	public void clickContinue() {
		//sol1
		btnContinue.click();
		
		//sol2
		//btnContinue.submit();
		//slo3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		//sol4
		//javascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("argument[0].click();",btnContinue);
		//sol5
		//btnContinue.sendkeys(keys.RETURN);
		//sol6
		//WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeclickable(btnContinue)).click();
		}
	public	String getConfirmationMsg() {
			try {
				return (msgConfirmation.getText());
				
			} catch (Exception e) {
				return(e.getMessage());
			}
		}
	}
	




