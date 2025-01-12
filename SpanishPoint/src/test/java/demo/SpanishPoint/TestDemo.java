package demo.SpanishPoint;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDemo {

	
	@Test
	public void demoTest() throws InterruptedException{
		
		//driver setup 
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//window and implicit wait configuration
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://www.matchingengine.com/");
		
		
		//actions class for imitating mouse movements
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.linkText("Modules"))).perform();	
		driver.findElement(By.linkText("Repertoire Management Module")).click();
		
		//The hard waits are not required for the code to work, they are just there to show the automation steps
		Thread.sleep(1000);
		
		//JS code for scrolling to additional features
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1550)");
		Thread.sleep(1000);
		
		
		//clicks Products Supported
		WebElement btn_productsSupported = driver.findElement(By.xpath("//span[@class='vc_tta-title-text'][text() = 'Products Supported']"));
		btn_productsSupported.click();
		Thread.sleep(1000);
	
		//Captures the group of products in a list 
		List<WebElement> listOfProducts = driver.findElements(By.xpath("(//div[@class = 'wpb_text_column wpb_content_element wpb_animate_when_almost_visible wpb_fadeIn fadeIn wpb_start_animation animated'])[2]//span"));
		
		//products that need to be present
		String[] products = {"Cue Sheet / AV Work", "Recording", "Bundle", "Advertisement "};

		for(int i=0; i<products.length; i++) {
			//will throw 'Correct product not displayed.' in testNG report if products are not found
			Assert.assertEquals(listOfProducts.get(i).getText(), products[i], "Correct product not displayed.");
			}
		

		
		//Again, the wait is not required, just here to show the automation steps.
		Thread.sleep(2000);
		
		
		driver.quit();
		
		
	}
	
}
