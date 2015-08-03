import io.github.craftqq.NetIO.ConnectionClient;
import io.github.craftqq.NetIO.ConnectionServer;
import io.github.craftqq.utility.Observable;
import io.github.craftqq.utility.Observer;

import java.time.LocalTime;



public class Main 
{

	public static void main(String[] args) 
	{
		boolean isServer = false;
		String host = "";
		String arg = args[0];
			if(arg.equalsIgnoreCase("-server")||arg.equalsIgnoreCase("/server")||arg.equalsIgnoreCase("server"))
			{
				isServer = true;
			}
			else
			{
				host = arg;
			}
		ConnectionServer s;
		ConnectionClient c;
		HalloHalloechen protokoll = new HalloHalloechen();
		if(isServer)
		{
			s = new ConnectionServer();
			s.start();
			s.subscribe(protokoll);
		}
		else
		{
			c = new ConnectionClient(host);
			c.subscribe(protokoll);
			c.start();
			try
			{
				Thread.sleep(50);
			}
			catch(InterruptedException IE)
			{
				
			}
			c.send("hallo");
		}
		try
		{
			Thread.sleep(5000);
		}
		catch(InterruptedException IE)
		{
			
		}
		System.exit(0);
	}
	
	public static void log(String message)
	{
		System.out.println("["+LocalTime.now() +"]"+message);
	}
}
class HalloHalloechen implements Observer
{

	@Override
	public void notify(String message, Observable source) 
	{
		Main.log(message);
		if(source instanceof ConnectionServer)
		{
			ConnectionServer s = (ConnectionServer)source;
			s.sendAll("Hallöchen");
		}
		else
		{
			ConnectionClient c = (ConnectionClient) source;
			c.closeConnection();
		}
		
	}
	
	
}
