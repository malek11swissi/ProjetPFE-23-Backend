package com.pack;
import java.util.Random;
import org.springframework.stereotype.Component;




public class Randomize
{
	long serialNumber,intermediate;
	public long retournerrandomserialnumber(){
        Random random = new Random();
        intermediate=random.nextLong();  
        //if nombre random negative convert valeur absolue 
        if(intermediate<0)
        	intermediate*=-1;
      // numéro serie plus court 
        serialNumber=intermediate/100000;
		      return serialNumber;
	}




  public static void main(String[] args)
    {
    	Randomize r=new Randomize();
    	System.out.println(r.retournerrandomserialnumber());
  }
}     