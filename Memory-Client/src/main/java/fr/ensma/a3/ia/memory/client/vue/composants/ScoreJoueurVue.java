package fr.ensma.a3.ia.memory.client.vue.composants;

import fr.ensma.a3.ia.memory.client.controleur.IPartieControleur;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ScoreJoueurVue extends HBox {

    private HBox etatjoueur;
    private Label pseudo, score;
    private IPartieControleur refControleur;

    public ScoreJoueurVue(final IPartieControleur pRefControleur) {
        super();
        refControleur = pRefControleur;
        etatjoueur = new HBox(20);
        etatjoueur.setAlignment(Pos.TOP_CENTER);
        pseudo = new Label("Pseudo");
        score = new Label("Score");
        etatjoueur.getChildren().addAll(pseudo,score);

    }
}
