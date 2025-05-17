package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass
	@Parameters({"browser", "os"})
	public void setup(String br, String os) throws IOException, InterruptedException
	{
		
		//loading config file
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());  //this.getClass will get class name dynamically and store in logger varaiable. LogManager will get the log4j xml file into the varaible
		
		//For remote execution using selenium grid
		//To check if execution to be done local or remote
		
		if(p.getProperty("execution_env").equals("remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
			}
			else 
			{
				System.out.println("incorrect platform/ OS");
					return;
			}
					
	
		
		switch(br.toLowerCase()) {
		case "chrome":cap.setBrowserName("chrome");break;
		case "edge": cap.setBrowserName("MicrosoftEdge");break;
		case "ff" : cap.setBrowserName("firefox");break;
		default: System.out.println("Incorrect browser");return;
		
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		
		if(p.getProperty("execution_env").equals("local")) {
		switch (br.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "ff" : driver = new FirefoxDriver(); break;
		default : System.out.println("invalid browser"); return;
		}
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
	//method to capture screenshot
	
	
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
