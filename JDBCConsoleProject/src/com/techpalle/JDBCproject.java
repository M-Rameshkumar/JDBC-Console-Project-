package com.techpalle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;


/*
 *  Project Name: JDBC Console Project
 *  Description : The main purpose of this  project is to explore 
 *                JDBC apis for performing DML operation
 */



public class JDBCproject {
	
	public static int count=1; //static variable accessed by only static method

	public static void main(String[] args) {
		
		/*
		 * let ask user input...
		 */
		
		Scanner s= new Scanner(System.in);
		
		Dao d=new Dao();
		
		int option;
		int pos;
		
		
		try {
			
		
		System.out.println("WELCOME TO JDBC CONSOLE PROJECT");
		do {
			
			System.out.println("------------------CHOOSE CORRECT OPTION------------------------------");
			System.out.println("1: creating table");
			System.out.println("2: inserting table");
			System.out.println("3: update the row of table");
			System.out.println("4: delete a row of table");
			System.out.println("5: read all rows");
			System.out.println("6: read one rows");
			System.out.println("0: exit");
			
			option=s.nextInt();
			
			
			
			switch(option) {
	
			
			
			
			
			
			case 6:
									try {
										System.out.println("Enter the Eid for disply:");
										pos=s.nextInt();
										
										if(pos>0) {
											d.display(pos);
										}
										else {
											System.out.println("invalid index");
										}
										
									}
									catch(InputMismatchException e) {
										System.out.println("INVALID INPUT");
									}
								
								break;
			
			
			case 5:
		
				d.displayAll();
				break;
			
			
			
			case 4:
				
								try {
									System.out.println("enter eid for");
									pos=s.nextInt();
									
									if(pos>=0) {
									
									d.deleting(pos);
									}
									else {
										System.out.println("Invalid Index");
									}
									
									
								}
								catch(InputMismatchException e) {
									System.out.println("INVALID INPUT");
								}
								
								break;
			
			
			
			
			case 3:
				
								try {	
									//we have read eno and new salary from keyboard
									
									System.out.println("enter eno and new salary...");
									int no=s.nextInt();
									s.nextLine();
									
									int newsal=s.nextInt();
									
									d.updating(no, newsal);
								}
								
								
								catch(InputMismatchException e) {
									System.out.println("Invalid Input");
									s.nextLine();
								}
									catch(Exception e) {
										System.out.println("Something went wrong ..try..again");
										
									}
								break;
			
			case 2:
							System.out.println("ENTER EN0 AS INT ,ENAME AS STRING ,ESAL AS INT SEQUENTIALLY...");
									try {
										int eno=s.nextInt(); //user enters a number into eno
										//let us remove extra enter key 1.number 2.name use this
										
										s.nextLine();
										
										String ename=s.next();//user enters string into ename
										
										int esal=s.nextInt(); //user enters a number into esal
										
										System.out.println("OK INSERTING...");
					
									d.inserting(eno, ename, esal);
						     	   }
									
									catch(InputMismatchException e)
									{
										System.out.println(" INVALID INPUT");
										s.nextLine(); // Clear the scanner buffer in case of invalid input
									}
									catch(Exception e) {
										System.out.println(e.getMessage());
									}
								
							break;
			
	   
			case 1:
								try {
						
				                if(count==1) {	
				                
				                	System.out.println("OK I AM CREATING...");
				                	d.creating();
				                	count++;
				                	
				                }
				                else {
				                	System.out.println("we canot create same table twice");
				                }
				                
								
								} catch(InputMismatchException e) { 
									
									System.out.println("Please Enter applicable data");
								}
								break;
							
				
			
			
			case 0:
								System.out.println("OK I AM EXITING... ");
								
								try {
									Thread.sleep(3000);
									
									
								} catch (InterruptedException e) {
									
									
				                     System.out.println("SOME ISSUE WHILE EXITING");
									 e.printStackTrace();
								}
								break;
								
								default:
									System.out.println("Choose valid option");
					              
							
						
						
						}
						
						
					}
					while(option!=0);
					}
						catch(InputMismatchException e) {
							
							s.nextLine();
						}


		
		
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
	
	
	
	//below we create for avoiding code duplication
	
	
		
		String Driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/palle";
		String user="root";
		String pass="ramesh123";
		
		

	
	
    //	in this method we wil create employee table
	
	
	
	public void creating(){		
		
		
		Connection c=null;
		Statement s=null;
		
			
			try {
				Class.forName(Driver);
				
				 c=DriverManager.getConnection(url,user, pass);
				
				 s=c.createStatement();
				
				s.executeUpdate("CREATE TABLE EMPLOYEE (ID INT PRIMARY KEY AUTO_INCREMENT , ENAME VARCHAR(50),ESALARY BIGINT) "); 
				System.out.println("sucessfully table was created...");
				
				} 
			catch (ClassNotFoundException e) { 
			
				System.out.println("Driver is not loaded correctly");
				
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Something went wrong with Database");
				System.out.println(e.getMessage());
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
			Class.forName(Driver);
			
			c=DriverManager.getConnection(url,user,pass);
			
              String query="insert into employee values(?,?,?)"; //for prepeared statement we need prepare first
              
              ps=c.prepareStatement(query);
              
              // now we need replace(?,?,?)
              
              ps.setInt(1, id); //1st ?
              ps.setString(2, ename); // 2nd ?
              ps.setInt(3, esalary); // 3rd ?
              
              ps.executeUpdate();
              
            
              
              System.out.println("Sucessfully Inserted...");
              
			
			
		} catch (ClassNotFoundException e ) { //'e' is a error related varaiable

			System.out.println("Driver is not loaded properly");
			e.printStackTrace();
			System.out.println(e.getCause());//it identity the original exception
			System.out.println(e.getMessage());// it will print root causes ''clear the error message ex:throw new NullPointerException("This is a null pointer exception");
		}
		
		catch (SQLException e) {
			System.out.println("someting went wrong with database");
			System.out.println(e.getMessage());
			
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
	
	
	//in below method we will update ...for a given employee no update new salary
	
	
	public void updating(int eno,int newsal) {
		
		Connection c=null;
		PreparedStatement ps= null;
		
		              try {
						Class.forName(Driver);
						c=DriverManager.getConnection(url,user,pass);
						
						
						String query="update employee set ESALARY=? where ID=?"; 
						
						ps=c.prepareStatement(query);
						
						ps.setInt(1, newsal);
						ps.setInt(2, eno);
						
						
						int rowAffected=ps.executeUpdate(); //executeUpdate return affected rows 
						//It returns an integer value indicating the number of rows that were affected by the query.
						
						if(rowAffected >0) {
							System.out.println("Successfully Updated...");
						
						}
						else {
							System.out.println("No employee found with id:"+eno);
						}
						
						
					
						
					} catch (ClassNotFoundException e) {
						
						System.out.println("Driver is not loaded");
  					} catch (SQLException e) {
						// TODO Auto-generated catch block
  						System.out.println(" something wrong with database");
					}
		              finally {
		            	  try {
		            		if(ps!=null) {  
		            			ps.close();
		            		}
		            		if(c!=null) {
		            			c.close();
		            		}
								
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            	  
		              }
		
	}
	


//below method we can delete a particular record 
	public void deleting(int eid) {
		
		PreparedStatement ps=null;
		Connection c=null;
		
		try {
			Class.forName(Driver);
			
			c=DriverManager.getConnection(url, user, pass);
			
			
			String query="DELETE FROM EMPLOYEE WHERE ID=?";
			
			ps=c.prepareStatement(query);
			
			ps.setInt(1, eid);
			
			 int count=ps.executeUpdate();
			if(count>0) {
				System.out.println("SUCESSFULLY DELETED RECORD...");
			}
			else {
				System.out.println("Index out of limit: "+eid);
			}
			
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("DRIVER NOT LOADED");
			e.printStackTrace();
		} catch (SQLException e) {

			System.out.println("SOMETHING ISSUE IN DATABASE");
			e.printStackTrace();
		}
		
		
		finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		
	}

	
	
	
	//below method we will read all employee details and display
	
	
	public void displayAll() {
		
		Connection c= null;
		Statement s=null;
		
		
        try {
			Class.forName(Driver);
			c=DriverManager.getConnection(url,user,pass);
			
			s=c.createStatement();
			
			
		   ResultSet res=s.executeQuery("select * from employee"); //data stored in 'res'
		
		  System.out.println("\n-------------------EMPLOYEE TABLE DATA BELOE---------------------------\n");
		  System.out.println("ID          ENAME          ESALARY");
		  System.out.println("--          -----          --------");
		  
		
		   while(res.next()) {
			   
			   System.out.println(res.getInt(1) +" "+ res.getString(2)+" "+res.getInt(3)); 
			 
		   }
		   System.out.println("-----------------------------------------------------------------------------");
		
		
    	
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver is not Loaded");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Sql connection issues");
			e.printStackTrace();
		}
        
       finally {
    	   try {
    		   if(s!=null) {
    			   s.close();
    		   }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
    	   try {
    		   if(c!=null) {
    			   c.close();
    		   }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
        
	}
	
	
	//below method for display particular record
	
	public void display(int eid) { 
		
		
		PreparedStatement ps=null;
		Connection c=null;
		
		try {
			Class.forName(Driver);
			c=DriverManager.getConnection(url, user, pass);
			
			
			String query="SELECT * FROM EMPLOYEE WHERE ID=?";
			
			
	
			ps=c.prepareStatement(query);
			
			ps.setInt(1, eid);
		
		   ResultSet res= ps.executeQuery();
		   
		   
		   
		   if(res.next()) {
			   System.out.println(res.getInt(1)+"  "+res.getString(2)+"  "+res.getInt(3));
			   System.out.println("Sucessfully Displayed...");
		   }
		   else {
			   System.out.println("Index out of limit:"+eid);
		   }
		   
			
			
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("DRIVER NOT LOADED");

			e.printStackTrace();
		} catch (SQLException e) {
			
			System.out.println("SOME ISSUE IN DATABASE");
			e.printStackTrace();
		}
		
		
		finally {
			
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
	}
	

	
	
	
}







