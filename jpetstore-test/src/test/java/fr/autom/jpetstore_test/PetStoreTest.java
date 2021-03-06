package fr.autom.jpetstore_test;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PetStoreTest {

	WebDriver driver;
	String browser;
	
	@Before
	public void setUp() throws MalformedURLException {
		browser = System.getProperty("browser");
		/*DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", browser);
		if (browser.equals("firefox")) {
			caps.setCapability("marionette", true);
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);*/
		
		

		if (browser.equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions().setProfile(new FirefoxProfile());
			options.addPreference("browser.tabs.remote.autostart", false);
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Formation\\OWASP ZAP\\webdriver\\windows\\64\\geckodriver.exe");
			driver = new FirefoxDriver(options);
		} else if (browser.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Formation\\OWASP ZAP\\webdriver\\windows\\32\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		driver.get("http://localhost:8085/jpetstore/actions/Catalog.action");
	}

	@Test
	public void test() {
		WebElement bouton = driver.findElement(By.xpath("//div[@id='SidebarContent']/a[1]/img[1]"));
		bouton.click();

		WebElement elementVerif = driver.findElement(By.xpath("//div[@id='Catalog']/h2"));
		String texte = elementVerif.getText();
		assertTrue(texte.equals("Fish"));
		System.out.println("Succès !");
	}

	@After
	public void tearDown() {
		driver.close();
	}
}
