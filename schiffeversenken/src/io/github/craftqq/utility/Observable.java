package io.github.craftqq.utility;


public interface Observable 
{
	public void subscribe(Observer o);
	public void unsubscribe(Observer o);
}
