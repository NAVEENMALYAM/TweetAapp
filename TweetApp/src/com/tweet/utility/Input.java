package com.tweet.utility;

import java.util.Scanner;

public class Input {
	
	static final Scanner sc =new Scanner(System.in);
	
	 public static String getString() {
		 
		 String a=sc.nextLine();
		 return a;
		
	}
    public static int getInteger() {
    	int a=sc.nextInt();
    	sc.nextLine();
    	return a;
    }
}
