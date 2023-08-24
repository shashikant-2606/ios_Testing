package org.ios_Testing;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class NewTest {
  @Test
  public void f() throws MalformedURLException 
  {
	  DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
      //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "16.2"); // Replace with your iOS version
     // capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11"); // Replace with your device name
      capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
      capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
      //capabilities.setCapability("webDriverAgentUrl", WDAServer.SERVER_URL);
      
      URL url=new URL("http://127.0.0.1:4723/wd/hub/");

      IOSDriver driver = new IOSDriver(url, capabilities);

     driver.get("https://www.amazon.in");
     
     String s1=driver.getTitle();
     System.out.println(s1);
     //my test

      driver.quit();
  }
}
