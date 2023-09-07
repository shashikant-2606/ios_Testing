package org.ios_Testing;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.clients.Configuration;
import com.mailslurp.models.Email;
import com.mailslurp.models.InboxDto;

import okhttp3.OkHttpClient;

public class Timetrial extends NewTest 
{
	private static ApiClient mailslurpClient;
	private static final Long TIMEOUT_MILLIS = 120000L;
	private static InboxDto inbox;
	private static String confirmationCode;
	private static final boolean UNREAD_ONLY = true;
	Email email1;

  @Test (timeOut=120000)
  public void f() throws InterruptedException, ApiException 
  {
	  OkHttpClient httpClient = new OkHttpClient.Builder()
              .connectTimeout(5, TimeUnit.MINUTES)
              .writeTimeout(5, TimeUnit.MINUTES)
              .readTimeout(5, TimeUnit.MINUTES)
              .build();
	  //During the below line of cod ewe are trying to connect mailslurp website to get a temprory email for signup process.
	  mailslurpClient = com.mailslurp.clients.Configuration.getDefaultApiClient();
	  mailslurpClient.setBasePath("https://java.api.mailslurp.com");
	  mailslurpClient.setHttpClient(httpClient);
      mailslurpClient.setApiKey("0978c19929af998ac07caa7967c02544862dac4745ffe29a33f975f873275333");
      mailslurpClient.setConnectTimeout(TIMEOUT_MILLIS.intValue());
      
      //Below line of code will be created a temprory Email address for signup process
      
      mailslurpClient.setConnectTimeout(60000);
      mailslurpClient.setWriteTimeout(60000);
      mailslurpClient.setReadTimeout(60000);
      
      InboxControllerApi inboxControllerApi = new InboxControllerApi(mailslurpClient);
      inbox = inboxControllerApi.createInboxWithDefaults();
      String emailAddress = inbox.getEmailAddress();
      System.out.println(emailAddress);
      System.out.println(inbox.getId());
      
      String password1="Testing@12345";
      Thread.sleep(5000);
      driver.get("https://playground.mailslurp.com");
      String url=driver.getTitle();
      System.out.println(url);
      
      Thread.sleep(5000);
      driver.findElement(By.cssSelector("[data-test=sign-in-create-account-link]")).click();
      
      Thread.sleep(5000);
      driver.findElement(By.name("email")).sendKeys(emailAddress);
      driver.findElement(By.name("password")).sendKeys(password1);
      driver.findElement(By.cssSelector("[data-test=sign-up-create-account-button]")).click();
      
      System.out.println("Completed till this process");
      
      /*
       String name="Villeos";
      
      String password1="Testing@12345";
      
      String password2="Testing@12345";
      
      //The following method will be use to complete the account details.
      
      p1.Accout_info(emailAddress, name, password1, password2);
      
      //The following method will be use to complete the account details.
      
      p1.Contact_info();*/
      
      //by using the below line of code we can extract a verification code from the inbox of temprory mail account.
     try
     {
      WaitForControllerApi waitForControllerApi = new WaitForControllerApi(mailslurpClient);
      email1 = waitForControllerApi.waitForLatestEmail(inbox.getId(), TIMEOUT_MILLIS.longValue(), UNREAD_ONLY, null, null, null,null);
     }
     
     catch (ApiException e) {
    	    e.printStackTrace();
    	    System.out.println("Error message: " + e.getMessage());
    	    System.out.println("HTTP status code: " + e.getCode());
    	    System.out.println("Response body: " + e.getResponseBody());
    	    // Handle the exception
    	}
    	
      Pattern p = Pattern.compile(".*verification code is (\\d+).*");
      Matcher matcher = p.matcher(email1.getBody());

      // find first occurrence and extract
      assertTrue(matcher.find());
      confirmationCode = matcher.group(1);
      
      System.out.println(confirmationCode);
      

      //assertTrue(confirmationCode.length() == 6);*/
}
}
