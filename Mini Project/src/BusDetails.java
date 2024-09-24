import java.util.*;
import java.sql.*;
public class BusDetails {
	Scanner scanner=new Scanner(System.in);
	int busno,Buscapacity,c;
	String bustype,Drivername,Fromlocation,Tolocation;
	 void AddBus()
	 {
		 try
		 {	
			int choice=0; 
			 do {
				 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/busreservation","root","1234");
				 System.out.print("Enter the Bus Number :");
				 busno=scanner.nextInt();
				 
				 PreparedStatement st2=con.prepareStatement("Select * from busdetails where busno=?");
				 
				 st2.setInt(1, busno);
				 
				 ResultSet rs=st2.executeQuery();
				 
				 while(rs.next())
				 {
					 if(rs.getInt(1)==busno) {
						 c++;
						 break;
					 }
				 }
				 
				 if(c!=0)
				 {
					 System.out.println("Bus Already Registered......");
					 System.out.print("Entet 1 for Add New Bus :");
					 choice=scanner.nextInt();
				 }
				 else {
				 System.out.print("Enter the Bus Type AC/NON-AC :");
				 bustype=scanner.next();
				 System.out.print("Enter Bus DriverName :");
				 Drivername=scanner.next();
				 System.out.print("Enter From Location :");
				 Fromlocation=scanner.next();
				 System.out.print("Enter To Location");
				 Tolocation=scanner.next();
				 System.out.print("Enter Bus Capacity");
				 Buscapacity=scanner.nextInt();
				 
				
				 PreparedStatement st=con.prepareStatement("Insert into busdetails values(?,?,?,?,?,?)");
				 
				 st.setInt(1, busno);
				 st.setString(2,bustype);
				 st.setString(3,Drivername);
				 st.setString(4,Fromlocation);
				 st.setString(5,Tolocation);
				 st.setInt(6,Buscapacity  );
				 
				 st.executeUpdate();
				 st.close();
				 
				 System.out.println("Data Saved....\n");
				 System.out.print("Entet 1 for Add Another Bus :");
				 choice=scanner.nextInt();
				 }
			 }
			 while(choice==1);
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
	 }
	 void UpdateBus()
	 {
		 String Bustype,Drivername,From_Location,ToLocation ;
		 try
		 {
			 int choice=0;
			 do {
				 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/busreservation","root","1234");
				 System.out.print("Enter the Bus Number :");
				 busno=scanner.nextInt();
				 
				 PreparedStatement st2=con.prepareStatement("Select * from busdetails where busno=?");
				 
				 st2.setInt(1, busno);
				 
				 ResultSet rs=st2.executeQuery();
				 
				 while(rs.next())
				 {
					 if(rs.getInt(1)==busno) {
						 c++;
						 break;
					 }
				 }
				 
				 if(c==0)
				 {
					 System.out.println("\nBus Not Found !!!!");
					 System.out.println("...................................\n");
					 System.out.print("Entet 1 for Retry :");
					 choice=scanner.nextInt();
				 }
				 else
				 {
				 System.out.print("1.Change Type\n2.DriverName\n3.Location\nChoose Any One :");
				 int num=scanner.nextInt();
				 
				 switch(num)
				 {
				 case 1:
					 System.out.print("Enter the New BusType :");
					 Bustype=scanner.next();					 
					 
					 PreparedStatement st=con.prepareStatement("Update busdetails set BusType=? where BusNo=?");
					 
					 st.setString(1, Bustype);
					 st.setInt(2, busno);
					 
					int a= st.executeUpdate();
					
					if(a>0)
					{
						System.out.println("Successfully Updated.....");
						System.out.println("...................................");
					}
					else
					{
						System.out.println("Not Updated.....");
						System.out.println("...................................");
					}
					 st.close();
					 break;
				 case 2:
					 System.out.print("Enter the New Drivername :");
					 Drivername=scanner.next();					 
					 
					 PreparedStatement st1=con.prepareStatement("Update busdetails set Drivername=? where BusNo=?");
					 
					 st1.setString(1, Drivername);
					 st1.setInt(2, busno);
					 
					int a1= st1.executeUpdate();
					
					if(a1>0)
					{
						System.out.println("Successfully Updated.....");
						System.out.println("...................................");
					}
					else
					{
						System.out.println("Not Updated.....");
						System.out.println("...................................");
					}
					 st1.close();
					 break;
				 case 3:
					 System.out.print("Enter the From_Location :");
					 From_Location =scanner.next();		
					 
					 System.out.print("Enter the ToLocation :");
					 ToLocation =scanner.next();
					 
					 PreparedStatement st11=con.prepareStatement("Update busdetails set From_Location=?,ToLocation=? where BusNo=?");
					 
					 st11.setString(1, From_Location);
					 st11.setString(2, ToLocation);
					 st11.setInt(3, busno);
					 
					int a11= st11.executeUpdate();
					
					if(a11>0)
					{
						System.out.println("Successfully Updated.....");
						System.out.println("...................................");
					}
					else
					{
						System.out.println("Not Updated.....");
						System.out.println("...................................");
					}
					 st11.close();
					 break;
				 default:
					System.out.println("Entet the Valid Choice.....");
				 }
				 
				 System.out.print("Entet 1 for Update Another Bus :");
				 choice=scanner.nextInt();
			 }
			 }
			 while(choice==1);
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
		 
	 }
	 void delete()
	 {
		 try {
		 System.out.print("Enter Bus Number\nto Remove Form the DataBase :");
		 int buseno = scanner.nextInt();
		 
		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/busreservation","root","1234");
		 PreparedStatement st=con.prepareStatement("delete from  busdetails where BusNo=?");
		 
		 st.setInt(1, buseno);
		 int c= st.executeUpdate();
		 
		 if(c>0)
			{
				System.out.println("Successfully Removed.....");
				System.out.println("...................................");
			}
			else
			{
				System.out.println("Not Removed.....");
				System.out.println("...................................");
			}
		 }
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
	 }
}
