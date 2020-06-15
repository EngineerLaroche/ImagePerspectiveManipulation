package Application;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/*******************************************************************
 * @Titre_Classe: SAUVEGARDE BINAIRE
 * 
 * @Resumer: 
 * Recupere le fichier image et le transforme a un fichier .bin .
 * Classe non terminé !
 * 
 * @Source: 
 * Inspiration: https://stackoverflow.com/questions/19738982/converting-jpeg-to-binary-1-and-0-format
 * https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
 * 
 * @Auteur: Alexandre Laroche
 * 			Maxime Gelinas
 * 			Tomy Nguyen
 * 			Xel Said
 * 
 * @Date: 25 juillet 2017
 *******************************************************************/
public class SauvegardeBinaire {

	/***************************
	 * CONSTRUCTEUR
	 ***************************/
	public SauvegardeBinaire(String repertoire, File file, File destinationFichier) throws IOException {
		ecritureFichierBinaire(repertoire, file.getAbsolutePath(), destinationFichier.getAbsolutePath());
	}

	/*************************************************************************
	 * @Titre: ECRITURE FICHIER BINAIRE
	 * 
	 * @Resumer: 
	 * Recoit les informations du repertoire de l'image et converti en un fichier .bin
	 * Classe non terminée.
	 * 
	 * @Param: ReferencePerspective
	 * 
	 *************************************************************************/
	public void ecritureFichierBinaire(String repertoire, String repertoireFinal, String file) throws FileNotFoundException{

		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(repertoire));
				BufferedWriter bw = new BufferedWriter(new FileWriter(repertoireFinal))) {
			int read;
			while ((read=bis.read()) != -1) {
				String text = Integer.toString(read,2);
				while (text.length() < 8) {
					text="0"+text;
				}
				bw.write(text);
			}            
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}


