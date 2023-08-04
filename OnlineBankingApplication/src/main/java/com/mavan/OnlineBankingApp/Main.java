package com.mavan.OnlineBankingApp;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Main 
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println("|**************************-----Welcome To My Online Banking Application-----***************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        // Define a custom date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Format the current date using the defined format
        String formattedDate = currentDate.format(formatter);
        // Display the formatted date in the console
        System.out.println("Date: " + formattedDate);
        while(true) 
		{	
			Scanner sc = new Scanner(System.in);
            System.out.println();
            System.out.println("Options: ");
            System.out.println("1. Add your Bank account and Create a new Profile...");
			System.out.println("2. Login...");
			System.out.println();
			System.out.println("+-------------------------------------------------------------------------------------------------------+");
			System.out.println("|**************************************-----Select the Option-----**************************************|");
            System.out.println("+-------------------------------------------------------------------------------------------------------+");
            System.out.print("Option: ");
            int option =sc.nextInt();
			switch (option) 
			{
				case 1: Operation.createAccount();
					break;
				case 2: Operation.login();
					break;
				default:
				{
		            System.err.println("+-------------------------------------------------------------------------------------------------------+");
		            System.err.println("|************************************-----Error: Invalid option-----************************************|");
		            System.err.println("+-------------------------------------------------------------------------------------------------------+");
				}
			}
		}
	
	}
}
