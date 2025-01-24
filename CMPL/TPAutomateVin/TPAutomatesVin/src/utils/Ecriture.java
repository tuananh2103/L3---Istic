package utils;

import java.io.*;

/**
 * quelques primitives d'ecriture a l'ecran ou dans un fichier
 * 
 * @author Girard, Grazon, Masson
 *
 * janvier 2025
 */

public class Ecriture {

	/**
	 * methode de gestion d'une exception fatale
	 * 
	 * @param e : exception a traiter
	 */
	private static void erreur(IOException e) {
		System.out.println(e.getMessage());
		System.out.println("Erreur fatale");
		System.exit(1);
	}

	/**
	 * primitive d'ouverture d'un fichier
	 * 
	 * @param nomFich : nom du fichier a ouvrir
	 * @return
	 */
	public static OutputStream ouvrir(String nomFich) {
		// delivre un pointeur sur le fichier de nom nomFich (null si erreur)
		OutputStream f;
		try {
			f = new DataOutputStream(new FileOutputStream(nomFich));
		} catch (IOException e) {
			f = null;
		}
		return f;
	}

	/**
	 * primitive de fermeture d'un fichier
	 * 
	 * @param f : nom du fichier a fermer
	 */
	public static void fermer(OutputStream f) {
		// fermeture d'un fichier
		try {
			f.close();
		} catch (IOException e) {
			erreur(e);
		}
	}

	/**
	 * primitive d'ecriture d'un caractere dans un fichier
	 * 
	 * @param f : fichier ou ecrire
	 * @param c : caractere a ecrire
	 */
	public static void ecrireChar(OutputStream f, char c) {
		try {
			f.write(c);
		} catch (IOException e) {
			erreur(e);
		}
	}

	/**
	 * primitive d'ecriture d'un caractere a l'ecran
	 * 
	 * @param c : caractere a ecrire
	 */
	public static void ecrireChar(char c) {
		System.out.print(c);
	}

	/**
	 * ecriture d'une chaine dans un fichier
	 * 
	 * @param f : fichier ou ecrire
	 * @param s : chaine a ecrire
	 */
	public static void ecrireString(OutputStream f, String s) {
		try {
			for (int i = 0; i < s.length(); i++)
				f.write(s.charAt(i));
		} catch (IOException e) {
			erreur(e);
		}
	}

	/**
	 * ecriture d'une chaine a l'ecran
	 * 
	 * @param s : chaine a ecrire
	 */
	public static void ecrireString(String s) {
		System.out.print(s);
	}

	/**
	 * ecriture d'une chaine dans un fichier avec retour a la ligne
	 * 
	 * @param f : fichier ou ecrire
	 * @param s : chaine a ecrire
	 */
	public static void ecrireStringln(OutputStream f, String s) {
		ecrireString(f, s + "\r\n");
	}

	/**
	 * ecriture d'une chaine a l'ecran avec retour a la ligne
	 * 
	 * @param s : chaine a ecrire
	 */
	public static void ecrireStringln(String s) {
		System.out.println(s);
	}

	/**
	 * ecriture d'un retour a la ligne dans un fichier
	 * 
	 * @param f : fichier ou ecrire
	 */
	public static void ecrireStringln(OutputStream f) {
		ecrireString(f, "\r\n");
	}

	/**
	 * ecriture d'un retour a la ligne a l'ecran
	 */
	public static void ecrireStringln() {
		System.out.println();
	}

	/**
	 * ecriture d'un entier dans un fichier
	 * 
	 * @param f : fichier ou ecrire
	 * @param x : entier a ecrire
	 */
	public static void ecrireInt(OutputStream f, int x) {
		ecrireString(f, Integer.toString(x));
	}

	/**
	 * ecriture d'un entier a l'ecran
	 * 
	 * @param x : entier a ecrire
	 */
	public static void ecrireInt(int x) {
		System.out.print(x);
	}

	/**
	 * transforme un entier en une chaine de taille fixe
	 * 
	 * @param x        : entier a transformer en chaine
	 * @param longueur : nombre de caracteres de la chaine resultat
	 * @return chaine formee de l'entier complete, a gauche, d'espaces si besoin
	 */
	private static String chInt(int x, int longueur) {
		String s = Integer.toString(x), ch = "";
		int k = longueur - s.length();
		for (int i = 0; i < k; i++)
			ch += ' ';
		return ch + s;
	}

	/**
	 * ecriture d'un entier, dans un fichier, sur nombre fixe de caracteres
	 * 
	 * @param f        : fichier ou ecrire
	 * @param x        : entier a ecrire
	 * @param longueur : nombre de caracteres pour ecrire l'entier (entier complete
	 *                 a gauche d'espaces si besoin)
	 */
	public static void ecrireInt(OutputStream f, int x, int longueur) {
		ecrireString(f, chInt(x, longueur));
	}

	/**
	 * ecriture d'un entier, a l'ecran, sur nombre fixe de caracteres
	 * 
	 * @param x        : entier a ecrire
	 * @param longueur : nombre de caracteres pour ecrire l'entier (entier complete
	 *                 a gauche d'espaces si besoin)
	 */
	public static void ecrireInt(int x, int longueur) {
		System.out.print(chInt(x, longueur));
	}

	/**
	 * ecriture d'un reel dans un fichier
	 * 
	 * @param f : fichier ou ecrire
	 * @param d
	 */
	public static void ecrireDouble(OutputStream f, double d) {
		ecrireString(f, Double.toString(d));
	}

	/**
	 * ecriture d'un reel a l'ecran
	 * 
	 * @param d
	 */
	public static void ecrireDouble(double d) {
		System.out.print(d);
	}

}
