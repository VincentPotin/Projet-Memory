package fr.ensma.a3.ia.memory.client.vue;

import fr.ensma.a3.ia.memory.client.controleur.IHallOfFameControleur;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HallOfFameVue extends FlowPane{
    
    private BorderPane root2;
    private HBox infoliste;
    private VBox listejoueurs;
    private Label titre, pseudojoueur, nbpartiesjouees, score;
    private IHallOfFameControleur refControleur;

    public HallOfFameVue(final IHallOfFameControleur pRefControleur) {
		super();
		refControleur = pRefControleur;
		root2 = new BorderPane();
        titre = new Label("Hall Of Fame");
		titre.setFont(Font.font("SansSerif", FontWeight.BOLD, 24));
        titre.setTextFill(Color.BLUEVIOLET);
        root2.setTop(titre);
        infoliste = new HBox(100);
        pseudojoueur = new Label("Joueur");
        nbpartiesjouees = new Label("Parties jouées");
        score = new Label("Score Total");
        infoliste.getChildren().addAll(pseudojoueur,nbpartiesjouees,score);
        infoliste.setAlignment(Pos.CENTER);
        listejoueurs = new VBox(20);
        listejoueurs.setAlignment(Pos.CENTER);
        listejoueurs.getChildren().addAll(infoliste);
		root2.setCenter(listejoueurs);
        BorderPane.setMargin(titre, new Insets(10, 0, 10, 0));
        BorderPane.setMargin(listejoueurs, new Insets(10, 0, 10, 0));
        BorderPane.setAlignment(titre, Pos.CENTER);
        getChildren().add(root2);
	}
}
