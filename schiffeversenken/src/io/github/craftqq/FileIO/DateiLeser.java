package io.github.craftqq.FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class DateiLeser 
{
	
	public static String[] leseDatei(String name)
	{
		ArrayList<String> al = new ArrayList<String>();
		try
		{
			FileReader fr = new FileReader(name);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			while(s != null)
			{
				al.add(s);
				s = br.readLine();
			}
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return al.toArray(new String[0]);
	}
	
	public static String[] leseDatei(File file)
	{
		System.out.println(file.getAbsolutePath());
		ArrayList<String> al = new ArrayList<String>();
		try
		{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s = br.readLine();
			while(s != null)
			{
				al.add(s);
				s = br.readLine();
			}
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return al.toArray(new String[0]);
	}
	
}
