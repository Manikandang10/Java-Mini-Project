import java.util.*;
import java.sql.*;
public class Booking_Process 
{
	String Name,Date;
	int id,BusNumber,Age;
	long IdProof  ;
	Scanner s=new Scanner(System.in);
	public Booking_Process() {
		try {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/busreservation","root","1234");
		PreparedStatement st=con.prepareStatement("select * from busdetails");
		
		ResultSet rs=st.executeQuery();
		
		while(rs.next()) 
		{
			System.out.println("BusNo :"+rs.getInt(1));
			System.out.println("BusType :"+rs.getString(2));
			System.out.println("From_Location :"+rs.getString(4));
			System.out.println("To_Location :"+rs.getString(5));
			System.out.println("Available Seat :"+rs.getInt(6));
			System.out.println(".............................................");
		}
		st.close();
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void addinfo()
	{
		try {
			System.out.println("Entet the BusNumber :");
			int busno=s.nextInt();
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/busreservation","root","1234");
			PreparedStatement st=con.prepareStatement("select * from busdetails where busno=?");
			
			st.setInt(1, busno);
			ResultSet rs=st.executeQuery();
			
			int count=0;
			while(rs.next())
			{
				count=rs.getInt(6);
			}
			System.out.println("Available Seat :"+count);
		
			if(count>0)
			{
				try
				{	
					int choice; 
					do {
					System.out.print("Entet Your Name :");
					Name =s.next();
					System.out.print("Enter Your BusNumber :");
					System.out.println(busno);
					System.out.print("Enter Your Age :");
					Age=s.nextInt();
					System.out.print("Enter AadhaarNumber:");
					IdProof  =s.nextLong();
					System.out.print("Entet Travaling Date :");
					Date=s.next();
					
					
					Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/busreservation","root","1234");
					PreparedStatement st1=con1.prepareStatement("Insert into passengerdetails values(?,?,?,?,?,?)");
					st1.setInt(1, id);
					st1.setString(2,Name);
					st1.setString(3,Date);
					st1.setInt(4,busno);
					st1.setInt(5,Age);
					st1.setLong(6,IdProof  );
					
					int y=st1.executeUpdate();
					if(y>0) {
					System.out.println("Data Saved....\n");
					count--;
					}
					else
					{
						System.out.println("Data not Saved....\n");
					}
					st1.close();
					System.out.println("Available Seat :"+count);
					
					PreparedStatement st2=con.prepareStatement("Update busdetails set BusCapacity=? where BusNo=?");
					
					st2.setInt(1, count);
					st2.setInt(2, busno);
					
					st2.executeUpdate();
					
					System.out.print("Entet 1 for Another Booking :");
					choice=s.nextInt();
					}
					while(choice==1);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				
			}
			else
			{
				System.out.println("No Sheat in the Bus Try Another Bus......");
			}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			
	}
	
	void Cancel()
	{
		try {
		System.out.print("Enter Your IDProof :");
		IdProof=s.nextLong();
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/busreservation","root","1234");
		PreparedStatement st=con.prepareStatement("select * from passengerdetails where IdProof=?");
		
		st.setLong(1,IdProof);
		
		ResultSet rs=st.executeQuery();
		int c=0;
		
		while(rs.next())
		{
			System.out.println("Passenger ID :"+rs.getInt(1));
			System.out.println("Passenger Name :"+rs.getString(2));
			System.out.println("Passenger Date :"+rs.getString(3));
			System.out.println("Passenger BusNumber :"+rs.getInt(4));
			System.out.println("Passenger Age :"+rs.getInt(5));
			System.out.println("Passenger IdProof :"+rs.getLong(6));
			System.out.println(".............................................");
			   
			c=1;
		}
		if(c==0) {
			System.out.println("Inavlid ID Proof..");
		}
		
		
		System.out.print("Enter Your ID :");
		id=s.nextInt();
		System.out.print("Enter Your BusNumber :");
		BusNumber=s.nextInt();
		PreparedStatement st1=con.prepareStatement("delete from passengerdetails where id=? and BusNumber=?");
		
		st1.setInt(1,id);
		st1.setInt(2,BusNumber);
		int r=st1.executeUpdate();		
		if(r>0) {
			System.out.println("Cancelation  Complete.....");
			
			PreparedStatement st2=con.prepareStatement("select * from busdetails where busno=?");
			
			st2.setInt(1,BusNumber);
			
			ResultSet rs1=st2.executeQuery();
			int buscap=0;
			
			
			while(rs1.next())
			{
				buscap=rs1.getInt(6);
			}
			
			int upadate=buscap+1;
			if(buscap>0) {
			PreparedStatement st3=con.prepareStatement("Update busdetails set BusCapacity=? where BusNo=?");
			
			st3.setInt(1, upadate);
			st3.setInt(2, BusNumber);
			
			st3.executeUpdate();
			st3.close();
			}
			
		}
		else
		{
			System.out.println("Inavlid ID ..");
		}
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
