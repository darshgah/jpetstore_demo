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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass
    @Parameters({"browser"})
    public void setup(String br) throws IOException, InterruptedException {

        // Load config file
        FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        // Initialize logger
        logger = LogManager.getLogger(this.getClass());

        // Initialize browser drivers
        switch (br.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--remote-allow-origins=*");

                
                String userDataDir = System.getProperty("java.io.tmpdir") + "/chrome-profile-" + System.currentTimeMillis();
                chromeOptions.addArguments("--user-data-dir=" + userDataDir);

                // Optional headless mode if needed in CI
                 chromeOptions.addArguments("--headless=new");

                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            case "ff":
                driver = new FirefoxDriver();
                break;

            default:
                System.out.println("Invalid browser: " + br);
                return;
        }

        // Common setup
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(p.getProperty("url"));
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Method to capture screenshot
    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String scrfolder = System.getProperty("user.dir") + File.separator + "screenshots";

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = scrfolder + File.separator + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        FileUtils.copyFile(sourceFile, targetFile);

        return targetFilePath;
    }
}
