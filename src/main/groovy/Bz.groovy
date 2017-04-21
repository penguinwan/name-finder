import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

Selenium selenium
WebDriver driver;

FirefoxProfile profile = new FirefoxProfile();
profile.setEnableNativeEvents(false);
driver = new FirefoxDriver(profile);
String baseUrl = "http://www.name321.net"
selenium = new WebDriverBackedSelenium(driver, baseUrl);

def execution = { word ->
	selenium.open("/chest/bz.php");
	for (int second = 0;; second++) {
		if (second >= 60) throw new Exception("timeout");
		try { if (selenium.isElementPresent("css=input[type=\"submit\"]")) break; } catch (Exception e) {}
		Thread.sleep(1000);
	}
	selenium.type("name=name", "李南"+word);
	selenium.click("document.login.sex[1]");
	selenium.select("name=m", "label=12");
	selenium.select("name=d", "label=18");
	selenium.select("name=h", "label=22");
	selenium.select("name=h", "label=21");
	selenium.select("name=i", "label=5");
	selenium.click("css=input[type=\"submit\"]");
	//selenium.waitForPageToLoad("60000");
	//Thread.sleep(2000);
	for (int second = 0;; second++) {
		if (second >= 60) throw new Exception("timeout");
		try { if (selenium.isElementPresent("css=input[type=\"button\"]")) break; } catch (Exception e) {}
		Thread.sleep(1000);
	}
	selenium.click("css=input[type=\"button\"]")
	return driver.findElement(By.cssSelector("div[id=\"show\"]")).getText()
}

def inputFile = new File('input-1.txt')
def outputFile = new File('output-1.txt')

inputFile.eachLine('UTF-8') { line ->
	def word = line.trim()
	def result = execution(word)
	outputFile.append(result, 'UTF-8')
	outputFile.append('\n')
}

selenium.stop()