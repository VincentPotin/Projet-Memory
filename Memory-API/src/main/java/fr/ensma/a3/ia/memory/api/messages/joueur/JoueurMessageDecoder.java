package fr.ensma.a3.ia.memory.api.messages.joueur;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class JoueurMessageDecoder implements Decoder.Text<JoueurMessage> {
    
    private static Gson gson = new Gson();
	
	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public JoueurMessage decode(String mess) throws DecodeException {
		return gson.fromJson(mess, JoueurMessage.class);
	}

	@Override
	public boolean willDecode(String mess) {
		return true;
	}

}