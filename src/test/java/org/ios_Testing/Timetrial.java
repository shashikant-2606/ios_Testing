package org.ios_Testing;

import org.testng.annotations.Test;

public class Timetrial extends NewTest 
{
  @Test
  public void site1() 
  {
	  driver.get("https://www.villeos.com/");
	  String s1=driver.getTitle();
	     System.out.println(s1);
  }
  @Test
  public void site2()
  {
	  driver.get("https://www.amazon.in");
	  String s1=driver.getTitle();
	     System.out.println(s1);
  }
}
