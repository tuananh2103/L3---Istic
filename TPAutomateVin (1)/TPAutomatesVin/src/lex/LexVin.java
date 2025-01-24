package lex;

/**
 * La classe LexVin implemente un analyseur lexical pour une application de livraison de vin
 * @author NGUYEN TUAN ANH - PHAM KHANH LINH GROUPE 1.8
 * janvier 2025
 */

import java.io.InputStream;
import utils.*;

public class LexVin extends Lex {

    /** codage des items lexicaux */
    protected final int BEAUJOLAIS = 0, BOURGOGNE = 1, IDENT = 2, NBENTIER = 3, VIRGULE = 4, PTVIRG = 5, BARRE = 6,
            AUTRES = 7;

    /** tableau de l'affichage souhaite des items lexicaux */
    public static final String[] images = { "BEAUJ", "BOURG", "IDENT", "NBENT", "VIRG ", "PTVIR", "BARRE ", "AUTRE" };

    /** nombre de mots reserves dans l'application Vin */
    private final int NBRES = 2;

    /** attributs lexicaux associes resp. a NBENTIER et IDENT */
    private int valEnt, numIdCourant;

    /** methodes d'acces aux attributs lexicaux */
    public int getValEnt() {
        return this.valEnt;
    }

    public int getNumIdCourant() {
        return this.numIdCourant;
    }

    /** caractere courant */
    private char carLu;

    /** utilisé uniquement dans l'affichage fenêtre graphique */
    public char getCarLu() {
        return this.carLu;
    }

    /**
     * constructeur classe LexVin
     *
     * @param flot : donnee d'entree a analyser
     */
    public LexVin(InputStream flot) {
        // Initialisation du flot par la classe abstraite
        super(flot);
        System.out.println("Trinome PHAM"); // : maj avec vos noms
        System.out.println("--------------");

        // Prelecture du premier caractere de la donnee
        lireCarLu();

        // Initialisation de tabIdent par mots reserves de l'application Vin
        this.tabIdent.add(0, "BEAUJOLAIS");
        this.tabIdent.add(1, "BOURGOGNE");
    }

    /**
     * methode de lecture d'un nouveau caractere courant carLu a partir de la donnee
     * en entree flot
     */
    private void lireCarLu() {
        carLu = Lecture.lireChar(flot);
        this.notifyObservers(carLu);
        // transformation de tous les caracteres de separation en espaces et forcage
        // lettres en majuscules
        if ((carLu == '\r') || (carLu == '\n') || (carLu == '\t'))
            carLu = ' ';
        if (Character.isWhitespace(carLu))
            carLu = ' ';
        else
            carLu = Character.toUpperCase(carLu);
    }

    /**
     * detection item lexical IDENT ou mots reserves
     *
     * @return codage IDENT ou codage du mot reserve
     */
    private int lireIdent() {
		String s = "";
		do {
			s = s + carLu; lireCarLu();
		} while ((carLu >= 'a') && (carLu <= 'z') || (carLu >= 'A') && (carLu <= 'Z'));
		
		for (int i = 0; i < tabIdent.size();i++) {
		    if (s.equals(tabIdent.get(i))) {
		       if (i< NBRES) {
		    	   return i;
		       }
		       else {
		    	   numIdCourant = i;
		    	   return IDENT;
		       }
		    }
		}
		numIdCourant = tabIdent.size();
		tabIdent.add(numIdCourant, s);
		return IDENT;

		}

    /**
     * detection item lexical NBENTIER
     *
     * @return codage NBENTIER
     */
    private int lireNbEnt() {
        String s = "";
        // Read characters until a non-digit is encountered
        while (carLu >= '0' && carLu <= '9') {
            s = s + carLu;
            lireCarLu(); // Presumably reads the next character
        }
        
        // If s is non-empty, parse the number
        if (!s.isEmpty()) {
            valEnt = Integer.parseInt(s); 
            return NBENTIER;
        } 
        
        return AUTRES;
    }

    /**
     * definition de la methode abstraite lireSymb de Lex
     *
     * @return code de l'item lexical detecte
     */
    public int lireSymb() {
        while (carLu == ' ') 
            lireCarLu();
        
        if(( carLu >= '0') && (carLu <= '9')) {
            return lireNbEnt();
        }
        if ((carLu >= 'a') && (carLu <= 'z') || (carLu >= 'A') && (carLu <= 'Z') ) {
			return lireIdent();
        }
        switch (carLu) {
		case ';':
			lireCarLu();
			return PTVIRG;
			
		case ',':
			lireCarLu();
			return VIRGULE;
			
		case '/':
			lireCarLu();
			return BARRE;
			
		default: {
			System.out.println("LexVin : caractere incorrect : " + carLu);
			lireCarLu();
			return AUTRES;
		}
	}
    }

    /**
     * definition de la methode abstraite chaineIdent de Lex
     *
     * @param numIdent : numero d'un ident dans la table des idents
     * @return chaine correspondant a numIdent
     */
    public String chaineIdent(int numIdent) {
		if (numIdent>=0 && numIdent<tabIdent.size()) { 
			return tabIdent.get(numIdent);
		}
				
		else {
				System.out.println("Identificateur non-trouvé");
				return "AUTRES";
		}
	}

    /** utilitaire de test de l'analyseur lexical seul (sans analyse syntaxique) */
    private void testeur_lexical() {
        // Unite lexicale courante
        int token;
        // definition du caractere de fin de chaine utile
        // uniquement pour test autonome du lexical
        int finDeChaine = BARRE;
        do {
            token = lireSymb();
            if (token == NBENTIER)
                Lecture.attenteSurLecture("token : " + images[token] + " attribut valEnt = " + valEnt);
            else if (token == IDENT)
                Lecture.attenteSurLecture("token : " + images[token] + " attribut numIdCourant = " + numIdCourant);
            else
                Lecture.attenteSurLecture("token : " + images[token]);
        } while (token != finDeChaine);
    }

    /**
     * Main pour tester l'analyseur lexical seul (sans analyse syntaxique)
     */
    public static void main(String args[]) {

        String nomfich = Lecture.lireString("nom du fichier d'entree : ");
        InputStream flot = Lecture.ouvrir(nomfich);
        if (flot == null) {
            System.exit(0);
        }

        LexVin testVin = new LexVin(flot);
        testVin.testeur_lexical();

        Lecture.fermer(flot);
        Lecture.attenteSurLecture("fin d'analyse");
        System.exit(0);

    }
}
