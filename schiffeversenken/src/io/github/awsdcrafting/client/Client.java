package io.github.awsdcrafting.client;

import io.github.craftqq.utility.Observable;
import io.github.craftqq.utility.Observer;
import io.github.craftqq.FileIO.DateiLeser;
import io.github.craftqq.FileIO.DateiSchreiber;

public class Client implements Observer
{
	String[] readedFile;
	
	public Client()  //das ist der konstruktor
	{
		/*
		 * Kommentar von craftqq:
		 * DateiLeser.leseDatei(String name) ist eine statische methode, ein Objekt vom typ dateiLeser zu erzuegen ist nicht nötig
		 * die methode gibt ein Array an Strings zurück, nicht einen einzigen String (pro zeile ein string)
		 */
		readedFile = DateiLeser.leseDatei("User.txt");
		
		/*Dateiformat/Zeilenformat: USERID;Name;3stelliges Passwort 
		1. Zeile Hauptuser auf dem PC
		alle weiteren Zeilien zusätzliche User die sich hier mal eingeloggt haben aber ohne Passwort
		*/
	}
	
	public void SaveUserAndPW ()
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
