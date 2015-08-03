package io.github.awsdcrafting.server;

import io.github.craftqq.utility.Observable;
import io.github.craftqq.utility.Observer;

public class Server implements Observer
{

	@Override
	public void notify(String message, Observable source) 
	{
		String[] s = message.split(";");
		String t = s[0].split(":")[1];
		String u = s[1].split(":")[1];
		macheWas(t,u);
		
	}
	/**
	 * mache was mit der nachricht vom client
	 * @param message nachricht vom client
	 * @param id id des clienten (welcher client)
	 */
	public void macheWas(String message, String id)
	{
		
	}
}
