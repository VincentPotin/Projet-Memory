package fr.ensma.a3.ia.memory.api.messages.joueur;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class JoueurMessageEncoder implements Encoder.Text<JoueurMessage> {
    
	private static Gson gson = new Gson();
	
	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	/**
	 * Création d'un objet Json pour l'envoie
	 */
	@Override
	public String encode(JoueurMessage mess) throws EncodeException {
		return gson.toJson(mess);		
	}

}