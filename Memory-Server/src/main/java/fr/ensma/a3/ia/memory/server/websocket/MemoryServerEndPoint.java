package fr.ensma.a3.ia.memory.server.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import fr.ensma.a3.ia.memory.api.messages.joueur.JoueurMessage;
import fr.ensma.a3.ia.memory.api.messages.joueur.JoueurMessageDecoder;
import fr.ensma.a3.ia.memory.api.messages.joueur.JoueurMessageEncoder;










@ServerEndpoint(value = "/ws/memory/{pseudo}", encoders = JoueurMessageEncoder.class, decoders = JoueurMessageDecoder.class)
public class MemoryServerEndPoint {

	static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	static ArrayList<String> idplayers = new ArrayList<String>();


	@OnOpen
	public void onOpen(@PathParam("pseudo") String pseudo, Session sess, EndpointConfig endpointConfig) {
		System.out.println(sess.getId() + " vient de se connecter avec le pseudo " + pseudo);
		sess.getUserProperties().put("pseudo", pseudo);
		clients.add(sess);
		System.out.println(clients);
		
	}

	//Réaction du serveur à la réception du serveur
	@OnMessage
	public void onMessage(JoueurMessage mess, Session sess) {
		JoueurMessage message = new JoueurMessage();
		message.setSonPseudo("LeServer");
		message.setLeContenu((String) "C'est à toi de donner un chiffre en 0 et 100"); 
		for (Session client : clients) { 
				try {
						client.getBasicRemote().sendObject(mess);		
					}
				 catch (ClassCastException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EncodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(); //Integer.parseInt(client.getId()) == Integer.parseInt(sess.getId()) + 1
				}
			}
		}
	

	@OnClose
	public void onClose(Session sess) {
		System.out.println(sess.getUserProperties().get("pseudo") + " vient de se déconnecter ...");
		JoueurMessage mess = new JoueurMessage();
		mess.setSonPseudo("LeServer");
		mess.setLeContenu((String) sess.getUserProperties().get("pseudo") + " nous a quitté ... (sniff)"); 
		for (Session client : clients) {
				try {
					client.getBasicRemote().sendObject(mess); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EncodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		clients.remove(sess);
    }
	
	
	  @OnError
	  public void onError(Session session, Throwable t) {
	    t.printStackTrace();
	  }
}


