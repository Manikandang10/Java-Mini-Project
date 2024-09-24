import java.util.*;
public class BusReservation {

	public static void main(String[] args) {
		BusDetails bc=new BusDetails();
		Admin_Process ap=new Admin_Process();
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("1.Admin\n2.Booking\nSelect any one :");
		int choice=scanner.nextInt();
		
		if(choice==1)
		{
			ap.admin();
			
		}
		else if(choice==2)
		{
			System.out.println("-------Bus Available--------");
			Booking_Process bP=new Booking_Process();
			System.out.println();
			System.out.println("1.New Booking\n2.Cancel Booking\nSelect any one :");
			int ch=scanner.nextInt();
			
			switch(ch)
			{
			case 1:
				bP.addinfo();
				break;
			case 2:
				bP.Cancel();
				break;
			default:
				System.out.println("Invalid Choice.....");
			}
		}
		else
		{
			System.out.println("Invalid Choice....");
		}
		

		
	}

}
