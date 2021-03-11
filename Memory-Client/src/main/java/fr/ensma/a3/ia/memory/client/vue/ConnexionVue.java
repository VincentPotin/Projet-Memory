package fr.ensma.a3.ia.memory.client.vue;





import fr.ensma.a3.ia.memory.client.controleur.IConnexionControleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class ConnexionVue extends FlowPane implements EventHandler<ActionEvent> {

	private FlowPane root2;
    private VBox login;
	private Button connexion, newplayer;
	private TextField playerName;
	private Label indication;
	private IConnexionControleur refControleur;

	public ConnexionVue(final IConnexionControleur pRefControleur) {
		super();
		refControleur = pRefControleur;
		root2 = new FlowPane(Orientation.VERTICAL,10, 10);
		root2.setMinWidth(600);
		root2.setAlignment(Pos.CENTER);
        login = new VBox(20);
        login.setAlignment(Pos.TOP_CENTER);
        indication = new Label("Veuillez saisir votre pseudo");
		indication.setMinWidth(200);
		indication.setAlignment(Pos.CENTER);
        playerName = new TextField();
		playerName.setEditable(true);
        connexion = new Button("Connexion");
		newplayer = new Button("Pas de compte ?");
		login.getChildren().addAll(indication,playerName,connexion, newplayer);
		root2.getChildren().addAll(login);
		getChildren().add(root2);
		//Abonnements:
		connexion.addEventHandler(ActionEvent.ACTION, this);

	}
	
	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == connexion ) {
			System.out.println(playerName.getText() + " demande à se connecter");
			refControleur.connectPlayer(playerName.getText());
			
		} 
		
	}

}

