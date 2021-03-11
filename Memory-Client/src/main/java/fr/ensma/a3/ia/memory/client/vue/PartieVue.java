package fr.ensma.a3.ia.memory.client.vue;

import fr.ensma.a3.ia.memory.client.controleur.IPartieControleur;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PartieVue extends FlowPane{

    private FlowPane root2;
    private VBox joueurs_1, joueurs_2, game;
	private HBox cartes_tirees; 
	private IPartieControleur refControleur;

	public PartieVue(final IPartieControleur pRefControleur) {
		super();
		refControleur = pRefControleur;
		root2 = new FlowPane(Orientation.HORIZONTAL,10, 10);
		root2.setMinWidth(600);
		root2.setAlignment(Pos.CENTER);
        joueurs_1 = new VBox(20);
        joueurs_1.setAlignment(Pos.TOP_CENTER);
        cartes_tirees = new HBox(20);
        cartes_tirees.setAlignment(Pos.TOP_CENTER);
        game = new VBox(20);
        game.setAlignment(Pos.TOP_CENTER);
        game.getChildren().addAll(cartes_tirees);
		joueurs_2 = new VBox(20);
        joueurs_2.setAlignment(Pos.TOP_CENTER);
		root2.getChildren().addAll(joueurs_1,game,joueurs_2);
		getChildren().add(root2);
		
	}
    
}
