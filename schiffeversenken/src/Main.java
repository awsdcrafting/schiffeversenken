import io.github.craftqq.GUI.Fenster;
import io.github.craftqq.GUI.JElementHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JComboBox;



public class Main 
{

	public static void main(String[] args) 
	{
		Fenster f = new Fenster("Schiffe versenken", 1600, 900);
		JComboBox<String> namenWahl = JElementHelper.<String>baueComboBox(20, 20);
		JButton knopf1 = JElementHelper.baueKnopf("Erster Knopf", 20, 60, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// mache was wenn knopf1 gedrückt wurde
				
			}
		});
		JButton knopf2 = JElementHelper.baueKnopf("Zweiter Knopf", 200, 60, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// mache was wenn knopf2 gedrückt wurde
				
			}
		});
		f.addToContentPane(namenWahl);
		f.addToContentPane(knopf1);
		f.addToContentPane(knopf2);
		f.initialize();
		
//		boolean isServer = false;
//		String host = "";
//		String arg = args[0];
//			if(arg.equalsIgnoreCase("-server")||arg.equalsIgnoreCase("/server")||arg.equalsIgnoreCase("server"))
//			{
//				isServer = true;
//			}
//			else
//			{
//				host = arg;
//			}
//		ConnectionServer s;
//		ConnectionClient c;
//		HalloHalloechen protokoll = new HalloHalloechen();
//		if(isServer)
//		{
//			s = new ConnectionServer();
//			s.start();
//			s.subscribe(protokoll);
//		}
//		else
//		{
//			c = new ConnectionClient(host);
//			c.subscribe(protokoll);
//			c.start();
//			try
//			{
//				Thread.sleep(50);
//			}
//			catch(InterruptedException IE)
//			{
//				
//			}
//			c.send("hallo");
//		}
//		try
//		{
//			Thread.sleep(5000);
//		}
//		catch(InterruptedException IE)
//		{
//			
//		}
//		System.exit(0);
	}
	
	public static void log(String message)
	{
		System.out.println("["+LocalTime.now() +"]"+message);
	}
}
//class HalloHalloechen implements Observer
//{
//
//	@Override
//	public void notify(String message, Observable source) 
//	{
//		Main.log(message);
//		if(source instanceof ConnectionServer)
//		{
//			ConnectionServer s = (ConnectionServer)source;
//			s.sendAll("Hallöchen");
//		}
//		else
//		{
//			ConnectionClient c = (ConnectionClient) source;
//			c.closeConnection();
//		}
//		
//	}
//	
//	
//}
