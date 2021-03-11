package fr.ensma.a3.ia.memory.api.messages.joueur;

public class JoueurMessage {
    
    private String sonPseudo;
	private String leContenu;
	
	public final String getSonPseudo() {
		return sonPseudo;
	}
	public final void setSonPseudo(final String pseu) {
		sonPseudo = pseu;
	}
	
	public final String getLeContenu() {
		return leContenu;
	}
	public final void setLeContenu(final String m) {
		leContenu = m;
	}

}
