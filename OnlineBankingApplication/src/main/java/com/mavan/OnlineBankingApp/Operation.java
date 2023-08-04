package com.mavan.OnlineBankingApp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Operation 
{
	//private static datatype var_name;
	private static Connection conn;
	private static PreparedStatement pst ;	
	private static String sql;
	private static ResultSet rs;
	private static long account_number;
	private static String name,email,address,ifsc,password;
	private static float balance;
	private static long phone;
	private static long i;
	private static Scanner sc = new Scanner(System.in);
	// To create a new account
	public static void createAccount() throws SQLException, ClassNotFoundException
	{
		conn=DataBaseConnection.getConnection();
		sql = "insert into profile(account_number, name, email, phone_number, address, ifsc, balance, password) values(?,?,?,?,?,?,?,?)";
		pst = conn.prepareStatement(sql);
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
        System.out.println("|***************************************-----Add Your Details-----**************************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
        //get data from user
        while(true) 
		{
			System.out.print("Add Your 14 Digit Bank Account Number:* ");
	        long ac_no =  sc.nextLong();;
			if (ac_no >= 10000000000000l && ac_no<= 99999999999999l) 
			{
				  account_number = ac_no;
				  break;
			} 
			else 
			{
				System.out.println();
				System.err.println("Account number must be 14 digit...");
			}
		}
		System.out.print("Enter Your Full Name:* ");
		sc.nextLine();
		name=sc.nextLine();
		System.out.print("Enter Email:* ");
		email = sc.next();
		while(true) 
		{
			System.out.print("Enter 10 Digit Phone Number:* ");
			long ph =sc.nextLong();
			if(ph>=1000000000l && ph<=9999999999l)
			{
				phone = ph;
				break;
			}
			else 
			{
				System.out.println();
				System.err.println("Phone Number Must Be 10 Digit Only...");
			}
		}
		System.out.print("Enter Address City, State, Pincode:* ");
		sc.nextLine();
		address = sc.nextLine();
		while(true) 
		{
			System.out.print("Enter 11 Digit IFSC Code:* ");
			String if_sc = sc.next();
			int len = if_sc.length();
			if(len == 11) {
				ifsc=if_sc;
				break;
			}
			else
			{
				System.out.println();
				System.err.println("IFSC code must be 11 digit only...");
			}
		}
		System.out.print("Enter Balance:* Rs. ");
		balance = sc.nextFloat();
		System.out.print("Enter Password:* ");
		password = sc.next();
		//Set data into placeholder
		pst.setLong(1, account_number);
		pst.setString(2, name);
		pst.setString(3, email);
		pst.setLong(4, phone);
		pst.setString(5, address);
		pst.setString(6, ifsc);
		pst.setFloat(7, balance);
		pst.setString(8, password);
		int i = pst.executeUpdate(); /// imp
		if(i>0) {
			System.out.println();
			System.out.println("Welcome "+name+"...");
			System.out.println();
            System.out.println("+-------------------------------------------------------------------------------------------------------+");
			System.out.println("|*****************************-----Your Account Is Successfully Created-----****************************|");
            System.out.println("+-------------------------------------------------------------------------------------------------------+");
			System.out.println();
			// Stay Login or Logout...
            System.out.println("+-------------------------------------------------------------------------------------------------------+");
			System.out.println("|****************************************-----Enter Option-----*****************************************|");
            System.out.println("+-------------------------------------------------------------------------------------------------------+");
			System.out.println("1. Stay Login...");
			System.out.println("2. Logout...");
			int option = sc.nextInt();
			switch (option) {
			case 1: Operation.home();
				break;
			case 2: 
				System.out.println();
				System.out.println("+-------------------------------------------------------------------------------------------------------+");
				System.out.println("|**************************-----Thank you for using Banking Application-----****************************|");
	            System.out.println("+-------------------------------------------------------------------------------------------------------+");
				System.out.println();
	            System.exit(0);
			break;
			default:
				System.out.println();
				System.err.println("+-------------------------------------------------------------------------------------------------------+");
				System.err.println("|************************************-----Error: Invalid option-----************************************|");
	            System.err.println("+-------------------------------------------------------------------------------------------------------+");
				break;
			}
		}
		else {
			System.out.println();
			System.err.println("+-------------------------------------------------------------------------------------------------------+");
			System.err.println("|*****************************-----Error: Your account is not created-----******************************|");
	        System.err.println("+-------------------------------------------------------------------------------------------------------+");
		}
	}
	// After login
	public static void login() throws ClassNotFoundException, SQLException 
	{
		// Login by using Account number, Email ID and Password...
		conn=DataBaseConnection.getConnection();
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
        System.out.println("|*********************************-----Enter Your Details For Login-----********************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.print("Enter your Email: ");
        email=sc.next();
        System.out.print("Enter your password: ");
        password=sc.next();
        sql="select name from profile where email=? and password=?";
        pst=conn.prepareStatement(sql);
		pst.setString(1,email);
		pst.setString(2,password);
	    rs=pst.executeQuery();
        if(rs.next()) 
        {
        	System.out.println();
        	System.out.println("+-------------------------------------------------------------------------------------------------------+");
			System.out.println("|*************************************-----Login successful!!!-----*************************************|");
            System.out.println("+-------------------------------------------------------------------------------------------------------+");
            System.out.println();
	        name=rs.getString(1);
	        System.out.println("Hello " +name+"...");
	  		Operation.home();
        }
        else 
        {
          System.out.println();
          System.err.println("+-------------------------------------------------------------------------------------------------------+");
		  System.err.println("|****************************-----Error: Enter Valid Email or Password-----*****************************|");
          System.err.println("+-------------------------------------------------------------------------------------------------------+");
        }
	}
	// Home menu
	public static void home() throws ClassNotFoundException, SQLException 
	{
		 while(true) 
		 {
			 System.out.println();
			 System.out.println("+-------------------------------------------------------------------------------------------------------+");
			 System.out.println("|*************************************-----Select the Option-----***************************************|");
	         System.out.println("+-------------------------------------------------------------------------------------------------------+");
			 System.out.println();
	         System.out.println("1. Check balance.");
	         System.out.println("2. Deposit amount in your account.");
	         System.out.println("3. Withdraw amount in your account.");
	         System.out.println("4. Fund transfer in Other Account.");
	         System.out.println("5. Check User profile.");
	         System.out.println("6. Update user profile.");
	         System.out.println("7. Mini Statement.");
	         System.out.println("8. Help & Contact us.");
	         System.out.println("9. Logout.");
	         System.out.print("Option: ");
	         System.out.println();
	         int option = sc.nextInt();
	         switch (option) 
	         {
			case 1: Operation.checkBalance();
				break;
			case 2: Operation.depositAmount();
				break;
			case 3: Operation.withdrowAmount();
				break;
			case 4: Operation.fundTransferInOtherAccount();
				break;
			case 5: Operation.userDetails();
				break;
			case 6: Operation.update();
				break;
			case 7: Operation.miniStatement();
				break;
			case 8: 
				System.out.println("+-------------------------------------------------------------------------------------------------------+");
				System.out.println("|************************************-----Contact Information-----**************************************|");
				System.out.println("+-------------------------------------------------------------------------------------------------------+");
				System.out.println("| Customer care Number\t\t\t\t\t:\t\t\t\t +91 9144272136 |");
				System.out.println("| Sand Mail\t\t\t\t\t\t:\t\t\t     anshuagh@gmail.com |");
				System.out.println("| WhatsApp Number\t\t\t\t\t:\t\t\t\t +91 9144272136 |");
				System.out.println("+-------------------------------------------------------------------------------------------------------+");

				break;
			case 9:
				System.out.println();
				System.out.println("+-------------------------------------------------------------------------------------------------------+");
				System.out.println("|**************************-----Thank you for using Banking Application-----****************************|");
	            System.out.println("+-------------------------------------------------------------------------------------------------------+");
				System.out.println();
				System.exit(0);
				break;
			default: 
				System.out.println();
				System.err.println("+-------------------------------------------------------------------------------------------------------+");
				System.err.println("|************************************-----Error: Invalid option-----************************************|");
	            System.err.println("+-------------------------------------------------------------------------------------------------------+");				
	            break;
			 }
		 }
	}
	//Check balance.
	public static void checkBalance() throws SQLException, ClassNotFoundException
	{
		conn=DataBaseConnection.getConnection();
		sql="select account_number, name, balance  from profile where account_number = ? and password = ?";
		pst = conn.prepareStatement(sql);
		System.out.println();
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println("|**************************-----Enter your credentials for Varification-----****************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println();
		System.out.print("Enter Account Number: ");
		account_number = sc.nextLong();
		System.out.print("Enter Your password: ");
		password = sc.next();
		pst.setLong(1, account_number);
		pst.setString(2, password);
		rs=pst.executeQuery();
		if(rs.next()) 
		{
			System.out.println();
			System.out.println("+-------------------------------------------------------------------------------------------------------+");
			System.out.println("|***********************************-----Your Avilable Balance-----*************************************|");
	        System.out.println("+-------------------------------------------------------------------------------------------------------+");
			account_number=rs.getLong(1);
		    name=rs.getString(2);
		    balance=rs.getFloat(3);
		    System.out.println("Account Number: "+ account_number);
		    System.out.println("Name : "+ name);
		    System.out.println("Avilable balance: "+ balance);
		}else
		{
			System.out.println();
			System.err.println("+-------------------------------------------------------------------------------------------------------+");
			System.err.println("|***********************-----Error: Enter Valid Account Number and Password-----************************|");
			System.err.println("+-------------------------------------------------------------------------------------------------------+");
        }
	}
	//Deposit amount in your account.
	public static void depositAmount() throws SQLException, ClassNotFoundException
	{
		conn=DataBaseConnection.getConnection();
		sql="select balance from profile where account_number = ? and password = ?";
		pst = conn.prepareStatement(sql);
		System.out.println();
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println("|**************************-----Enter your credentials for Varification-----****************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println();		
		System.out.print("Enter Account Number: ");
		account_number = sc.nextLong();
		System.out.print("Enter Your password: ");
		password = sc.next();
		pst.setLong(1, account_number);
		pst.setString(2, password);
		rs=pst.executeQuery();
		if(rs.next())
		{
			balance=rs.getFloat(1);
			float updated_balance = balance;
			System.out.print("Enter amount to deposit: ₹");
	        double amount = sc.nextDouble();
	        if (amount > 0) 
	        {
	        	updated_balance += amount;
	    		String sql_update = "Update profile set balance = ? where account_number = ?";
	    		pst=conn.prepareStatement(sql_update);
				pst.setFloat(1, updated_balance);
				pst.setLong(2,account_number);
				i=pst.executeUpdate();
				if(i>0) 
				{
					System.out.println("Deposited ₹" + amount + " successfully...");
					// Insert deposit transaction record into Transaction table
				    sql = "INSERT INTO Transaction (account_number, date, time, transaction_type, amount) VALUES (?, NOW(),NOW(), ?, ?)";
				    pst = conn.prepareStatement(sql);
				    pst.setLong(1, account_number);
				    pst.setString(2, "Credit"); // Assuming "Credit" represents a deposit transaction
				    pst.setDouble(3, amount);
				    pst.executeUpdate();
					
				}else 
				{
					System.out.println();
					System.err.println("+-------------------------------------------------------------------------------------------------------+");
					System.err.println("|******************************-----Your Transaction Has Been Failed-----*******************************|");
					System.err.println("+-------------------------------------------------------------------------------------------------------+");
				}
	        } 
	        else 
	        {
	            System.err.println("Error: Invalid deposit amount...");
	        }
		}
		else {
			System.out.println();
			System.err.println("+-------------------------------------------------------------------------------------------------------+");
			System.err.println("|***********************-----Error: Enter Valid Account Number and Password-----************************|");
			System.err.println("+-------------------------------------------------------------------------------------------------------+");		
			}
	}
	
	//Withdraw amount in your account.
	public static void withdrowAmount() throws SQLException, ClassNotFoundException
	{
		conn=DataBaseConnection.getConnection();
		sql="select balance from profile where account_number = ? and password = ?";
		pst = conn.prepareStatement(sql);
		System.out.println();
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println("|**************************-----Enter your credentials for Varification-----****************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println();	
		System.out.print("Enter Account Number: ");
		account_number = sc.nextLong();
		System.out.print("Enter Your password: ");
		password = sc.next();
		pst.setLong(1, account_number);
		pst.setString(2, password);
		rs=pst.executeQuery();
		if(rs.next())
		{
			balance=rs.getFloat(1);
			float updated_balance = balance;
			System.out.print("Enter amount to Withdrow: ₹");
	        double amount = sc.nextDouble();
	        if (amount > 0 && amount <= updated_balance) 
	        {
	        	updated_balance -= amount;
	    		String sql_update = "Update profile set balance = ? where account_number = ?";
	    		pst=conn.prepareStatement(sql_update);
				pst.setFloat(1, updated_balance);
				pst.setLong(2,account_number);
				
				i=pst.executeUpdate();
				if(i>0) 
				{
					System.out.println("Withdrow ₹" + amount + " successfully...");
					// Insert withdrawal transaction record into Transaction table
				    sql = "INSERT INTO Transaction (account_number, date, time, transaction_type, amount) VALUES (?, NOW(),NOW(), ?, ?)";
				    pst = conn.prepareStatement(sql);
				    pst.setLong(1, account_number);
				    pst.setString(2, "Debit"); // Assuming "Debit" represents a withdrawal transaction
				    pst.setDouble(3, amount);
				    pst.executeUpdate();
					
				}else 
				{
					System.out.println();
					System.err.println("+-------------------------------------------------------------------------------------------------------+");
					System.err.println("|******************************-----Your Transaction Has Been Failed-----*******************************|");
					System.err.println("+-------------------------------------------------------------------------------------------------------+");	       
				}
	        } 
	        else 
	        {
				System.err.println("Error: Invalid Withdrow Amount...");
	        }
		}
		else 
		{
			System.out.println();
			System.err.println("+-------------------------------------------------------------------------------------------------------+");
			System.err.println("|***********************-----Error: Enter Valid Account Number and Password-----************************|");
			System.err.println("+-------------------------------------------------------------------------------------------------------+");
		}
	}
	
	//Sand amount in Other Account.
	public static void fundTransferInOtherAccount() throws SQLException, ClassNotFoundException
	{
		conn=DataBaseConnection.getConnection();
		sql="select balance from profile where account_number = ? and password = ?";
		pst = conn.prepareStatement(sql);
		System.out.println();
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println("|****************************-----Enter credentials for Varification-----*******************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println();	
		System.out.print("Enter Your Account Number: ");
		long first_account = sc.nextLong();
		System.out.print("Enter your Password: ");
		password = sc.next();
		pst.setLong(1, first_account);
		pst.setString(2, password);
		rs=pst.executeQuery();
		while(rs.next())
		{
			balance=rs.getFloat(1);
		}
			float updated_balance = balance;
			System.out.print("Sand Amount : ₹");
	        double sendamount = sc.nextDouble();
	        ///////////
	        sql="select balance from profile where account_number = ? and ifsc = ?";
			pst = conn.prepareStatement(sql);
			System.out.print("Enter Friends Bank Account Number: ");
			long second_account = sc.nextLong();
			System.out.print("Enter friend's Bank IFSC code: ");
			ifsc = sc.next();
			pst.setLong(1, second_account);
			pst.setString(2, ifsc);
			rs=pst.executeQuery();
		while(rs.next())
		{
			balance=rs.getFloat(1);
		}
		float receve_balance = balance;
		////////
        if (sendamount > 0 && sendamount <= updated_balance) 
        {
        	updated_balance -= sendamount;
    		String sql_update = "Update profile set balance = ? where account_number = ?";
    		pst=conn.prepareStatement(sql_update);
			pst.setFloat(1, updated_balance);
			pst.setLong(2,first_account);
			
			i=pst.executeUpdate();
			if(i>0) 
			{
				System.out.println("Amount ₹" + sendamount + " successfully debited form " + first_account);
				// Insert debit transaction record into Transaction table for sender
			    sql = "INSERT INTO Transaction (account_number, date, time, transaction_type, amount) VALUES (?, NOW(),NOW(), ?, ?)";
			    pst = conn.prepareStatement(sql);
			    pst.setLong(1, first_account);
			    pst.setString(2, "Debit"); // Debit for sender
			    pst.setDouble(3, sendamount);
			    pst.executeUpdate();

			}else 
			{
				System.out.println();
				System.err.println("+-------------------------------------------------------------------------------------------------------+");
				System.err.println("|******************************-----Your Transaction Has Been Failed-----*******************************|");
				System.err.println("+-------------------------------------------------------------------------------------------------------+");
			}
        } 
        else 
        {
        	System.err.println("Error: Invalid Amount...");
        }
        				 // // // * // // //
        if (sendamount > 0) 
        {
        	receve_balance += sendamount;
    		String sql_update = "Update profile set balance = ? where account_number = ?";
    		pst=conn.prepareStatement(sql_update);
			pst.setFloat(1, receve_balance);
			pst.setLong(2,second_account);
			i=pst.executeUpdate();
			if(i>0) 
			{
				System.out.println("Amount ₹" + sendamount + " successfully credited to " + second_account);
				// Insert credit transaction record into Transaction table for receiver
			    sql = "INSERT INTO Transaction (account_number, date, time, transaction_type, amount) VALUES (?, NOW(),NOW(), ?, ?)";
			    pst = conn.prepareStatement(sql);
			    pst.setLong(1, second_account);
			    pst.setString(2, "Credit"); // Credit for receiver
			    pst.setDouble(3, sendamount);
			    pst.executeUpdate();
			}else 
			{
				System.out.println();
				System.err.println("+-------------------------------------------------------------------------------------------------------+");
				System.err.println("|******************************-----Your Transaction Has Been Failed-----*******************************|");
				System.err.println("+-------------------------------------------------------------------------------------------------------+");
			}
        } 
        else 
        {
            System.err.println("Error: Invalid amount.");
        }
	}
	//Check User profile.
	public static void userDetails() throws SQLException, ClassNotFoundException
	{
		conn=DataBaseConnection.getConnection();
		sql="select * from profile where account_number = ? and password = ?";
		pst = conn.prepareStatement(sql);
		System.out.println();
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println("|****************************-----Enter credentials for Varification-----*******************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println();	
		System.out.print("Enter Account Number: ");
		account_number = sc.nextLong();
		System.out.print("Enter Your password: ");
		password = sc.next();
		pst.setLong(1, account_number);
		pst.setString(2, password);
		rs=pst.executeQuery();
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println("|*********************************-----Your Account Information-----************************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
		if(rs.next()) 
		{
			System.out.println();
			    	account_number=rs.getLong(1);
			    	name=rs.getString(2);
			    	email=rs.getString(3);
			    	phone= rs.getLong(4);
			    	
			    	address=rs.getString(5);
			    	ifsc=rs.getString(6);
			    	balance=rs.getFloat(7);
			    	password=rs.getString(8);
	
			    	System.out.println("Account Number: "+ account_number);
			    	System.out.println("Name : "+ name);
			    	System.out.println("Email ID: "+ email);
			    	System.out.println("Phone Number: "+ phone);
	
			    	System.out.println("Address: "+ address);
			    	System.out.println("IFSC code: "+ ifsc);
			    	System.out.println("Avilable balance: "+ balance);
			    	System.out.println("Password: "+ password);
			System.out.println();
		}
		else 
		{
			System.out.println();
			System.err.println("+-------------------------------------------------------------------------------------------------------+");
			System.err.println("|***********************-----Error: Enter Valid Account Number and Password-----************************|");
			System.err.println("+-------------------------------------------------------------------------------------------------------+");
		}
	}
	
	//Update user profile.
	public static void update() throws SQLException, ClassNotFoundException
	{
		conn=DataBaseConnection.getConnection();
		sql="select * from profile where account_number = ? and password = ?";
		pst = conn.prepareStatement(sql);
		System.out.println();
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println("|****************************-----Enter credentials for Varification-----*******************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println();			
		System.out.print("Enter Account Number: ");
		account_number = sc.nextLong();
		System.out.print("Enter Your password: ");
		password = sc.next();
		pst.setLong(1, account_number);
		pst.setString(2, password);
		rs=pst.executeQuery();
		
		if(rs.next()) 
		{
			while(true) 
			{
				System.out.println();
				System.out.println("+-------------------------------------------------------------------------------------------------------+");
				System.out.println("|*************************************-----Select the Option-----***************************************|");
		        System.out.println("+-------------------------------------------------------------------------------------------------------+");
				System.out.println();
				System.out.println("1. Change your name.");
				System.out.println("2. Change your email.");
				System.out.println("3. Change your password.");
				System.out.println("4. Go to main menu...");
				System.out.println();
				System.out.print("Option: ");
				int option=sc.nextInt();
				switch (option) {
				case 1:
						System.out.print("Enter new name: ");
						sc.nextLine();
						name=sc.nextLine();
						sql="update profile set name=? where account_number=? and password = ?";
						pst= conn.prepareStatement(sql);
						pst.setString(1,name);
						pst.setLong(2,account_number);
						pst.setString(3, password);
						i=pst.executeUpdate();
						if(i>0) 
						{
							System.out.println("Name is updated successfully...");
						}else 
						{
							System.err.println("Name is not updated...Try again...");
						}
					break;
				case 2:
						System.out.print("Enter new email: ");
						sc.nextLine();
						email=sc.nextLine();
						sql="update profile set email=? where account_number=? and password = ?";
						pst= conn.prepareStatement(sql);
						pst.setString(1,email);
						pst.setLong(2,account_number);
						pst.setString(3, password);
						i=pst.executeUpdate();
						if(i>0) 
						{
							System.out.println("Email is updated successfully...");
						}else 
						{
							System.err.println("Email is not updated...Try again...");
						}
					break;
				case 3:
						System.out.print("Enter new password: ");
						
						String new_password=sc.next();
						sql="update profile set password=? where account_number=? and password = ?";
						pst= conn.prepareStatement(sql);
						pst.setString(1,new_password);
						pst.setLong(2,account_number);
						pst.setString(3, password);
						i=pst.executeUpdate();
						if(i>0) 
						{
							System.out.println("Password is updated successfully...");
						}else 
						{
							System.err.println("Password is not updated...Try again...");
						}
					break;
				case 4: Operation.home();
					break;
				default:
					System.out.println();
		            System.err.println("+-------------------------------------------------------------------------------------------------------+");
					System.err.println("|************************************-----Error: Invalid option-----************************************|");
		            System.err.println("+-------------------------------------------------------------------------------------------------------+");			
		            break;
				}
			}
		}
		else {
			System.out.println();
			System.err.println("+-------------------------------------------------------------------------------------------------------+");
			System.err.println("|***********************-----Error: Enter Valid Account Number and Password-----************************|");
			System.err.println("+-------------------------------------------------------------------------------------------------------+");		
			}
	}
	// Get Mini Statement
	public static void miniStatement() throws SQLException, ClassNotFoundException 
	{
		conn = DataBaseConnection.getConnection();
		//sql = "SELECT * FROM Transaction WHERE account_number = ? ORDER BY date_and_time DESC LIMIT 5";
	    sql = "SELECT * FROM Transaction WHERE account_number = ? and date  BETWEEN ? and ?";
	    pst = conn.prepareStatement(sql);
	    System.out.println();
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println("|****************************-----Enter credentials for Varification-----*******************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println();		
		System.out.print("Enter your Account Number: ");
	    account_number = sc.nextLong();
	   
	    System.out.print("Enter first date: Formate -> YYYY-MM-DD :");
	    String first_date = sc.next();
	    
	    System.out.print("Enter second date: Formate -> YYYY-MM-DD :");
	    String second_date = sc.next();
	    pst.setLong(1, account_number);
	    pst.setString(2, first_date);
	    pst.setString(3, second_date);
	    rs = pst.executeQuery();
	    if (rs.next()) 
	    {
			System.out.println("+-------------------------------------------------------------------------------------------------------+");
			System.out.println("|************************************-----Transaction History-----**************************************|");
	        System.out.println("+-------------------------------------------------------------------------------------------------------+");
			System.out.println();		
	        System.out.println("+-------------------------------------------------------------------------------------------------------+");
	    	System.out.println("|Transaction ID\t\tAccount Number\tDate\t\tTime\t\tTransaction Type\tAmount\t|");
	        System.out.println("+-------------------------------------------------------------------------------------------------------+");
	        do {
	            long transaction_id = rs.getLong("transaction_id");
	            account_number = rs.getLong("account_number");
	            String date = rs.getString("date");
	            String time = rs.getString("time");
	            String transaction_type = rs.getString("transaction_type");
	            float amount = rs.getFloat("amount");

	            System.out.println("|"+ transaction_id + "\t\t\t"+account_number+"\t"+ date +"\t"+time+ "\t"+transaction_type+"\t\t\t" + amount +"\t|");
	    	} while(rs.next());
	        System.out.println("+-------------------------------------------------------------------------------------------------------+");
            Operation.checkBalance1(account_number);
	    } 
	    else {
	        System.err.println("No transactions found for the account number: " + account_number);
	    }
	}
	public static void checkBalance1(long accountNumber) throws SQLException, ClassNotFoundException
	{
		conn=DataBaseConnection.getConnection();
		sql="select account_number, name, balance  from profile where account_number = ?";
		pst = conn.prepareStatement(sql);
		
		pst.setLong(1, account_number);
		System.out.println("+-------------------------------------------------------------------------------------------------------+");
		System.out.println("|***************************************-----Mini Statement-----****************************************|");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
		rs=pst.executeQuery();
		if(rs.next()) 
		{
			System.out.println();
				account_number=rs.getLong(1);
		    	name=rs.getString(2);
		    	balance=rs.getFloat(3);
		    	
		    	System.out.println("Account Number: "+ account_number);
		    	System.out.println("Name : "+ name);
		    	System.out.println("Avilable balance: "+ balance);
			System.out.println();
		}
	}
}
