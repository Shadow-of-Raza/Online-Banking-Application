package com.mavan.OnlineBankingApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseConnection 
{
	private static String driver;
	public static String url;
	public static String un;
	public static String pass;
	private static Connection conn;
	
	public static Connection getConnection() throws SQLException
	{
		driver = "com.mysql.cj.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/BankApplication";
		un = "root";
		pass= "frontman";
		try 
		{
			Class.forName(driver);
			 conn = DriverManager.getConnection(url,un,pass);
		} 
		catch (ClassNotFoundException e) 
		{
			System.err.println("Connection failed...");
			e.printStackTrace();
		}
		return conn;
	}
}
