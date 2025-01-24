package lex ;

import java.io.*;
import java.util.ArrayList;
import utils.*;

/**
 * La classe Lex modelise un analyseur lexical abstrait
 * 
 * @author Girard, Masson, Perraudeau
 * janvier 2025
 */
abstract public class Lex {
	
	/** definition du flot d'entree a analyser */
	protected InputStream flot;
	
	/** definition de la table des identificateurs */
	protected ArrayList<String> tabIdent = new ArrayList<String>(); 
	
	/** gestion de l'affichage sur la fenetre de trace de l'execution */
	private ArrayList<ObserverLexique> lesObserveurs = new ArrayList<ObserverLexique>();
	
	public void newObserver(ObserverLexique obs){
		this.lesObserveurs.add(obs);
	}
	public void notifyObservers(char c){
		for(ObserverLexique o : this.lesObserveurs){
			o.nouveauChar(c);
		}
	}
	/** fin gestion de l'affichage sur la fenetre de trace de l'execution */
	
	/** constructeur de classe Lex */
	public Lex (InputStream f){
		this.flot = f;
	}
	/** methode d'acces a un item lexical */
	abstract public int lireSymb();
	/** methode d'acces a la chaine correspondant a un ident d'indice numIdent (dans tabIdent) **/
	abstract String chaineIdent(int numIdent);

}
