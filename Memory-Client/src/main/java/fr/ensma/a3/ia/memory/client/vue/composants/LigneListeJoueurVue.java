package fr.ensma.a3.ia.memory.client.vue.composants;

import fr.ensma.a3.ia.memory.client.controleur.IHallOfFameControleur;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LigneListeJoueurVue extends HBox{
    
    private HBox ligne;
    private Label pseudo, nbparties, scoretotal;
    private IHallOfFameControleur refControleur;

    public LigneListeJoueurVue(final IHallOfFameControleur pRefControleur) {
        super();
        refControleur = pRefControleur;
        ligne = new HBox(20);
        ligne.setAlignment(Pos.TOP_CENTER);
        pseudo = new Label("Pseudo");
        nbparties = new Label("50");
        scoretotal = new Label("730");
        ligne.getChildren().addAll(pseudo,nbparties,scoretotal);
      
    }

}
