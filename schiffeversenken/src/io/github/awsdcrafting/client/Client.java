package io.github.awsdcrafting.client;

import io.github.craftqq.utility.Observable;
import io.github.craftqq.utility.Observer;
import io.github.craftqq.FileIO.DateiLeser;
import io.github.craftqq.FileIO.DateiSchreiber;

public class Client implements Observer
{
	String ReadedFile;
	
	public Client()  //das ist der konstruktor
	{		
		DateiLeser myLeserling;
		myLeserling.leseDatei("User.txt",ReadedFile);
		
		/*Dateiformat/Zeilenformat: USERID;Name;3stelliges Passwort 
		1. Zeile Hauptuser auf dem PC
		alle weiteren Zeilien zusätzliche User die sich hier mal eingeloggt haben aber ohne Passwort
		*/
	}
	
	public void SaveUserAndPW ()
	{
		DateiSchreiber mySchreiberling;
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
