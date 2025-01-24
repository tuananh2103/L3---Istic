package syntax;

import java.io.*;
import utils.*;
import lex.*;

/**
 * La classe AutoVin met en oeuvre l'automate d'analyse syntaxique de distribution de vin
 * Realisation par interpreteur de tables
 * 
 * @author ?? MERCI DE PRECISER LE NOM DU TRINOME ?? TODO
 * janvier 2025
 * 
 */
public class AutoVin extends Automate{

	/**
	 * Rappel: reprise apres erreur demandee (avec points de reprise sur les items lexicaux PTVIRG et BARRE)
	 */

	/** table des transitions */
	private final int[][] TRANSIT =
		{/* Etat        BJ    BG   IDENT  NBENT VIRG PTVIRG BARRE AUTRES  */
	 	/* 0 */      {  0,   0,    0,     0,   0,   0,   0,    0   },
	 	/* 1 */      {  0,   0,    0,     0,   0,   0,   0,    0   },
	 	/* TODO : a modifier et completer pour ne pas boucler sur l'etat 0 */ 
	 	/* Rappel conventions :  etatErreur = TRANSIT.length - 1 et pas de ligne pour etatFinal */
    } ;
	
	/** gestion de l'affichage sur la fenetre de trace de l'execution */
	public void newObserver(ObserverAutomate oAuto, ObserverLexique oLex ){
		this.newObserver(oAuto);
		this.analyseurLexical.newObserver(oLex);
		analyseurLexical.notifyObservers(((LexVin)analyseurLexical).getCarLu());
	}
	/** fin gestion de l'affichage sur la fenetre de trace de l'execution */
 
	/**
	 *  constructeur classe AutoVin pour l'application Vin
	 *  
	 * @param flot : donnee a analyser
	 */
	public AutoVin(InputStream flot) {
		// On utilise ici un analyseur lexical de type LexVin
		analyseurLexical = new LexVin(flot);
		// Initialisation etats particuliers de l'automate fini d'analyse syntaxique
		this.etatInitial = 0;
		this.etatFinal = TRANSIT.length;
		this.etatErreur = TRANSIT.length - 1;
	}

	/** definition de la methode abstraite getTransition de Automate 
	 * 
	 * @param etat : code de l'etat courant
	 * @param itemLex : code de l'item lexical courant
	 * @return code de l'etat suivant
	 **/
	int getTransition(int etat, int itemLex) {
		return this.TRANSIT[etat][itemLex];
	}

	// ici les methodes liées aux action ne sont pas encore définies.
	void faireAction(int etat, int itemLex) {};
	void initAction() {};	
	int getAction(int etat, int itemLex) {return 0;};
		
}
