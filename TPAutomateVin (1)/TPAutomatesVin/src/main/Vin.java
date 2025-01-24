package main;

import java.io.InputStream;

import utils.*;
import syntax.*;

/**
 * La classe Vin correspond au programme principal d'un
 * analyseur syntaxique par automate d'etats fini avec actions
 * pour l'application Vin
 * 
 * @author Girard, Masson, Perraudeau
 * janvier 2025
 *
 */
public class Vin {

	/**
	 * Initialisation du fichier d'entree
	 * 
	 * @return flux de donnees a analyser provenant d'un fichier
	 */
	public static InputStream debutAnalyse() {
		String nomfich;
		nomfich = Lecture.lireString("nom du fichier d'entree : ");
		InputStream f = Lecture.ouvrir(nomfich);
		if (f == null) {
			System.exit(0);
		}
		return f;
	} 

	/**
	 * fermeture fichier d'entree et fenetre de trace d'execution
	 * 
	 * @param f : fichier d'entree a analyser
	 */
	public static void finAnalyse(InputStream f) {
		Lecture.fermer(f);
		// Fermeture de la fenetre de trace d'execution
		System.out.println("");
		System.out.println(" pour fermer la fenetre de trace d'execution, tapez entree (2 fois) ") ;
		Lecture.lireChar() ;
		Lecture.lireChar() ;
		System.exit(0);
	} 
	
	/**
	 * Analyse syntaxique par automate d'etats finis avec actions,
	 * mis en oeuvre par interpreteur de tables
	 * 
	 * @param args ici suppose vide
	 */
	public static void main(String[] args) {

		FenAffichage fenetre = new FenAffichage();
		
		// La donnee a analyser
		InputStream flot = debutAnalyse();

		// Création de l'analyseur syntaxique avec actions
		ActVin analyseur = new ActVin(flot);
		
		// Fenetre de trace de l'analyse
		analyseur.newObserver(fenetre, fenetre);
		
		// Appel de l'interpréteur de tables
		analyseur.interpreteur();
		
		finAnalyse(flot);
	}
} 

