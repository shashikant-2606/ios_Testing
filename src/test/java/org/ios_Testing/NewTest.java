package org.ios_Testing;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class NewTest {
  @Test
  public void f() throws MalformedURLException 
  {
	  DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
      //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.5"); // Replace with your iOS version
      //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12 Pro"); // Replace with your device name
      capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
      capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XcuiTest");
      
      URL url=new URL("http://127.0.0.1:4723/");

      IOSDriver driver = new IOSDriver(url, capabilities);

     driver.get("https://www.amazon.in/");
     
     String s1=driver.getTitle();
     System.out.println(s1);

      driver.quit();
  }
}
