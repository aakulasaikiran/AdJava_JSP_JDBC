/* Used to hold Address Details
* Author : Team -J
* Version : 1.0
*/

package com;

public class Address
{
String street;
String email;
String city;


 public void setStreet( String value ) {
 	street = value;
 }
 public void setEmail( String value ) {
	 email = value;
 } 
 public void setCity( String value ) {
	 city=value;
 } 
 public String getStreet( String value ) {
	  return street;
 }
 public String getEmail() {
	  return email;
 } 
 public String getCity() {
	  return city;
 }

}
