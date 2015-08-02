package io.github.craftqq.NetIO;

import io.github.craftqq.utility.BidirectionalMap;
import io.github.craftqq.utility.Observable;
import io.github.craftqq.utility.ObservableObserver;
import io.github.craftqq.utility.Observer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server extends Thread implements ObservableObserver
{
	protected ServerSocket server;
	protected boolean running;
	protected HashMap<Integer, ConnectionManager> connections;
	protected BidirectionalMap<Integer, String> clientNames;
	protected int port;
	private int nextID;
	private ArrayList<Observer> observers;
	
	
	public Server()
	{
		this(55055);
	}
	
	public Server(int port_)
	{
		connections = new HashMap<Integer, ConnectionManager>();
		clientNames = new BidirectionalMap<Integer, String>();
		observers = new ArrayList<Observer>();
		port = port_;
		nextID = 0;
	}
	
	private synchronized int nextIdentifier()
	{
		int i = nextID;
		nextID++;
		return i;
	}
	
	public void run()
	{
		try
		{
			server = new ServerSocket(port);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println();
			System.out.println("Could not create Server!");
			return;
		}
		running = true;
		while(running)
		{
			Socket so;
			try
			{
				so = server.accept();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				continue;
			}
			ConnectionManager c = new ConnectionManager(so);
			synchronized(this){
				int i = nextIdentifier();
				clientNames.put(i, "Guest_"+i);
				connections.put(i,c);
			}
			c.subscribe(this);
			c.run();
		}
		for(ConnectionManager c: connections.values())
		{
			c.send("CONNECTION:CLOSE");
			c.closeConnection();
		}
		return;
	}
	
	public void sendAll(String s)
	{
		for(ConnectionManager c: connections.values())
		{
			c.send(s);
		}
	}
	
	public void sendTo(String s, String recipient)
	{
		ConnectionManager c = connections.get(recipient);
		c.send(s);
	}
	
	public void sendExcept(String s, String excluded)
	{
		int i = clientNames.getKey(excluded);
		for(ConnectionManager c:connections.values())
		{
			if(c.getIdentifier() == i)
			{
				continue;
			}
			c.send(s);
		}
	}
	
	public void stopServer()
	{
		running = false;
	}

	public String getNameByID(int id)
	{
		return clientNames.get(id);
	}
	
	public int getIDByName(String s)
	{
		return clientNames.getKey(s);
	}
	
	@Override
	public void subscribe(Observer o) 
	{
		observers.add(o);
	}

	@Override
	public void unsubscribe(Observer o) 
	{
		observers.remove(o);
	}

	@Override
	public void notify(String message, Observable source)
	{
		ConnectionManager c = (ConnectionManager)source;
		int i = c.getIdentifier();
		if(message.toLowerCase().toUpperCase().startsWith("CONNECTION"))
		{
			String s = message.substring(11);
			if(s.equalsIgnoreCase("CLOSE"))
			{
				c.closeConnection();
				connections.remove(i);
				clientNames.removeByKey(i);
			}
			else if(s.toLowerCase().toUpperCase().startsWith("NEWNAME"))
			{
				String name = s.substring(8);
				clientNames.removeByKey(i);
				clientNames.put(i, name);
			}
		}
		String s = "REMOTE:" + message + ";ID:" + i;
		for(Observer o: observers)
		{
			o.notify(s, this);
		}
	}
}