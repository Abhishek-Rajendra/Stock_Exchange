package stock;

import java.util.*;


public class Stock {
	
	public  int timeid=1;	//Here time is like id, not the real time
	
	public ArrayList<Order> sellorder = new ArrayList<Order>();
	public ArrayList<Order> buyorder = new ArrayList<Order>();
	public ArrayList<Trade> trade= new ArrayList<Trade>();
	
/*---------------------------------------------------------------------
				Print trades
---------------------------------------------------------------------*/	
	public void print_trade()
	{
		System.out.print("time\tSeller\tBuyer\tCompany\tStocks sold\tPrice per stock\tTotal Cost\n");
		for(int i=0;i<trade.size();i++)
		{
			System.out.print(trade.get(i).time + "\t" + trade.get(i).seller + "\t" + trade.get(i).buyer + "\t" + trade.get(i).company 
			+ "\t" + trade.get(i).no_of_stocks + "\t\t" + trade.get(i).price_per_stock + "\t\t" + trade.get(i).no_of_stocks*trade.get(i).price_per_stock + "\n");
		}
		if(trade.size()==0)
		{
			System.out.print("No Trades so far\n");
		}
	}

/*---------------------------------------------------------------------
				Print sellorder
---------------------------------------------------------------------*/	
	public void print_sellorder()
	{
		System.out.print("time\tSeller\tCompany\tNumber of Stocks\tPrice per stock\n");
		for(int i=0;i<sellorder.size();i++)
		{
			System.out.print(sellorder.get(i).time + "\t" + sellorder.get(i).client + "\t" + sellorder.get(i).company 
			+ "\t" + sellorder.get(i).no_of_stocks + "\t\t\t" + sellorder.get(i).price_per_stock + "\n");
		}
		if(sellorder.size()==0)
		{
			System.out.print("No Sell order\n");
		}
	}
/*---------------------------------------------------------------------
				Print buyorder
---------------------------------------------------------------------*/	
	
	public void print_buyorder()
	{
		System.out.print("time\tBuyer\tCompany\tNumber of Stocks\tPrice per stock\n");
		for(int i=0;i<buyorder.size();i++)
		{
			System.out.print(buyorder.get(i).time + "\t" + buyorder.get(i).client + "\t" + buyorder.get(i).company 
			+ "\t" + buyorder.get(i).no_of_stocks + "\t\t\t" + buyorder.get(i).price_per_stock + "\n");
		}
		if(buyorder.size()==0)
		{
			System.out.print("No Buy order\n");
		}
	}

/*---------------------------------------------------------------------
				Adding New Order
---------------------------------------------------------------------*/	
	public void Addorder(String type,String client,String company,double price,int no_of_stocks)
	{
		Order order= new Order(timeid,client,company,no_of_stocks,price);

		if(type.equals("sell"))
		{
			sellorder.add(order);
			executeOrders(type,sellorder.size()-1);			//Executing orders
		}
		else if(type.equals("buy"))
		{
			buyorder.add(order);
			executeOrders(type,(buyorder.size()-1));		//Executing orders

		}else
		{
			System.out.print("INVALID TYPE\n");
		}
		timeid++;		//Increment after every addition
	}

/*---------------------------------------------------------------------
				Executing valid orders
---------------------------------------------------------------------*/	
	protected void executeOrders(String type,int pos)
	{
		int result=0;		
		
		if(type.equals("sell"))
		{
			for(int i=0;i<buyorder.size();i++) 	//looping through buyerorder when type added is sell
			{
				Trade done= new Trade();		//declaring and initialization of trade list
				
				if(buyorder.get(i).company.equals(sellorder.get(0).company) && buyorder.get(i).time <=sellorder.get(pos).time && buyorder.get(i).price_per_stock >=sellorder.get(pos).price_per_stock)
				{
					if(buyorder.get(i).no_of_stocks > sellorder.get(i).no_of_stocks)	//If Buyer's number of stocks is high
					{
						result=buyorder.get(i).no_of_stocks-sellorder.get(i).no_of_stocks;
						buyorder.get(i).setstock(result);
						
						//Adding values into Trade object done, which is later added to trade global object
						
						done.setTrade(sellorder.get(pos).time,sellorder.get(pos).client,buyorder.get(i).client,buyorder.get(i).company,sellorder.get(i).no_of_stocks,sellorder.get(pos).price_per_stock);
						
						//Printing the trade made
//						System.out.print("time: " + sellorder.get(pos).time + "|Seller: " + sellorder.get(pos).client + "|Buyer: " + buyorder.get(i).client 
//						+ "|Company: " + buyorder.get(i).company + "|Stocks Sold: " + sellorder.get(i).no_of_stocks
//						+ "|price Per stock: " + sellorder.get(pos).price_per_stock +"\n");
//						
						sellorder.remove(pos);		//Removing the recent added object from the sellers list
						trade.add(done);
						break;
					}
					else if(buyorder.get(i).no_of_stocks < sellorder.get(pos).no_of_stocks)		//If seller's number of stocks is high
					{
						result=sellorder.get(pos).no_of_stocks-buyorder.get(i).no_of_stocks;
						sellorder.get(pos).setstock(result);
						
						//Adding values into Trade object done, which is later added to trade global object
						
						done.setTrade(sellorder.get(pos).time,sellorder.get(pos).client,buyorder.get(i).client,buyorder.get(i).company,buyorder.get(i).no_of_stocks,sellorder.get(pos).price_per_stock);
						
						//Printing the trade made						
//						System.out.print("time: " + sellorder.get(pos).time + "|Seller: " + sellorder.get(pos).client + "|Buyer: " + buyorder.get(i).client
//						+ "|Company: " + buyorder.get(i).company + "|Stocks Sold: " + buyorder.get(i).no_of_stocks +
//						"|price Per stock: " + sellorder.get(pos).price_per_stock+"\n");
						
						buyorder.remove(i);		//Removing from buyer's list
						i=i-1;
					}
					else																		//If number of stocks is same for both
					{
						//Adding values into Trade object done, which is later added to trade global object
						
						done.setTrade(sellorder.get(pos).time,sellorder.get(pos).client,buyorder.get(i).client,buyorder.get(i).company,sellorder.get(i).no_of_stocks,sellorder.get(pos).price_per_stock);
						
						//Printing the trade made						
//						System.out.print("time: " + sellorder.get(pos).time + "|Seller: " + sellorder.get(pos).client + "|Buyer: " + buyorder.get(i).client 
//						+ "|Company: " + buyorder.get(i).company + "|Stocks Sold: " + sellorder.get(i).no_of_stocks 
//						+ "|price Per stock: " + sellorder.get(pos).price_per_stock+"\n");
						
						//Removing from both the list (i.e. seller and buyer
						
						sellorder.remove(pos);
						buyorder.remove(i);
						i=i-1;
					}
				}
				trade.add(done);
			}
		}else if(type.equals("buy"))
		{	
			for(int i=0;i<sellorder.size();i++) 	//looping through sellorder when type added is sell
			{
				Trade done= new Trade();		//declaring and initialization of trade list
				
				if(sellorder.get(i).company.equals(buyorder.get(pos).company) && sellorder.get(i).time <=buyorder.get(pos).time && buyorder.get(pos).price_per_stock >=sellorder.get(i).price_per_stock)
				{
					if(sellorder.get(i).no_of_stocks > buyorder.get(pos).no_of_stocks)		//If seller's number of stocks is high
					{
						result=sellorder.get(i).no_of_stocks-buyorder.get(pos).no_of_stocks;
						sellorder.get(i).setstock(result);
						
						//Adding values into Trade object done, which is later added to trade global object
						
						done.setTrade(buyorder.get(pos).time,sellorder.get(i).client,buyorder.get(pos).client,buyorder.get(pos).company,buyorder.get(pos).no_of_stocks,sellorder.get(i).price_per_stock);
						
						//Printing the trade made						
//						System.out.print("time: " + buyorder.get(pos).time + "|Seller: " + sellorder.get(i).client + "|Buyer: " + buyorder.get(pos).client
//						+ "|Company: " + buyorder.get(pos).company + "|Stocks Sold: " + buyorder.get(pos).no_of_stocks
//						+ "|price Per stock: " + sellorder.get(i).price_per_stock+"\n");
						
						buyorder.remove(pos);		//Removing the recent added object from the buyers list
						trade.add(done);
						break;
					}
					else if(sellorder.get(i).no_of_stocks < buyorder.get(pos).no_of_stocks)		//If Buyer's number of stocks is high
					{
						result=buyorder.get(pos).no_of_stocks-sellorder.get(i).no_of_stocks;
						buyorder.get(pos).setstock(result);
						
						//Adding values into Trade object done, which is later added to trade global object
						
						done.setTrade(buyorder.get(pos).time,sellorder.get(i).client,buyorder.get(pos).client,buyorder.get(pos).company,sellorder.get(i).no_of_stocks,sellorder.get(i).price_per_stock);
						
						
						//Printing the trade made						
//						System.out.print("time: " + buyorder.get(pos).time + "|Seller: " + sellorder.get(i).client + "|Buyer: " + buyorder.get(pos).client 
//						+ "|Company: " + buyorder.get(pos).company + "|Stocks Sold: " + sellorder.get(i).no_of_stocks 
//						+ "|price Per stock: " + sellorder.get(i).price_per_stock+"\n");
						
						sellorder.remove(i);		//Removing from seller's list
						i=i-1;
					}
					else																		//If number of stocks is same for both
					{
						//Adding values into Trade object done, which is later added to trade global object
						
						done.setTrade(buyorder.get(pos).time,sellorder.get(i).client,buyorder.get(pos).client,buyorder.get(pos).company,sellorder.get(i).no_of_stocks,sellorder.get(i).price_per_stock);
						
						//Printing the trade made						
//						System.out.print("time: " + buyorder.get(pos).time + "|Seller: " + sellorder.get(i).client + "|Buyer: " + buyorder.get(pos).client 
//						+ "|Company: " + buyorder.get(pos).company + "|Stocks Sold: " + sellorder.get(i).no_of_stocks 
//						+ "|price Per stock: " + sellorder.get(i).price_per_stock+"\n");
						
						//Removing from both the list (i.e. seller and buyer

						buyorder.remove(pos);
						sellorder.remove(i);
						i=i-1;
					}
				}
				trade.add(done);	//Adding the trade into the array list
			}
		}
			
	}	

}

