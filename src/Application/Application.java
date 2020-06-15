package Application;
import javax.swing.SwingUtilities;
import Fenetres.ImageOriginal;
import Fenetres.ImagePerspective;
import References.ReferencePerspective;
import Fenetres.FenetrePrincipale;

/*******************************************************************
 * @Titre_Classe: APPLICATION
 * 
 * @Resumer:
 * Permet de demarrer l'application.
 * Donne les parametres pour creer les differentes fenetres
 * 
 * @Auteur: Alexandre Laroche
 * 			Maxime Gelinas
 * 			Tomy Nguyen
 * 			Xel Said
 * 
 * @Date: 20 juillet 2017 
 *******************************************************************/
public class Application{

	/***************************
	 * Classes instanciees
	 ***************************/
	private FenetrePrincipale fenetrePrincipale = new FenetrePrincipale();

	/********************
	 * Variables
	 ********************/
	private int positionX, positionY, largeur, hauteur;
	
	/********************
	 * Constantes
	 ********************/
	private static final int 
	POS_Y = 20,
	POS_X = 500,
	LARGEUR = 450, 
	HAUTEUR = 450;

	/***************************
	 * MAIN APPLICATION
	 ***************************/
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Application();
			}
		});
	}

	/***************************
	 * CONSTRUCTEUR
	 * Dimensions des 4 fenetres
	 ***************************/
	public Application(){

		imageOriginal(20, POS_Y, LARGEUR, HAUTEUR);
		imagePerspective(POS_X, POS_Y, LARGEUR, HAUTEUR);
		
		imagePerspective(20, 500, LARGEUR, HAUTEUR);
		imagePerspective(POS_X, 500, LARGEUR,HAUTEUR);	
		
	}

	/*************************************************************************
	 * @Titre: IMAGE ORIGINAL
	 * 
	 * @Resumer:
	 * Donne les dimension pour creer une fenetre avec l'image original.
	 * Ajout de la fenetre dans un conteneur. 
	 * Ajout d'un observateur a la fenetre. 
	 * 
	 * @Param: position X et Y
	 * @Param: largeur et hauteur
	 * 
	 *************************************************************************/
	private ImageOriginal imageOriginal(int _positionX, int _positionY, int _largeur, int _hauteur) {

		this.positionX = _positionX;
		this.positionY = _positionY;
		this.largeur = _largeur;
		this.hauteur = _hauteur;

		//Nouveau cadre avec l'image original
		ImageOriginal imageOriginal = new ImageOriginal(positionX, positionY, largeur, hauteur);
		fenetrePrincipale.conteneur(imageOriginal);
		Controleur.getInstance().getReferenceOriginale().addObserver(imageOriginal);
		return imageOriginal;
	}

	/*************************************************************************
	 * @Titre: IMAGE PERSPECTIVE
	 * 
	 * @Resumer:
	 * Donne les dimensions pour creer une fenetre qui sera une 
	 * perspective de l'image original.
	 * Ajout de la fenetre dans un conteneur. 
	 * Ajout d'un observateur a la fenetre de l'image original et a celle
	 * de l'image avec une perspective. 
	 * 
	 * @Param: position X et Y
	 * @Param: largeur et hauteur
	 * 
	 *************************************************************************/
	private ImagePerspective imagePerspective(int _positionX, int _positionY, int _largeur, int _hauteur) {

		this.positionX = _positionX;
		this.positionY = _positionY;
		this.largeur = _largeur;
		this.hauteur = _hauteur;
		
		//Nouvelle reference vers une perspective et nouveau cadre avec l'image (perspective)
		ReferencePerspective referencePerspective = new ReferencePerspective();
		ImagePerspective imagePerspective = new ImagePerspective(positionX, positionY, largeur, hauteur);

		fenetrePrincipale.conteneur(imagePerspective);
		referencePerspective.addObserver(imagePerspective);
		imagePerspective.setReferencePerspective(referencePerspective);
		Controleur.getInstance().ajouterPerspective(referencePerspective);
		return imagePerspective;
	}
}