package fr.ensma.a3.ia.memory.client.vue;



import fr.ensma.a3.ia.memory.client.controleur.MemoryControleur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MemoryVue extends Application{

    private MemoryControleur memCtrl;
    @Override
    public void start(Stage stage) {
        memCtrl = new MemoryControleur(this);
        Scene scene = new Scene(memCtrl.getConnexionVue(), 500, 500);
        stage.setTitle("GAME");
        stage.setScene(scene);
        stage.show();
         
    }

    

    
    
}
