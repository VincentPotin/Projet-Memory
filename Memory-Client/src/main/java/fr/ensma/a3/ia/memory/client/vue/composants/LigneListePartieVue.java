package fr.ensma.a3.ia.memory.client.vue.composants;

import fr.ensma.a3.ia.memory.client.controleur.ISelectionPartieControleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LigneListePartieVue extends HBox implements EventHandler<ActionEvent> {
    
    private HBox ligne;
    private Label idpartie, nbjoueurs;
    private Button rejoindre;
    private ISelectionPartieControleur refControleur;

    public LigneListePartieVue(final ISelectionPartieControleur pRefControleur) {
        super();
        refControleur = pRefControleur;
        ligne = new HBox(20);
        ligne.setAlignment(Pos.TOP_CENTER);
        idpartie = new Label("Partie1");
        nbjoueurs = new Label("0/4");
        rejoindre = new Button("Rejoindre");
        ligne.getChildren().addAll(idpartie,nbjoueurs,rejoindre);
        //Abonnements:
		rejoindre.addEventHandler(ActionEvent.ACTION, this);

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == rejoindre ) {
			System.out.println("Demande de connexion à la partie : " + idpartie.getText());
            refControleur.rejoindrepartie(idpartie.getText(),nbjoueurs.getText());
		}

    }

}
