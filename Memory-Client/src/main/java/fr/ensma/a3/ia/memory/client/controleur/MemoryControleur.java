package fr.ensma.a3.ia.memory.client.controleur;



import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.google.gson.Gson;

import org.glassfish.tyrus.client.ClientManager;

import fr.ensma.a3.ia.memory.api.messages.joueur.JoueurMessage;
import fr.ensma.a3.ia.memory.client.JoueurEndPoint;
import fr.ensma.a3.ia.memory.client.vue.ConnexionVue;
import fr.ensma.a3.ia.memory.client.vue.HallOfFameVue;
import fr.ensma.a3.ia.memory.client.vue.MemoryVue;
import fr.ensma.a3.ia.memory.client.vue.PartieVue;
import fr.ensma.a3.ia.memory.client.vue.SelectionPartieVue;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MemoryControleur implements IConnexionControleur, ISelectionPartieControleur, IPartieControleur, IHallOfFameControleur{

    private static final String SERVER = "http://localhost:8080/ws/memory";

    private static Gson gson = new Gson();
    
    private MemoryVue maVue;
    private ConnexionVue refConnexionVue;
    private SelectionPartieVue refSelPartieVue;
    private PartieVue refPartieVue;
    private HallOfFameVue refHallOfFameVue;

    
    public MemoryControleur(MemoryVue v) {
    maVue = v;
		refConnexionVue = new ConnexionVue(this);
    refSelPartieVue = new SelectionPartieVue(this);
    refPartieVue = new PartieVue(this);
    refHallOfFameVue = new HallOfFameVue(this);
    }


    @Override
  	public void connectPlayer(String pseudo) {
      Scene scene = new Scene(this.getSelectionPartieVue(), 700, 700);
      Stage newWindow = new Stage();
      newWindow.setTitle("Parties en cours");
      newWindow.setScene(scene);
      newWindow.initModality(Modality.APPLICATION_MODAL);
      newWindow.show();
      ClientManager joueur = ClientManager.createClient();
			// Connexion au serveur;
      WebSocketContainer container = ContainerProvider.getWebSocketContainer();
      Scanner scan = new Scanner(System.in);
      String blabla = scan.nextLine();
      try {
        Session sess = joueur.connectToServer(JoueurEndPoint.class, URI.create(SERVER+"/"+pseudo+"/"));
        sess.getUserProperties().put("Pseudo", pseudo);
        do {
          sess.getBasicRemote().sendText(formatMessage(pseudo, blabla));
        } while(!blabla.equalsIgnoreCase("quit"));
        //container.connectToServer(JoueurEndPoint.class, new URI("ws://localhost:8080/ws/memory"));
       } catch (DeploymentException | IOException e) {
        e.printStackTrace();
       } finally {
        scan.close();
      }
	  }

    @Override
  	public void clickImage() {
        
	  }

    private static String formatMessage(String pseu, String bla) {
      JoueurMessage m = new JoueurMessage();
          m.setSonPseudo(pseu);
          m.setLeContenu(bla);
          return gson.toJson(m);
      }

    @Override
  	public void createPartie(String nompartie, Integer nbjoueurs, Integer nbcartes) {
        
	  }

    @Override
    public void rejoindrepartie(String idpartie, String nbjoueurs) {
        
    }

    public ConnexionVue getConnexionVue() {
      return refConnexionVue;
    }

    public SelectionPartieVue getSelectionPartieVue() {
      return refSelPartieVue;
    }

    public PartieVue getPartieVue() {
      return refPartieVue;
    }

    public HallOfFameVue getHallOfFameVue() {
      return refHallOfFameVue;
    }



   
      

}
