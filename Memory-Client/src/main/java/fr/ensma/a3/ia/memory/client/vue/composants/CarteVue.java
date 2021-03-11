package fr.ensma.a3.ia.memory.client.vue.composants;

import fr.ensma.a3.ia.memory.client.controleur.IPartieControleur;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CarteVue extends VBox implements EventHandler<MouseEvent> {
    
    private IPartieControleur refControleur;

    Image faceCachee = new Image(this.getClass().getResourceAsStream("/Face_cachee.png"));
    ImageView imageview = new ImageView();

    public CarteVue(final IPartieControleur pRefControleur) {
        super(20);
		refControleur = pRefControleur;
        setAlignment(Pos.TOP_CENTER);
        imageview = new ImageView(faceCachee);
        imageview.setFitHeight(100);
        imageview.setFitWidth(100);
        getChildren().add(imageview);
        imageview.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

    }

    @Override
    public void handle(MouseEvent event) {
            refControleur.clickImage();
        }

}
