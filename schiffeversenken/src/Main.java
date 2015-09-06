import io.github.craftqq.GUI.Fenster;
import io.github.craftqq.GUI.JElementHelper;
import io.github.craftqq.NetIO.ConnectionClient;
import io.github.craftqq.NetIO.ConnectionServer;
import io.github.craftqq.utility.Observable;
import io.github.craftqq.utility.Observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JComboBox;



public class Main 
{

	public static void main(String[] args) 
	{
		testServer();
		System.exit(0);
	}
	
	public static void testGUI()
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
	}
	
	public static void testServer()
	{
		ConnectionServer server = new ConnectionServer();
		ConnectionClient client = new ConnectionClient("localhost");
		HalloHalloechen protokoll = new HalloHalloechen();
		server.subscribe(protokoll);
		client.subscribe(protokoll);
		server.start();
		pause(1000);
		client.start();
		pause(1000);
		client.send("Hallo");
		pause(2000);
	}
	
	public static void log(String message)
	{
		System.out.println("["+LocalTime.now() +"]"+message);
	}
	
	public static void pause(long millisec)
	{
		try
		{
			Thread.sleep(millisec);
			
		}
		catch(InterruptedException IE)
		{
			IE.printStackTrace();
		}
	}
}
class HalloHalloechen implements Observer
{

	@Override
	public void notify(String message, Observable source) 
	{
		Main.log(message);
		if(source instanceof ConnectionServer)
		{
			ConnectionServer s = (ConnectionServer)source;
			s.sendAll("Hallöchen");
		}
		else
		{
			ConnectionClient c = (ConnectionClient) source;
			c.closeConnection();
		}
		
	}
	
	
}
