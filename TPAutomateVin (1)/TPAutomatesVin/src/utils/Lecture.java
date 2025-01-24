package utils;
import java.io.*;
import java.util.Scanner;

/**
 * Quelques primitives de lecture au clavier ou dans un fichier
 * 
 * @author Girard, Masson, Perraudeau, Demange
 *
 * janvier 2025
 */

public class Lecture {

	private static final Scanner sc = new Scanner(System.in);

	/**
	 * Ouverture d'un flot de lecture sur fichier de nom nomFich (null si erreur)
	 * 
	 * @param nomFich nom du fichier d'entree
	 * @return flot correspondant aux donnees du fichier
	 */
	public static InputStream ouvrir(String nomFich) {
		InputStream f;
		try {
			f = new DataInputStream(new FileInputStream(nomFich));
		} catch (FileNotFoundException e) {
			System.out.println("fichier " + nomFich + " inexistant");
			f = null;
		}
		return f;
	}

	/**
	 * Determine si la fin de fichier est atteinte
	 * 
	 * @param f fichier contenant le flot de donnee a analyser
	 * @return vrai si fin de fichier trouvee
	 */
	public static boolean finFichier(InputStream f) {
		try {
			return (f != System.in && f.available() == 0);
		} catch (IOException e) {
			System.out.println("pb test finFichier");
			System.exit(1);
		} catch (java.lang.NullPointerException e) {
			System.exit(2);
		}
		return true;
	}

	/**
	 * ferme un fichier (affiche un message si probleme)
	 * 
	 * @param f : fichier contenant le flot de donnee a analyser
	 */
	public static void fermer(InputStream f) {
		try {
			f.close();
		} catch (IOException e) {
			System.out.println("pb fermeture fichier");
		}
	}

	/**
	 * Lecture d'un octet dans la chaine d'entree (avec capture de l'exception)
	 * 
	 * @param f fichier contenant le flot de donnee a analyser
	 * @return caractere lu dans f
	 */
	public static char lireChar(InputStream f) {
		char carSuiv = ' ';
		try {
			int x = f.read();
			if (x == -1) {
				System.out.println("lecture en fin de fichier");
				System.exit(2);
			}
			carSuiv = (char) x;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Erreur fatale");
			System.exit(3);
		}
		return carSuiv;
	}

	/**
	 * Lecture d'un caractère au clavier
	 * 
	 * @return caractere courant lu
	 */
	public static char lireChar() {
		return lireChar(System.in);
	}

	/**
	 * Lecture d'une chaine au clavier, terminée par la touche Entrée, suivie de
	 * l'affichage d'un message
	 * 
	 * @param mess message à afficher, après lecture chaine
	 * @return chaine lue
	 **/
	public static String lireString(String mess) {
		System.out.println();
		System.out.print(mess);
		return sc.nextLine();
	}

	/**
	 * Affichage d'un message a l'ecran, et attente d'un retour-a-la-ligne
	 * 
	 * @param mess message à afficher
	 **/
	public static void attenteSurLecture(String mess) {
		System.out.println();
		System.out.print(mess + " pour continuer tapez entree");
		sc.nextLine();
	}

}
