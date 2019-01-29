package stock;

public class Trade {
	int time;
	String seller;
	String buyer;
	String company;
	int no_of_stocks;
	double price_per_stock;
	
	public void setTrade(int time1,String seller1,String buyer1,String company1,int no_of_stocks1,double price_per_stock1)
	{
		time=time1;
		seller=seller1;
		buyer=buyer1;
		company=company1;
		no_of_stocks=no_of_stocks1;
		price_per_stock=price_per_stock1;
	}
}
