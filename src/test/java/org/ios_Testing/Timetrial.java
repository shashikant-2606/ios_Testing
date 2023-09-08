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
import com.mailslurp.models.Inbox;

import okhttp3.OkHttpClient;

public class Timetrial extends NewTest 
{
	private static ApiClient mailslurpClient;
	private static final Long TIMEOUT_MILLIS = 60000L;
	private static Inbox inbox;
	private static Email email;
	private static String confirmationCode;
	private static final boolean UNREAD_ONLY = true;

  @Test
  public void f() throws ApiException, InterruptedException 
  {
	  //During the below line of cod ewe are trying to connect mailslurp website to get a temprory email for signup process.
	  mailslurpClient = com.mailslurp.clients.Configuration.getDefaultApiClient();
      mailslurpClient.setApiKey("78dd8839611be7cb9b55bf2576414311a362a54cc04fae9ff6114bd0262c93c6");
      mailslurpClient.setConnectTimeout(TIMEOUT_MILLIS.intValue());
      
      
      //Below line of code will be created a temprory Email address for signup process
      InboxControllerApi inboxControllerApi = new InboxControllerApi(mailslurpClient);
      Inbox inbox = inboxControllerApi.createInboxWithDefaults();
      String emailAddress = inbox.getEmailAddress();
      System.out.println(emailAddress);
      
      String password1="Testing@12345";
      Thread.sleep(5000);
      
      driver.get("https://playground.mailslurp.com");
      
      driver.findElement(By.cssSelector("[data-test=sign-in-create-account-link]")).click();
      
      Thread.sleep(5000);
      driver.findElement(By.name("email")).sendKeys(emailAddress);
      driver.findElement(By.name("password")).sendKeys(password1);
      driver.findElement(By.cssSelector("[data-test=sign-up-create-account-button]")).click();
      
      WaitForControllerApi waitForControllerApi = new WaitForControllerApi(mailslurpClient);
      email = waitForControllerApi.waitForLatestEmail(inbox.getId(), TIMEOUT_MILLIS, UNREAD_ONLY);
      
      //assertTrue(email.getSubject().contains("Please confirm your email address"));
      
      Pattern p = Pattern.compile(".*verification code is (\\d+).*");
      Matcher matcher = p.matcher(email.getBody());

      // find first occurrence and extract
      assertTrue(matcher.find());
      confirmationCode = matcher.group(1);
      
      System.out.println(confirmationCode);
}
}
