package utils;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JFrame;

import lex.LexVin;

/**
 * Gestion fenêtre d'affichage pour traces d'exécution 
 * 
 * janvier 2025
 */

public class FenAffichage extends JFrame implements ObserverAutomate, ObserverLexique {

	private static final long serialVersionUID = 1L;
	TextArea fenEntree;
	TextArea fenTrace;

	public FenAffichage() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Affichage des traces de l'automate");
		this.setLocation(430, 0);
		fenEntree = new TextArea("FICHIER D'ENTREE\n", 9, 80);
		fenEntree.append("----------------\n");
		fenEntree.setFont(new Font("Courier", Font.PLAIN, 12));
		fenEntree.setEditable(false);
		this.add(fenEntree, "North");
		fenTrace = new TextArea("ETAT, UNITE, ETAT, ACTION\n", 10, 80);
		fenTrace.append("--------------------------\n");
		fenTrace.setFont(new Font("Courier", Font.PLAIN, 12));
		fenTrace.setEditable(false);
		this.add(fenTrace, "South");
		this.pack();
		this.setVisible(true);
	}

	private void afficherEtatAnalyse(int etatDepart, int unite, int etatArrivee, int action) {
		String mess = "depart=" + etatDepart + "  item=" + LexVin.images[unite] + "\t" + "  arrivee=" + etatArrivee
				+ "  action= " + action + "\n";
		fenTrace.append(mess);
	}

	public static void main(String[] args) {
		new FenAffichage();
	}

	@Override
	public void notification(int etatDepart, int unite, int etatArrive, int action) {
		this.afficherEtatAnalyse(etatDepart, unite, etatArrive, action);

	}

	@Override
	public void nouveauChar(char c) {
		this.fenEntree.append("" + c);

	}

}
