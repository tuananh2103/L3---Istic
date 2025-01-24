package syntax;

import java.util.Set;
import java.util.TreeSet;

/**
 * classe Chauffeur representant les donnees des chauffeurs
 * 
 * @author Girard, Grazon, Masson
 *
 * janvier 2025
 */
public class Chauffeur {

	/**
	 * numChauf = numero identifiant du chauffeur
	 * bj = quantite totale de vin beaujolais livree
	 * bg = quantite totale de vin bourgogne livree
	 * ordin = quantite totale de vin ordinaire livree
	 */
	public int numChauf, bj, bg, ordin;

	/** magdif = ensemble des magasins livres */
	public Set<Integer> magDif;

	public Chauffeur(int numchauf, int bj, int bg, int ordin, Set<Integer> magDif) {
		this.numChauf = numchauf;
		this.bj = bj;
		this.bg = bg;
		this.ordin = ordin;
		this.magDif = new TreeSet<Integer>(magDif);
	}

	public Chauffeur copie() {
		return new Chauffeur(numChauf, bj, bg, ordin, magDif);
	}
}
