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

import fr.ensma.a3.ia.memory.api.messages.client.ClientMessage;
import fr.ensma.a3.ia.memory.api.messages.client.ClientMessageDecoder;
import fr.ensma.a3.ia.memory.api.messages.client.ClientMessageEncoder;








@ServerEndpoint(value = "/ws/multichat/{canalandpseudo}", encoders = ClientMessageEncoder.class, decoders = ClientMessageDecoder.class)
public class MultiChatServerEndPoint {

	static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	static ArrayList<String> idplayers = new ArrayList<String>();
	static int randomNumber = 101;

	public void generateNumber() {
		randomNumber =  (int)(Math.random() * 101);
	}

	public ClientMessage gagnant(Session s) {
		ClientMessage m = new ClientMessage();
		String pseudo_gagnant =(String) s.getUserProperties().get("pseudo");
		m.setLePseudo("Le Server");
		m.setLeContenu(pseudo_gagnant + " " + "a gagné");
		return m;
	}

	public ClientMessage finJeu() {
		ClientMessage m = new ClientMessage();
		m.setLePseudo("Le Server");
		m.setLeContenu("Le jeu est terminé, merci d'avoir joué !");
		return m;
	}


	public ClientMessage reaction(int val, int rand) {
		ClientMessage m = new ClientMessage();
		m.setLePseudo("Le Server");
		if (rand == val) {
			m.setLeContenu("C'est trouvé !!");
		}
		else if (Math.abs((rand-val)) > 10) {
			m.setLeContenu("Froid");
		}
		else if (Math.abs((rand-val)) < 10) {
			m.setLeContenu("Chaud");
		}
		return m;
	}

	@OnOpen
	public void onOpen(@PathParam("canalandpseudo") String canalandpseudo, Session sess, EndpointConfig endpointConfig) {
		System.out.println(sess.getId() + " vient de se connecter au canal " + canalandpseudo);
		String[] params = canalandpseudo.split(":");
		sess.getUserProperties().put("canalId", params[0]);
		sess.getUserProperties().put("pseudo", params[1]);
		if (params[0].equals("4")) {
			idplayers.add(sess.getId());
		}
		clients.add(sess);
		System.out.println(idplayers);
		
	}

	//Réaction du serveur à la réception du serveur
	@OnMessage
	public void onMessage(ClientMessage mess, Session sess) {
		if (randomNumber == 101) {
			generateNumber();
		}
		System.out.print("le nombre à trouver est : " + randomNumber);
		String canal_id_sess = (String) sess.getUserProperties().get("canalId");
		String id_sess = sess.getId();
		ClientMessage message = new ClientMessage();
		message.setLePseudo("LeServer");
		message.setLeContenu((String) "C'est à toi de donner un chiffre en 0 et 100"); 
		for (Session client : clients) {
			if (!sess.getId().equals(client.getId())) {
				try {
					if (client.getUserProperties().get("canalId").equals(sess.getUserProperties().get("canalId"))) {
						client.getBasicRemote().sendObject(mess);
						//Ajout du contenu lié au jeu du chaud-froid propre au canal 4
						if (canal_id_sess.equals("4")) {
							int value = Integer.parseInt(mess.getLeContenu());
							client.getBasicRemote().sendObject(reaction(value, randomNumber));
							sess.getBasicRemote().sendObject(reaction(value, randomNumber));
							if (reaction(value, randomNumber).getLeContenu() == "C'est trouvé !!") {
								client.getBasicRemote().sendObject(gagnant(sess));
								client.getBasicRemote().sendObject(finJeu());
								sess.getBasicRemote().sendObject(gagnant(sess));
								sess.getBasicRemote().sendObject(finJeu());
							}
							
							else if (id_sess == idplayers.get(idplayers.size() - 1)) {
									if (client.getId() == idplayers.get(0)) {
									client.getBasicRemote().sendObject(message);
									}
							} else if (client.getId() == idplayers.get(idplayers.indexOf(id_sess) + 1)) {
								client.getBasicRemote().sendObject(message);	
							}		
						}
					}
				} catch (ClassCastException e) {
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
	}
	
	@OnClose
	public void onClose(Session sess) {
		System.out.println(sess.getUserProperties().get("pseudo") + " vient de se déconnecter ...");
		ClientMessage mess = new ClientMessage();
		mess.setLePseudo("LeServer");
		mess.setLeContenu((String) sess.getUserProperties().get("pseudo") + " nous a quitté ... (sniff)"); 
		for (Session client : clients) {
			if (!sess.getId().equals(client.getId())) {
				try {
					if (client.getUserProperties().get("canalId").equals(sess.getUserProperties().get("canalId"))) {
					client.getBasicRemote().sendObject(mess); }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EncodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		clients.remove(sess);
	}
	
	  @OnError
	  public void onError(Session session, Throwable t) {
	    t.printStackTrace();
	  }
}
