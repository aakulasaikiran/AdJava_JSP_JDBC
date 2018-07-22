package myapp;

public class Rotator
	{
   private String images[] = { "1.jpg","2.jpg","3.jpg","4.jpg","5.jpg"};
   private String links[] = {"http://www.yahoo.co.in","http://www.scores.sify.com","http://www.cricinfo.com","http://www.bazee.com","http://www.google.co.in"};
   private int selectedIndex = 0;
   public String getImage()   
	   {
	   return images[ selectedIndex ];
	   }
   public String getLink()  
	   {
	   return links[ selectedIndex ];  
   }
   public void nextAdvertisement()  
	   {
	   selectedIndex = ( selectedIndex + 1 ) % images.length;
	   }
}