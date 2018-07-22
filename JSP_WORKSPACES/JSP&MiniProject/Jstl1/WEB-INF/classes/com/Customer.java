/* Used to hold customer data
* Author : Team -J
* Version : 1.0
*/

package com;
import com.Address;
public class Customer
{
String name;
int age;
Address addr;

 public void setName( String value ) {
 	name = value;
 }
 public void setAge( int value ) {
	 age = value;
 } 
 public void setAddress(Address addr){
	this.addr = addr;
 }
public String getName() {
 return name;
 } 

public int getAge() {
 return age; 
}
public Address getAddress(){
	return addr;
}
}
