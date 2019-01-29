package stock;

public class Order {
	int time;
	String client;
	String company;
	int no_of_stocks;
	double price_per_stock;
	
	public Order(int time1,String client1,String company1,int no_of_stocks1,double price_per_stock1)
	{
		time=time1;
		client=client1;
		company=company1;
		no_of_stocks=no_of_stocks1;
		price_per_stock=price_per_stock1;
	}
	public void setstock(int nostock)
	{
		no_of_stocks=nostock;
	}
	public String getClient()
	{
		return client;
	}

}
