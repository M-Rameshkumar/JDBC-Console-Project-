package com.techpalle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*
 *  Project Name: JDBC Console Project
 *  Description : The main purpose of this  project is to explore 
 *                JDBC apis for performing DML operation
 */



public class JDBCproject {

	public static void main(String[] args) {

		// call creating function - so that it creates table
		Dao d=new Dao();
//		d.creating();
//		d.inserting(1,"ram", 25000); // this will insert first employee
		d.inserting(2, "ramesh", 0);  // this will insert second employee
		
		
	}

}


/*
 * let us take another class for JDBC code
 * 
 * class name: DAO- data access object -also know as DAO layout
 *                  In this class we are going to write code for 5 operation
 *                  
 */


class  Dao{
	
	
	
	
	
    //	in this method we wil create employee table
	
	public void creating(){		
		
		
		Connection c=null;
		Statement s=null;
		
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				 c=DriverManager.getConnection("jdbc:mysql://localhost:3306/palle","root", "ramesh123");
				
				 s=c.createStatement();
				
				s.executeUpdate("CREATE TABLE EMPLOYEE (ID INT PRIMARY KEY AUTO_INCREMENT , ENAME VARCHAR(50),ESALARY BIGINT) "); 
				System.out.println("sucessfully table was created");
				
				} 
			catch (ClassNotFoundException e) { 
			
				System.out.println("Driver is not loaded correctly");
				
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Something went wrong with Database");
				e.printStackTrace();
			}
			finally {
				
				if(s!=null) 
					try {
						s.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
				
				if(c!=null) 
					try {
						c.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		
		
	}
	
	
	// in below methode we wil insert data into employee table
	
	public void inserting(int id,String ename,int esalary) {
		
		PreparedStatement ps=null; //dynamic values(we canot what values gone insert)
		
		Statement s=null;
		
		Connection c=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/palle", "root", "ramesh123");
			
              String query="insert into employee values(?,?,?)"; //for prepeared statement we need prepare first
              
              ps=c.prepareStatement(query);
              
              // now we need replace(?,?,?)
              
              ps.setInt(1, id); //1st ?
              ps.setString(2, ename); // 2nd ?
              ps.setInt(3, esalary); // 3rd ?
              
              ps.executeUpdate();
              System.out.println("sucessfully added!!");
              
			
			
		} catch (ClassNotFoundException e ) { //'e' is a error related varaiable

			System.out.println("Driver is not loaded properly");
			e.printStackTrace();
			System.out.println(e.getCause());//it identity the original exception
			System.out.println(e.getMessage());// it will print root causes ''clear the error message ex:throw new NullPointerException("This is a null pointer exception");
		}
		
		catch (SQLException e) {
			System.out.println("someting went wrong with database");
			
		}
		 catch(Exception e) {
			 //some unknow exception occurs this catch block will execute"this is called as generic exception or generic throw exception"
			 System.out.println("something unusal thing happend");
		 }
		
		finally {
			if(ps!=null) 
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(c!=null)
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	
	
	
}













