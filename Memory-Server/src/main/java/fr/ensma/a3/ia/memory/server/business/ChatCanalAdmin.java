package fr.ensma.a3.ia.memory.server.business;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.memory.api.messages.ChatCanalDesc;





public class ChatCanalAdmin {

	private static ChatCanalDesc privcanal = new ChatCanalDesc(1, "Canal de chat privé ... ");
	private static ChatCanalDesc profcanal = new ChatCanalDesc(2, "Canal de chat pro ...");
	private static ChatCanalDesc amicanal = new ChatCanalDesc(3, "Canal de chat amis ...");
	private static ChatCanalDesc jeucanal = new ChatCanalDesc(4, "Canal du jeu chaud-froid ...");
	
	public static List<ChatCanalDesc> getAllCanal() {
		List<ChatCanalDesc> liste = new ArrayList<ChatCanalDesc>();
		liste.add(privcanal);
		liste.add(profcanal);
		liste.add(amicanal);
		liste.add(jeucanal);
		return liste;
	}

	
	
}
