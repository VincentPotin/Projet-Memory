package fr.ensma.a3.ia.memory.client;


import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import fr.ensma.a3.ia.memory.api.messages.joueur.JoueurMessage;
import fr.ensma.a3.ia.memory.api.messages.joueur.JoueurMessageDecoder;
import fr.ensma.a3.ia.memory.api.messages.joueur.JoueurMessageEncoder;



@ClientEndpoint(encoders = JoueurMessageEncoder.class, decoders = JoueurMessageDecoder.class)
public class JoueurEndPoint {

	@OnOpen
	public void onOpen(Session sess) {
		System.out.println("Connexion établie !!");
	}
	
	@OnMessage
	public void onMessage(JoueurMessage mess) {
		System.out.println(mess.getSonPseudo() + " dit : " + mess.getLeContenu());
	}
	
	@OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }
	


}



