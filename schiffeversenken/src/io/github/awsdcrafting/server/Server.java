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
		// das ganze soll �ber tcp/ip laufen; wenn man einen film schaut oder cs spielt ist ein paketlos einkalkuliert (udp), in unserem
		// fall aber darf kein einziges paket verlorgen gehen ! deshalb tcp/ip
		
		
		split TYP; reststring
		
		select case typ:
			"ANMELDUNG" // �berpr�fe ob das passwort korrekt ist
				//wenn ja schau nach ob der spieler schon einen spielerstatus hat; wenn nein kommt er in die lobby mit status inlobby;
				//und er bekommt die lobbyscreen infos geschickt (eingeloggte spieler; chat; partizipierbare offene spiele) 
				//wenn ja hatte er einen conneciton loss, lade ihm das entsprechende fenster das er nun sehen sollte
			"NEW_SPIELER" //schau nach ob dieser spielername noch nicht existiert; wenn er nicht existiert lege ihn an und speichere das mitgelieferte passwort
						//und schick ihm den lobbyscreen
						// der client selbst pr�ft ob das passwort passt
						//wenn der spieler bereits existiert schicke ihm "PLAYERNAMEINUSE" zur�ck
						//der client fragt dann "haben sie ihr passwort vergessen?"
			"DELETE_SPIELER" //spieler l�schen; brauchen wir das?
			
			"CHATLINE" // ein spieler hat eine chatzeile geschrieben; sende diese zeile an alle anderen lobby oder spielteilnehmer weiter
			
			"OPEN_GAME" //schick allen anderen spielern in der lobby die info das dieser spieler ein spiel gestartet hat
						//der creator kann nun w�hlen ob er ein savegame wiederaufnimmt oder ob er ein neuen spielt startet
			"SETTINGS_CHANGED" //der creator hat einen wert der spielregeln ge�ndert, date das f�r alle spieler in der hauptlobby up und in der spiellobby up
			
			"PARTIZIPANT_RREQUEST" // ein spieler m�chte einem offenen spiel beitreten; wenn es noch m�glich ist schicke ihm "PREQACCEPTED" zur�ck
									// im falle das ein passwort gesetzt wurde muss sich nach dem part..req noch das passwort befinden und �berpf�rft werden obs eh passt
									//sonst schick PWERROR" zur�ck
			"MODIFED_MY_FLEET" // ein spieler hat seine flotte modifiziert; �berpr�fe ob er nach den regeln daf�r bereits ein VALID bekomnmt
							// wenn ja schicke "VALID" zur�ck wenn nein schick "INVALID" zur�ck
			
			"LEAVE_GAMELOBBY" //ein spieler ist zur�ck in die hauptlobby gegangen; alle spielern mitteilen
			
			"START_REQUEST"  //der creator m�chte das spiel starten; alle gamelobbyteilnehmer finden sich nun im gamescreen
			/* per zufall wird ausgelost wer anf�ngt, jener bekommt vom server das "ACTIVETAG" zugesandt und kann dann 
			 das feuer er�ffnen, er bekommt zus�tzlich die information welcher feuermodus gerade aktiv ist, der feuermodus wird in einem label �ber dem chat angezeigt */
			// im falle eines savegames wird geschaut ob die playerids passen
			// im falle von savegames sind alle einstellungen ausgegraut, es gibt nur den chat und vlt einen ready knopf..
			
			"FIRE" //der aktive spieler hat einen schuss abgegeben; X und Y wert werden analysiert; es wird geschaut was wurde alles getroffen
			//mit einem severseitigen "BATTLE_UPDATE" werden alle notwendigen treffre an alle betroffenen spieler mitgeteilt
			//der client tr�gt diese informationen dann nat�rlich in sein tabellenartiges objekt ein
			//zus�tzlich kann abh�ngig nach gamemodus eine bestimmter feuermodus f�r einen spieler durch derst�rung eines schiffs deaktiviert worden sein; auch
			//das muss server seitig notiert werden
			//mit einem serverseitigen "ACTIVETAG" inclusive feuermodus wird der n�chste spieler informiert, damit die gui ihn feuern l�sst
			//im gui sollte auch der feuermodus sch�n dargestellt werden, damit man mit roten k�stchen sieht was man gerade plant zu schiesen; vlt mit rechter maustaste
			//kann die direction in alle 4 richtungen gedreht werden
			
			//im "BATTLE_UPDATE" ist dann auch ersichtlich wer schon drausen ist und wer am ende der gewinner ist xD
			//in dem spielsettings kann man angeben was die maximale zeit ist die ein spieler hat um einen schuss abzugeben: von 10sekunden bis maximal 30sekunden
			//falls der spieler in dieser zeit keinen schuss abgegeben hat wird automatisch vom server ein zuvallsschuss abgegeben und das spiel geht weiter
			//der zufallsschuss soll halbswegs sinnvoll sein, da m�sste man ein bischen eine ki programmieren, das werde ich machen :D
			
			"SAVEGAME_REQUEST" //dieser spieler hat den savegame knopf bet�tigt; das bedeutet er muss leider abhauen und m�chte das spiel speichern
			//egal wer diesen knopf bet�tigt es gibt keine diskussion, es wird gesavt und fertig ist
			//man kann savegames in der gamelobby restricten, wenn einer abhaut spielt dann die ki weiter oder er ist weg (auch das ist einstellbar)
			
			//wie funktioniert das savegame?
			/* am server gibts ne savegame.txt; ein savegame wird hier nach creator gelistet gespeichert, hier finden sich die infos wer sonst noch mitgespielt hat
			 * und wie die aktuellen treffer und activeplayer sachen sind (nur der creator hat einen eintrag, die partizipanten bekommen keinen eintrag); nur der creator kann ein savegame wiederaufnhemen
			 * 
			 */
					
			"LEAVE_GAME" //der spieler hat sein programm geschlossen; teil allen lobby oder spielteilnehmen mit das er weg ist
			
			// ""... bin noch nicht ganz fertig xD
		
	}
}
