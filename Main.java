package stock;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		
		Stock stock=new Stock();
		while(true) 
		{
			System.out.print("1: Add order\n2: View Trades\n3: View Sellers Order\n4: Veiw Buyers Order\n5: Exit\n"); 
			Scanner input = new Scanner(System.in);
			System.out.print("Enter the option: ");
	    	int x = input.nextInt();
	    	
	    	if(x==1)
	    	{
	    		System.out.print("Enter Type of Transaction(sell/buy):");
	    		String type=input.next();
	    		System.out.print("Enter your Name:");
	    		String name=input.next();
	    		System.out.print("Company that Stock belongs to:");
	    		String company=input.next();
	    		System.out.print("Price Per Stock:");
	    		int price=input.nextInt();
	    		System.out.print("Number of Stocks(integer):");
	    		int stocks=input.nextInt();
	    		stock.Addorder(type,name,company,price,stocks);	
	    	}
	    	if(x==2)
	    	{
	    		stock.print_trade();
	    	}
	    	if(x==3)
	    	{
	    		stock.print_sellorder();
	    	}
	    	if(x==4)
	    	{
	    		stock.print_buyorder();
	    	}
			if(x>=5)
				break;
			System.out.print("\n");
		}

	}

}
