package fr.ensma.a3.ia.memory.client;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Scanner;

import javax.websocket.DeploymentException;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

import fr.ensma.a3.ia.memory.api.messages.ChatCanalDesc;
import fr.ensma.a3.ia.memory.api.messages.client.ClientMessage;

import com.google.gson.Gson;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class MyMultiChatClient {
	
	private static final String SERVER = "ws://localhost:8080/ws/multichat";
	private static final String REST_URI = "http://localhost:8080/services/multichat";

	private static Gson gson = new Gson();

	public static void main(String[] args) {
		
		Client restclient = ClientBuilder.newClient();
		
		//Appel du service pour récuperer la liste des canaux disponibles
		List<ChatCanalDesc> list_canaux = restclient.target(REST_URI).request(MediaType.APPLICATION_JSON).get(new GenericType<List<ChatCanalDesc>>(){});
		System.out.println(list_canaux);

	
		
		ClientManager client = ClientManager.createClient();
		String blabla;
	
		
		//Connexion au serveur :
		Scanner scan = new Scanner(System.in);
		System.out.println("Choisis ton canal de communication : ");
		//Appel du service pour recupérer un canal depuis un ID
		String canal = scan.nextLine();
		int idcanal = Integer.valueOf(canal);
		int id_canal = Integer.valueOf(idcanal) - 1;
		ChatCanalDesc rep = restclient.target(REST_URI).path(String.valueOf(id_canal)).request(MediaType.APPLICATION_JSON).get(ChatCanalDesc.class); 
		System.out.println(rep);
		System.out.println("Bienvenu sur MultiChat - Canal " + String.valueOf(canal));
		System.out.println("Donne ton pseudo : ");
		String pseudo = scan.nextLine();
		try {
			Session sess = client.connectToServer(MultiChatClientEndPoint.class, URI.create(SERVER+"/"+idcanal+":"+pseudo));
			sess.getUserProperties().put("Canal", idcanal);
			sess.getUserProperties().put("Pseudo", pseudo);
			do {
				blabla = scan.nextLine();
				sess.getBasicRemote().sendText(formatMessage(idcanal, pseudo, blabla));
			} while(!blabla.equalsIgnoreCase("quit"));
			
		} catch (DeploymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			scan.close();
		}	
	}
	
	private static String formatMessage(int canal, String pseu, String bla) {
		ClientMessage m = new ClientMessage();
        m.setCanalId(canal);
        m.setLePseudo(pseu);
        m.setLeContenu(bla);
        return gson.toJson(m);
    }

}
