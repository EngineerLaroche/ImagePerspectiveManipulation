package Fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import javax.swing.BorderFactory;
import Operations.OperationsSouris;
import References.PositionImage;
import References.ReferencePerspective;

/*******************************************************************
 * @Titre_Classe: AJUSTEMENT IMAGE
 * 
 * @Resumer:
 * Tout ce qui concerne les changements apportes a l'image.
 * On utilise des mutateurs et un observateur pour supporter les 
 * changements de positions. 
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 19 juillet 2017 
 *******************************************************************/
public class ImagePerspective extends ImageOriginal {

	/********************
	 * Constante
	 ********************/
	private static final long serialVersionUID = 5778115352555864274L;

	/***************************
	 * Classes instanciees
	 ***************************/
	private Image image;
	private ReferencePerspective referencePerspective;
	private MenuOutils menuOutils = new MenuOutils();
	private PositionImage positionImage = new PositionImage(0,0);

	/********************
	 * Variable
	 ********************/
	private float zoom = 1;

	/***************************
	 * CONSTRUCTEUR
	 * @Param: position X et Y
	 * @Param: largeur et hauteur
	 ***************************/
	public ImagePerspective(int _positionX, int _positionY, int _largeur, int _hauteur){

		super(_positionX, _positionY, _largeur, _hauteur);
		this.setLayout(new BorderLayout());

		//Ajout du menu outils a une fenetre qui contient une perspective
		this.add(menuOutils, BorderLayout.NORTH);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	/*************************************************************************
	 * @Titre: GRAPHIQUE IMAGE
	 * 
	 * @Resumer:
	 * Supporte l'affichage de l'image avec perspectives.
	 * La position de l'image change en fonction des translations et du zoom.
	 * 
	 * @Param: Graphics
	 * 
	 *************************************************************************/
	public void graphiqueImage(Graphics g) {
		int largeurZoom = (int) (this.getWidth() * zoom);
		int hauteurZoom = (int) (this.getHeight() * zoom);
		g.drawImage(this.image, positionImage.getPositionX(), 
				positionImage.getPositionY(), largeurZoom, hauteurZoom, null);
	}

	/*************************************************************************
	 * @Titre: SET REFERENCE PERSPECTIVE
	 * 
	 * @Resumer:
	 * Permet la lecture des actions de la souris sur la perspective.
	 * On envoi les parametres de la perspective au menu outils pour associer 
	 * les actions a celle-ci.
	 * 
	 * @Param: ReferencePerspective
	 * 
	 *************************************************************************/
	public void setReferencePerspective(ReferencePerspective _referencePerspective) {
		this.referencePerspective = _referencePerspective;
		OperationsSouris operationSouris = new OperationsSouris(referencePerspective);
		operationSouris.rouletteSouris(this);
		this.addMouseListener(operationSouris);
		this.addMouseMotionListener(operationSouris);
		this.menuOutils.setReferencePerspective(referencePerspective);
	}
	
	/********************
	 * SET POSITION IMAGE
	 * @Param: PositionImage
	 ********************/
	public void setPositionImage(PositionImage _positionImage){
		this.positionImage = _positionImage;
		this.repaint();
	}

	/*************************************************************************
	 * @Titre: UPDATE IMAGE
	 * 
	 * @Resumer:
	 * Si on observe une instance de la classe ReferencePerspective, on va
	 * chercher les parametres tels que l'image, le zoom et la position.
	 * Sinon on met a jour.
	 * 
	 * @Param: Observable
	 * @Param: Object
	 * 
	 *************************************************************************/
	@Override
	public void update(Observable observable, Object object) {
		if(observable instanceof ReferencePerspective) {
			referencePerspective = (ReferencePerspective) observable;
			this.image = referencePerspective.getImage();
			this.zoom = referencePerspective.getZoom();
			this.positionImage = referencePerspective.getPositionImage();
			this.repaint();
		} else {
			super.update(observable, object);
			this.image = this.getImage();
		}
	}
}