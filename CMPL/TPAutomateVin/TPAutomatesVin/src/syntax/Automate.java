package syntax ;

import java.util.ArrayList;
import lex.*;
import utils.*;

/**
 *  La classe Automate modelise un automate fini abstrait d'analyse syntaxique 
 *  
 *  @author Girard, Masson, Perraudeau
 *  janvier 2025
*/
public abstract class Automate {
	/** Definition de l'analyseur lexical utilise **/
	protected Lex analyseurLexical;		
	
	/** Etats specifiques de l'automate d'analyse syntaxique */
	protected int etatInitial;
	protected int etatFinal;
	protected int etatErreur;
	protected int etatCourant;

	/** errFatale permet d'arreter l'interpreteur avant la fin de la donnee
	 * 		geree par la methode getTransition si passage dans etatErreur provoque l'arret
	 * 		geree par la methode faireAction si certaines actions provoquent l'arret
	 */
	protected boolean errFatale;	

/** gestion de l'affichage sur la fenetre de trace de l'execution */
	private ArrayList<ObserverAutomate> lesObserveurs=new ArrayList<ObserverAutomate>();
	public void newObserver(ObserverAutomate obs){
		this.lesObserveurs.add(obs);
	}	
	public void notifyObservers(int etatDepart, int unite, int etatArrive, int action){
		for(ObserverAutomate o : this.lesObserveurs){
			o.notification(etatDepart, unite, etatArrive, action);
		}
	}
/** fin gestion de l'affichage sur la fenetre de trace de l'execution */
	
	/** constructeur de classe Automate 
	 * */
	public Automate() {
		initAction();
		this.errFatale = false;
	}
	
	/** interpreteur mettant en oeuvre l'analyse syntaxique par automate fini */
	public void interpreteur() {
		
		/** initialisation etat courant */
		etatCourant = etatInitial;	
		/** unite lexicale courante */
		int token;
		
		while (etatCourant != etatFinal && !errFatale) {
			token = analyseurLexical.lireSymb();
			int etatDepart=etatCourant;
			int action = getAction(etatCourant, token);
			faireAction(etatCourant, token);
			etatCourant = getTransition(etatCourant, token);
			this.notifyObservers(etatDepart, token, etatCourant, action);
		}
	}

	/** methode d'acces au code du prochain etat */ 
	abstract int getTransition(int etat, int unite);
	/** methode d'accès au code de la prochaine action */
	abstract int getAction(int etat, int unite);
	/** initialisations nécessaires aux actions */
	abstract void initAction();
	/** methode d'execution de la prochaine action */
	abstract void faireAction(int etat, int unite);

}
