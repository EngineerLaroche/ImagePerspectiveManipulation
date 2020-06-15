package References;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Observable;
import java.util.Stack;
import Operations.InterfaceOperations;

/*******************************************************************
 * @Titre_Classe: REFERENCE PERSPECTIVE
 * 
 * @Resumer:
 * Supporte les actions demandees par l'utilisateur pour modifier
 * la perspective. Supporte le processus qui permet de sauter d'un 
 * etat a un autre et donc de revnir ou retablir la perspective.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 29 juillet 2017 
 *******************************************************************/
public class ReferencePerspective extends Observable implements Serializable{

	/***************************
	 * Classes instanciees
	 ***************************/
	private Image image;
	private InterfaceOperations interfaceOperations;
	private PositionImage positionImage = new PositionImage(0,0);

	/***************************
	 * Piles Dynamiques
	 ***************************/
	private Stack<InterfaceOperations> listeRevenir = new Stack<InterfaceOperations>();
	private Stack<InterfaceOperations> listeRetablir = new Stack<InterfaceOperations>();

	/***************************
	 * Constance 
	 ***************************/
	private static final long serialVersionUID = -428465296112259216L;

	/***************************
	 * Variable
	 ***************************/
	private float zoom = 1;

	/***************************
	 * CONSTRUCTEUR
	 ***************************/
	public ReferencePerspective() {}

	/*************************************************************************
	 * @Titre: REVENIR ("ctrl + Z")
	 * 
	 * @Resumer: 
	 * Applique les operations avec la pile dynamique pour revenir a
	 * un etat anterieur tout en modifiant la pile dynamique qui
	 * permet de retablir a un etat futur.
	 * 
	 *************************************************************************/
	public void revenir() {
		if (!listeRevenir.empty()) {
			interfaceOperations = listeRevenir.pop();
			interfaceOperations.revenir();
			listeRetablir.push(interfaceOperations);
		}
		this.setChanged();
		this.notifyObservers();
	}

	/*************************************************************************
	 * @Titre: RESTAURER
	 * 
	 * @Resumer: 
	 * On recule jusqu'a l'etat d'origine de l'image. 
	 * Les deux piles dynamiques sont vides apres cette operation.
	 * 
	 *************************************************************************/
	public void restaurer(){
		while(!listeRevenir.isEmpty()){
			revenir();
		}
		listeRetablir.clear();
		this.setChanged();
		this.notifyObservers();
	}

	/*************************************************************************
	 * @Titre: EXECUTER OPERATIONS
	 * 
	 * @Resumer: 
	 * On execute les operations de l'utilisateur en ajustant les parametres
	 * retrouvees dans la pile dynamique.
	 * 
	 *************************************************************************/
	public void executerOperations(InterfaceOperations _interfaceOperations){
		this.interfaceOperations = _interfaceOperations;
		interfaceOperations.executer();
		listeRevenir.push(interfaceOperations);
		this.setChanged();
		this.notifyObservers();
	}

	/*************************************************************************
	 * @Titre: RETABLIR (ctrl + Y)
	 * 
	 * @Resumer: 
	 * Applique les operations avec la pile dynamique pour revenir a
	 * un etat futur tout en modifiant la pile dynamique qui permet de
	 * retablir a un etat anterieur.
	 * 
	 *************************************************************************/
	public void retablir() {
		if (!listeRetablir.empty()) {
			interfaceOperations = listeRetablir.pop();
			interfaceOperations.executer();
			listeRevenir.push(interfaceOperations);
		}
		this.setChanged();
		this.notifyObservers();
	}


	/***************************
	 * SET POSITION
	 * @Param: PositionsImage
	 ***************************/
	public void setPosition(PositionImage _positionImage){
		this.positionImage = _positionImage;
		int positionX = _positionImage.getPositionX();
		int positionY = _positionImage.getPositionY();
		positionImage.setPositionX(positionX);
		positionImage.setPositionY(positionY);

		this.setChanged();
		this.notifyObservers();
	}

	/***************************
	 * GET POSITION IMAGE
	 * @Return: position image
	 ***************************/
	public PositionImage getPositionImage() {
		return positionImage.getPositionImage();
	}

	/***************************
	 * GET CONTENU PERSPECTIVE
	 * @Return: double[] 
	 ***************************/
	public ContenuPerspective getContenu() {
		return new ContenuPerspective(positionImage, zoom);
	}

	/***************************
	 * SET IMAGE
	 * @Param: Image
	 ***************************/
	public void setImage(Image _image){
		this.image = _image;
		this.setChanged();
		this.notifyObservers(this.getImage());
	}

	/***************************
	 * GET IMAGE
	 * @return Image
	 ***************************/
	public Image getImage(){
		return image;
	}

	/***************************
	 * GET ZOOM
	 * @return zoom
	 ***************************/
	public float getZoom() {
		return zoom;
	}

	/***************************
	 * SET ZOOM
	 * @Param float zoom
	 ***************************/
	public void setZoom(float _zoom){
		this.zoom = _zoom;
		this.setChanged();
		this.notifyObservers();
	}

	/***************************
	 * SET CONTENU
	 * @Param float zoom
	 * @Param: PositionImage
	 ***************************/
	public void setContenu(float _zoom, PositionImage position) {
		this.zoom = _zoom;
		this.positionImage = position;
		setChanged();
		notifyObservers();
	}

	/***************************
	 * SET CONTENU
	 * @Param File
	 * 
	 * Importation d'un fichier
	 * .bin n'est pas terminé.
	 * Donc cette section est 
	 * incomplète.
	 * 
	 * @Source: 
	 * http://www.programcreek.com/2009/02/java-convert-image-to-byte-array-convert-byte-array-to-image/
	 * https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
	 * 
	 ***************************/
	public void importerImagePerspective(File _file) throws IOException{

		byte[] buffer = new byte[1000];

		try {
			FileInputStream inputStream =  new FileInputStream(_file);

			int total = 0;
			int nRead = 0;

			while((nRead = inputStream.read(buffer)) != -1) {
				System.out.println(new String(buffer));
				total += nRead;
			}   
			inputStream.close();        
			System.out.println("Read " + total + " bytes");
		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Impossible d'ouvrir le fichier '" + 
							_file + "'");                
		}
		catch(IOException ex) {
			System.out.println(
					"Erreur lecture fichier '" 
							+ _file + "'");                  
		}

		this.setChanged();
		this.notifyObservers();

	}
}