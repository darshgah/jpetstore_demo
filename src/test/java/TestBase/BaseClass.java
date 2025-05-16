package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
	
	public static WebDriver driver;
	public Properties p; 
	public Logger logger;
	
	@BeforeClass
	public void setup() throws IOException 
	{
		FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("browser").equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(p.getProperty("browser").equals("edge")) {
			driver = new EdgeDriver();
		}
		else if(p.getProperty("browser").equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Incorrect browser");
			return;
		}
		
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	
	@AfterClass
	public void tearDown() 
	{
		driver.close();
	}
	
	
	public String captureScreen(String tname) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String scrfolder = System.getProperty("user.dir")+ File.separator + "screenshots";
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		

		String targetFilePath=scrfolder + File.separator + tname + "_" + timeStamp + ".png";
		
		File targetFile = new File(targetFilePath);
		FileUtils.copyFile(sourceFile, targetFile);
		
		
		return targetFilePath;
		
		}
}
