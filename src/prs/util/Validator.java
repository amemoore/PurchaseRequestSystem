package prs.util;

import java.util.Scanner;

public class Validator
{
    public static String getString(Scanner sc, String prompt)
    {
        System.out.print(prompt);
        String s = sc.next();  // read user entry
        sc.nextLine();  // discard any other data entered on the line
        return s;
    }
    
    public static String getStringTwoChar(Scanner sc, String prompt)
    {
        StringBuilder chars;
        String s = "";
        boolean isValid = false;
        while (isValid==false){
	    	System.out.print(prompt);
	    	if (sc.hasNextInt()){
	    		System.out.println("Invalid input - enter state by 2 letter code.");
	        	break;
	    	}
	        else {
	        	s = sc.next();  // read user entry
		        chars = new StringBuilder(s);	
		        if (chars.length()==2)
		        	isValid=true;
		        else if (chars.length()!=2)
			        System.out.println("Invalid input - enter state by 2 letter code.");
	        }
        }  
	        sc.nextLine();  // discard any other data entered on the line
        return s;
    }
    
    public static String getLine(Scanner sc, String prompt)
    {
        System.out.print(prompt);
        String s = sc.nextLine();  // read user entry
        return s;
    }

    public static int getInt(Scanner sc, String prompt)
    {
        int i = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            System.out.print(prompt);
            if (sc.hasNextInt())
            {
                i = sc.nextInt();
                isValid = true;
            }
            else
            {
                System.out.println("Error! Invalid integer value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return i;
    }

    public static int getInt(Scanner sc, String prompt,
    int min, int max)
    {
        int i = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            i = getInt(sc, prompt);
            if (i <= min)
                System.out.println(
                    "Error! Number must be greater than " + min + ".");
            else if (i >= max)
                System.out.println(
                    "Error! Number must be less than " + max + ".");
            else
                isValid = true;
        }
        return i;
    }

    public static double getDouble(Scanner sc, String prompt)
    {
        double d = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            System.out.print(prompt);
            if (sc.hasNextDouble())
            {
                d = sc.nextDouble();
                isValid = true;
            }
            else
            {
                System.out.println("Error! Invalid decimal value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return d;
    }

    public static double getDouble(Scanner sc, String prompt,
    double min, double max)
    {
        double d = 0;
        boolean isValid = false;
        while (isValid == false)
        {
            d = getDouble(sc, prompt);
            if (d <= min)
                System.out.println(
                    "Error! Number must be greater than " + min + ".");
            else if (d >= max)
                System.out.println(
                    "Error! Number must be less than " + max + ".");
            else
                isValid = true;
        }
        return d;
    }

    public static boolean getBoolean(Scanner sc, String prompt, Boolean b){
    	String choice = getString(sc,prompt);
		if (choice.equalsIgnoreCase("y"))
			b = true;
		else if (choice.equalsIgnoreCase("n"))
			b = false;
		
		return b;
    }
}