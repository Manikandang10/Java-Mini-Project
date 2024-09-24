import java.util.*;
import java.sql.*;
public class Admin_Process {
	public void admin()
	{
		
		BusDetails bc=new BusDetails();
		Scanner s=new Scanner(System.in);
		try {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/busreservation","root","1234");
		System.out.print("Enter the Admin Id :");
		int id=s.nextInt();
		PreparedStatement st=con.prepareStatement("select * from Admindata where Aid=?");
		
		st.setInt(1, id);
		ResultSet rs=st.executeQuery();
		
		System.out.print("Enter UserName :");
		String usename=s.next();
		
		System.out.print("Enter Password :");
		int pass=s.nextInt();
		
		System.out.print("");
		String correctuse = null;
		int correctpass = 0;
		
		while(rs.next())
		{
			correctuse=rs.getString(3);
			correctpass=rs.getInt(4);
		}
		
		System.out.println("..................................");
		System.out.println();
		
		if(usename.equals(correctuse) && pass==correctpass)
		{
		System.out.println("1.BusDetails\n2.Booking\nSelect One :");
		int choice=s.nextInt();
		
		System.out.println();
			if(choice==1)
			{
				System.out.println("1.AddBus\n2.UpdateBus\n3.Cancel :");
				int ch=s.nextInt();
				
				switch(ch)
				{
					case 1:	
						bc.AddBus();
						break;
					case 2:	
						bc.UpdateBus();
						break;
					case 3:	
						bc.delete();
						break;
					default:
						System.out.println("Enter the valid Choice...");
				}
			}
			else if(choice==2)
			{
				System.out.println("Bus Available");
				Booking_Process bP=new Booking_Process();
				System.out.println("1.Booking\n2.Cancel\nSelect any One :");
				int ch=s.nextInt();
				
				switch(ch)
				{
					case 1:	
						bP.addinfo();
						break;
					case 2:	
						bP.Cancel();
						break;
					default:
						System.out.println("Enter the valid Choice...");
				}
			}
		}
		else
		{
			System.out.println("Incorrect Username And Password!!!");
		}
	}
	catch(Exception e)
	{
		System.out.println(e);	
	}
	}
}
