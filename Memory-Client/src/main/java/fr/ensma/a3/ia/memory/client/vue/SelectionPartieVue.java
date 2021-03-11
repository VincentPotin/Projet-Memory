package fr.ensma.a3.ia.memory.client.vue;

import java.util.ArrayList;
import java.util.List;

import fr.ensma.a3.ia.memory.client.controleur.ISelectionPartieControleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SelectionPartieVue extends FlowPane implements EventHandler<ActionEvent> {
    
    private BorderPane root2;
    private VBox newpartie;
    private VBox listeparties;
    private HBox infoliste, hnbcartes, hnbjoueurs;
	private Button creerpartie;
	private TextField nompartie;
	private Label infonewpartie, infonbjoueurs, infonbcartes, infolisteparties, noms, joueurs;
	private ComboBox<Integer> nbjoueurs,nbcartes;
	private ObservableList<Integer> listejoueurs,listecartes;
    private ISelectionPartieControleur refControleur;

    public SelectionPartieVue(final ISelectionPartieControleur pRefControleur) {
		super();
		refControleur = pRefControleur;
		root2 = new BorderPane();
		root2.setMinWidth(600);
        newpartie = new VBox(20);
        infonewpartie = new Label("Nouvelle Partie");
		infonewpartie.setMinWidth(200);
		infonewpartie.setAlignment(Pos.CENTER);
        nompartie = new TextField("nom partie");
		nompartie.setEditable(true);
		hnbjoueurs = new HBox(20);
		hnbjoueurs.setSpacing(20);
        infonbjoueurs = new Label("Choisis le nombre de joueurs");
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(2);
		list1.add(3);
		list1.add(4);
		list1.add(5);
		list1.add(6);
		listejoueurs = FXCollections.observableArrayList(list1);
		nbjoueurs = new ComboBox<Integer>(listejoueurs);	
		nbjoueurs.getSelectionModel().select(0);
		hnbjoueurs.getChildren().addAll(infonbjoueurs,nbjoueurs);
		hnbcartes = new HBox(20);
		hnbcartes.setSpacing(20);
		infonbcartes = new Label("Choisis le nombre de cartes");
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(49);
		list2.add(100);
		list2.add(121);
		listecartes = FXCollections.observableArrayList(list2);
		nbcartes = new ComboBox<Integer>(listecartes);	
		nbcartes.getSelectionModel().select(0);
		hnbcartes.getChildren().addAll(infonbcartes,nbcartes);
        creerpartie = new Button("Créer partie");
		creerpartie.setAlignment(Pos.CENTER);
		newpartie.getChildren().addAll(infonewpartie,nompartie,hnbjoueurs,hnbcartes,creerpartie);
		root2.setLeft(newpartie);
        listeparties = new VBox(20);
        listeparties.setAlignment(Pos.CENTER);
        infolisteparties = new Label("Parties en cours");
		infolisteparties.setMinWidth(200);
		infolisteparties.setAlignment(Pos.TOP_CENTER);
        infoliste = new HBox(20);
        infoliste.setAlignment(Pos.TOP_CENTER);
        noms = new Label("Nom");
		noms.setMinWidth(200);
		noms.setAlignment(Pos.TOP_CENTER);
        joueurs = new Label("Joueurs");
		joueurs.setMinWidth(200);
		joueurs.setAlignment(Pos.TOP_CENTER);
        infoliste.getChildren().addAll(noms,joueurs);
        listeparties.getChildren().addAll(infolisteparties,infoliste);
		root2.setRight(listeparties);
		BorderPane.setMargin(newpartie, new Insets(10, 0, 10, 0));
        BorderPane.setMargin(listeparties, new Insets(10, 0, 10, 0));
		getChildren().add(root2);
		//Abonnements:
		creerpartie.addEventHandler(ActionEvent.ACTION, this);
	}

    @Override
	public void handle(ActionEvent event) {
		if (event.getSource() == creerpartie ) {
			System.out.println("La partie : " + nompartie.getText() + " avec " + nbjoueurs.getValue() + " joueurs et " + nbcartes.getValue() + " cartes demande à être créée");
			refControleur.createPartie(nompartie.getText(),nbjoueurs.getValue(),nbcartes.getValue());
            
		} 
		
	}



}
