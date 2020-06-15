package Application;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import References.ContenuPerspective;


/*******************************************************************
 * @Titre_Classe: ECRITURE FICHIER
 * 
 * @Résumer:
 * Supporte le processus de sauvegarde du fichier image (perspective).
 * 
 * @Source:
 * Inspiration: https://www.java-forums.org/new-java/2790-how-save-image-jpg.html
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 25 juillet 2017 
 * 
 *******************************************************************/
public class SauvegardeImage{
	
	private File file;
	private BufferedImage bufferedImage;

	/*****************************
	 * CONSTRUCTEUR
	 * @Param: String 
	 * @Param: File
	 * @Param: ContenbuPerspective
	 * @Param: Rectangle
	 *****************************/
	public SauvegardeImage(String repertoireOriginal,File destinationFichier, ContenuPerspective contenuImage, Rectangle fenetrePerspectiveBordure) throws IOException {
		ecritureImage(repertoireOriginal, destinationFichier.getAbsolutePath(), contenuImage, fenetrePerspectiveBordure);
	}
	
	/*************************************************************************
	 * @Titre: ECRITURE CONTENU
	 * 
	 * @Resumer:
	 * Recupere l'image original pour ensuite l'envoyer dans un traitement 
	 * de changements image. Ensuite on procede a l'ecriture de l'image
	 * dans un fichier sur le disque dur de l'utilisateur.
	 * 
	 * @Param: String 
	 * @Param: String
	 * @Param: ContenbuPerspective
	 * @Param: Rectangle
	 * 
	 *************************************************************************/
	public void ecritureImage(String repertoireOriginal, String destinationFichier, ContenuPerspective contenuImage, Rectangle fenetrePerspectiveBordure) throws IOException {

		BufferedImage lectureImage = ImageIO.read(new File(repertoireOriginal));
		bufferedImage = creationNouvelleImage(lectureImage, contenuImage, fenetrePerspectiveBordure);
		file = new File(destinationFichier);
	}

	/*************************************************************************
	 * @Titre: SAUVEGARDE IMAGE DISQUE DUR
	 * 
	 * @Resumer:
	 * Sauvegarde la nouvelle image sur le disque dur de l'utilisateur
	 * 
	 *************************************************************************/
	public void setImageDisqueDur(){
		
		//Ecriture de l'image dans un repertoire sur le disque dur
		try { ImageIO.write(bufferedImage, "jpg", file); 
		}catch(IOException e) {
			System.out.println("Erreur sauvegarde pour: " + file.getPath());
			System.out.println("Message erreur: " + e.getMessage());
		}
	}

	/*************************************************************************
	 * @Titre: CREATION NOUVELLE IMAGE
	 * 
	 * @Resumer:
	 * Recoit l'image original pour l'emmener a un etat image perspectives.
	 * C'est ici qu'on modifie le zoom et la position de l'image a sauvegarder. 
	 * 
	 * @Param: Image
	 * 
	 *************************************************************************/
	private BufferedImage creationNouvelleImage(Image image, ContenuPerspective contenuImage, Rectangle fenetrePerspectiveBordure) {

		//Recupere la position X et Y de l'image. 
		int positionX = contenuImage.getPosition().getPositionX();
		int positionY = contenuImage.getPosition().getPositionY();

		//Recupere les dimnensions
		int largeur = image.getWidth(null);
		int hauteur = image.getHeight(null);

		double ratioLargeur = largeur/fenetrePerspectiveBordure.getWidth();
		double ratioHauteur = hauteur/fenetrePerspectiveBordure.getHeight();
		
		// Mapage de l'image originale
		int sx1 = 0;
		int sy1 = 0;
		int sx2 = largeur;
		int sy2 = hauteur;
		
		// calcul de l'image de destination
		int dx1 = (int) (positionX * ratioLargeur);
		int dy1 = (int) (positionY * ratioHauteur);
		int dx2 = (int) (positionX * ratioLargeur + largeur * contenuImage.getZoom());
		int dy2 = (int) (positionY * ratioHauteur + hauteur * contenuImage.getZoom());
		
		//C'est ici qu'on modifie la taille de l'image à la sortie
		BufferedImage zoomImage = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphiqueImage = zoomImage.createGraphics();
		graphiqueImage.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		graphiqueImage.dispose();

		return zoomImage;
	}
	
	/***************************
	 * GET FILE
	 ***************************/
	public File getFile(){
		return file;
	}
	
	/***************************
	 * GET BUFFERED IMAGE
	 ***************************/
	public BufferedImage getBufferedImage(){
		return bufferedImage;
	}
}
