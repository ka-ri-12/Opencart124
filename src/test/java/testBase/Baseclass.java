package testBase;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;




public class Baseclass {

	
	public  static WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups= {"sanity","Regression"})
	@Parameters({"os","browser"})
		public void setup(String os,String br) throws IOException
		{
		//loading config.properties file
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
	//selenium grid	
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cp = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				cp.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("mac"))
			{
				cp.setPlatform(Platform.MAC);
			}else
			{
				System.out.println("no matching os");
				return;
			}
		
		
		//browser
		switch(br.toLowerCase())
		{
		case "chrome":cp.setBrowserName("chrome");break;
		case "edge":cp.setBrowserName("MicrosoftEdge");break;
		case "firefox":cp.setBrowserName("firefox");break;
		default:System.out.println("No matching browser");return;
		}
		driver =new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cp);
		}
		
	if(p.getProperty("execution_env").equalsIgnoreCase("local"))	
	{
		
	switch(br.toLowerCase())
		{
		case "chrome":driver= new ChromeDriver();break;
		case "edge":driver= new EdgeDriver();break;
		default:System.out.println("invalid browser name..");return;
		}
	}
	
		//to generate logs
		 logger = LogManager.getLogger(this.getClass());     //LOG4J2
		
			driver= new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("https://tutorialsninja.com/demo/");//reading url from properties file
			driver.manage().window().maximize();
		}
	@AfterClass	(groups= {"sanity"})
		public void teardown() 
		{
			driver.quit();
		}
	
	public String captureScreen(String tname) {
		String timestamp =new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takescreenshot=(TakesScreenshot)driver;
		File sourcefile=takescreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetfilepath=System.getProperty("user.dir")+"\\screenshots\\"+ tname +"-"+ timestamp +".png";
		File targetfile=new File(targetfilepath);
		
		sourcefile.renameTo(targetfile);
		
		return targetfilepath;
	}
	
	
	}
