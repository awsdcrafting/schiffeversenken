package io.github.awsdcrafting.client;

import io.github.craftqq.utility.Observable;
import io.github.craftqq.utility.Observer;

public class Client implements Observer
{
	
	public Client()
	{
		
	}
	
	@Override
	public void notify(String message, Observable source) 
	{
		String s = message.split(";")[0];
		String t = s.split(":")[1];
		macheWas(t);
		
	}
	
	/**
	 * mache was mit der nachricht vom client
	 * @param nachricht die nachricht vom server
	 */
	public void macheWas(String nachricht)
	{
		
	}

}
