package io.github.craftqq.NetIO;

import io.github.craftqq.utility.Observable;
import io.github.craftqq.utility.Observer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionClient extends Thread implements Observable
{
	protected Socket socket;
	protected boolean connected;
	protected PrintWriter pw;
	protected BufferedReader br;
	protected String host;
	protected int port;
	private ArrayList<Observer> observers;
	
	public ConnectionClient(String host_, int port_)
	{
		host = host_;
		port = port_;
		observers = new ArrayList<Observer>();
	}
	
	public ConnectionClient(String host_)
	{
		this(host_, 55055);
	}
	
	public void closeConnection()
	{
		pw.write("CONNECTION:CLOSE");
		connected = false;
	}
	
	public boolean isConnected()
	{	
		return connected;
	}
	
	public void send(String s)
	{
		pw.println(s);
	}
	
	public synchronized String receive()
	{
		String s = "";
		try
		{
			if(br.ready())
			{
				while(br.ready())
				{
					s = s.concat(br.readLine() + "\n");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return s;
	}
	
	public void run()
	{
		try
		{
			socket = new Socket(host, port);
			connected = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println();
			System.out.println("Could not connect!");
			connected = false;
			return;
		}
		try
		{
			pw = new PrintWriter(socket.getOutputStream(), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			connected  = false;
		}
		
		while(connected)
		{
			String s = "";
			try
			{
				s = br.readLine();
				if(s != null)
				{
					if(s.toLowerCase().toUpperCase().startsWith("CONNECTION"))
					{
						String sc = s.substring(11);
						if(sc.equalsIgnoreCase("CLOSE"))
						{
							connected = false;
						}
					}
					String cs = "REMOTE:" + s + ";ID:SERVER";
					for(Observer o:observers)
					{
						o.notify(cs,this);
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
						
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		pw.close();
		try
		{
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
