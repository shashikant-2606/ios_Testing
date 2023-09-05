package org.ios_Testing;

import static org.testng.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.models.Email;
import com.mailslurp.models.InboxDto;

public class Timetrial extends NewTest 
{
	private static ApiClient mailslurpClient;
	private static final Long TIMEOUT_MILLIS = 30000L;
	private static InboxDto inbox;
	private static Email email;
	private static String confirmationCode;
	private static final boolean UNREAD_ONLY = true;
	


  @Test
  public void f() throws InterruptedException, ApiException 
  {
	  //During the below line of cod ewe are trying to connect mailslurp website to get a temprory email for signup process.
	  mailslurpClient = com.mailslurp.clients.Configuration.getDefaultApiClient();
      mailslurpClient.setApiKey("094f1948766abbac9d896db2425814cd28ef67f93c3c440e36b34923d866c9e2");
      mailslurpClient.setConnectTimeout(TIMEOUT_MILLIS.intValue());
      
      
      //Below line of code will be created a temprory Email address for signup process
      InboxControllerApi inboxControllerApi = new InboxControllerApi(mailslurpClient);
      InboxDto inbox = inboxControllerApi.createInboxWithDefaults();
      String emailAddress = inbox.getEmailAddress();
      System.out.println(emailAddress);
      
      String password1="Testing@12345";
      Thread.sleep(5000);
      
      driver.findElement(By.cssSelector("[data-test=sign-in-create-account-link]")).click();
      
      Thread.sleep(5000);
      driver.findElement(By.name("email")).sendKeys(emailAddress);
      driver.findElement(By.name("password")).sendKeys(password1);
      driver.findElement(By.cssSelector("[data-test=sign-up-create-account-button]")).click();
      
      /*
       String name="Villeos";
      
      String password1="Testing@12345";
      
      String password2="Testing@12345";
      
      //The following method will be use to complete the account details.
      
      p1.Accout_info(emailAddress, name, password1, password2);
      
      //The following method will be use to complete the account details.
      
      p1.Contact_info();*/
      
      //by using the below line of code we can extract a verification code from the inbox of temprory mail account.
      
      WaitForControllerApi waitForControllerApi = new WaitForControllerApi(mailslurpClient);
      email = waitForControllerApi.waitForLatestEmail(inbox.getId(), TIMEOUT_MILLIS, UNREAD_ONLY, null, null, null, null);
      
      //assertTrue(email.getSubject().contains("Please confirm your email address"));
      
      Pattern p = Pattern.compile(".*verification code is (\\d+).*");
      Matcher matcher = p.matcher(email.getBody());

      // find first occurrence and extract
      assertTrue(matcher.find());
      confirmationCode = matcher.group(1);
      
      System.out.println(confirmationCode);

      //assertTrue(confirmationCode.length() == 6);*/
}
}
