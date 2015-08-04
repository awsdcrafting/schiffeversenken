package io.github.awsdcrafting.server;

import io.github.craftqq.utility.Observable;
import io.github.craftqq.utility.Observer;

public class Server implements Observer
{
	//activeplayers array:
	//infos: playerid; status = (inlobby; ingamecreation; ingamepartizipation; ingamecreator; ingamepartizipant
	//

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
		// das ganze soll über tcp/ip laufen; wenn man einen film schaut oder cs spielt ist ein paketlos einkalkuliert (udp), in unserem
		// fall aber darf kein einziges paket verlorgen gehen ! deshalb tcp/ip
		
		
		split TYP; reststring
		
		select case typ:
			"ANMELDUNG" // überprüfe ob das passwort korrekt ist
				//wenn ja schau nach ob der spieler schon einen spielerstatus hat; wenn nein kommt er in die lobby mit status inlobby;
				//und er bekommt die lobbyscreen infos geschickt (eingeloggte spieler; chat; partizipierbare offene spiele) 
				//wenn ja hatte er einen conneciton loss, lade ihm das entsprechende fenster das er nun sehen sollte
			"NEWSPIELER" //schau nach ob dieser spielername noch nicht existiert; wenn er nicht existiert lege ihn an und speichere das mitgelieferte passwort
						//und schick ihm den lobbyscreen
						// der client selbst prüft ob das passwort passt
						//wenn der spieler bereits existiert schicke ihm "PLAYERNAMEINUSE" zurück
						//der client fragt dann "haben sie ihr passwort vergessen?"
			"DELETESPIELER" //spieler löschen; brauchen wir das?
			
			"OPENGAME" //schick allen anderen spielern in der lobby die info das dieser spieler ein spiel gestartet hat
						//
			"SETTINGSCHANGED" //der creator hat einen wert der spielregeln geändert, date das für alle spieler in der hauptlobby up und in der spiellobby up
			
			"PARTIZIPANT-RREQUEST" // ein spieler möchte einem offenen spiel beitreten; wenn es noch möglich ist schicke ihm "PREQACCEPTED" zurück
									// im falle das ein passwort gesetzt wurde muss sich nach dem part..req noch das passwort befinden und überpfürft werden obs eh passt
									//sonst schick PWERROR" zurück
			"MODIFEDMYFLEET" // ein spieler hat seine flotte modifiziert; überprüfe ob er nach den regeln dafür bereits ein VALID bekomnmt
							// wenn ja schicke "VALID" zurück wenn nein schick "INVALID" zurück
			"STARTREQUEST"  //der creator möchte das spiel starten
			
			""... bin noch nicht ganz fertig xD
		
	}
}
