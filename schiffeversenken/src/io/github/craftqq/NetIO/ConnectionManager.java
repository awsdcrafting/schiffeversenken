package io.github.craftqq.NetIO;

import io.github.craftqq.utility.Observable;
import io.github.craftqq.utility.Observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionManager extends Thread implements Observable
{
	protected Socket socket;
	protected PrintWriter pw;
	protected BufferedReader br;
	private boolean alive;
	private int identifier;
	protected ArrayList<Observer> observers;
	
	public ConnectionManager(Socket socket_, int identifier_)
	{
		socket = socket_;
		identifier = identifier_;
		observers = new ArrayList<Observer>();
	}
	
	public ConnectionManager(Socket client)
	{
		this(client, 0);
	}
	
	public int getIdentifier()
	{
		return identifier;
	}
	
	public void closeConnection()
	{
		try {
			socket.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		pw.close();
		try 
		{
			br.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		alive = false;
	}
	
	public void send(String s)
	{
		pw.println(s);
	}
	
	public void run()
	{
		try
		{
			pw = new PrintWriter(socket.getOutputStream(), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println();
			System.out.println("Error while connecting");
			return;
		}
		
		alive = true;
		while(alive)
		{
			String s = "";
			try
			{
				s = br.readLine();
				if(s != null)
				{
					for(Observer o: observers)
					{
						o.notify(s, this);
					}
				}
				else
				{
					try
					{
						Thread.sleep(50);
					}
					catch(InterruptedException IE)
					{
						IE.printStackTrace();
					}
				}
			}
			catch(IOException IOE)
			{
				IOE.printStackTrace();
			}
		}
		return;
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
}