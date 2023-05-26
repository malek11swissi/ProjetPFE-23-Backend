package com.pack;
// Java program to demonstrate
// method calls of Random class
import java.util.Random;

import org.springframework.stereotype.Component;

public class Randomize
{
	long serialNumber,intermediate;
	public long retournerrandomserialnumber(){
        Random random = new Random();
        intermediate=random.nextLong();  
        if(intermediate<0)
        	intermediate*=-1;
        serialNumber=intermediate/100000;
		return serialNumber;
	}
    public static void main(String[] args)
    {
    	Randomize r=new Randomize();
    	System.out.println(r.retournerrandomserialnumber());
  }
}     