package fr.ensma.a3.ia.memory.client.controleur;

public interface ISelectionPartieControleur {
    
    void createPartie(String nompartie, Integer nbjoueurs, Integer nbcartes);

	void rejoindrepartie(String idpartie, String nbjoueurs);

}
