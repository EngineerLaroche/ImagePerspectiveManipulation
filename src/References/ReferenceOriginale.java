package References;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;

/*******************************************************************
 * @Titre_Classe: REFERENCE ORIGINAL
 * 
 * @Resumer:
 * Supporte le processus d'importation de fichiers image.
 * On donne les parametres necessaires pour permettre la lecture 
 * de fichiers de type image.
 * 
 * @Source:
 * ImageIO.read(): https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 29 juillet 2017 
 *******************************************************************/
public class ReferenceOriginale extends Observable{

	/***************************
	 * Classes instanciees
	 ***************************/
	private Image image;
	private File file = null;

	/***************************
	 * CONSTRUCTEUR
	 ***************************/
	public ReferenceOriginale(){}

	/*************************************************************************
	 * @Titre: IMPORTER IMAGE
	 * 
	 * @Resumer:
	 * Creation d'un nouveau tampon image si le fichier recu en parametre
	 * est nouveau (null). On utilise ImageIO.read() pour lire des fichiers 
	 * sous formats GIF, PNG, JPEG, BMP et WBMP.
	 * 
	 * @Param: File
	 * @throws IOException
	 *************************************************************************/
	public void importerImage(File _file) throws IOException {
		this.file = _file;
		if(file == null) {
			this.image = new BufferedImage(450, 450, BufferedImage.TYPE_INT_RGB);
		} else {
			this.image = ImageIO.read(file);
		}
		this.setChanged();
		this.notifyObservers();
	}

	/********************
	 * GET FILE
	 * @return: Image
	 ********************/
	public File getFichier(){
		return file;
	}

	/********************
	 * GET IMAGE
	 * @return: Image
	 ********************/
	public Image getImage() {
		return image;
	}
}