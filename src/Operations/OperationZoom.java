package Operations;

import javax.swing.JOptionPane;
import References.ReferencePerspective;

/*******************************************************************
 * @Titre_Classe: OPERATION ZOOM
 * 
 * @Resumer: 
 * Supporte le mecanisme pour zoomer sur la perspective.
 * On garde en memoire une copie avec zoomActuel pour nous
 * permettre de revenir a un etet ulterieur du zoom.
 * 
 * @Source: 
 * Inspiration MouseAdapter: http://www.java2s.com/Code/Java/Event/extendsMouseAdapterandimplementsMouseMotionListenertocreateamousedrawingprogramm.htm
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 25 juillet 2017
 *******************************************************************/
public class OperationZoom implements InterfaceOperations {

	/***************************
	 * Variables
	 ***************************/
	private double zoom = 1, zoomActuel;

	/***************************
	 * Classe instanciee
	 ***************************/
	private ReferencePerspective referencePerspective;

	/***************************
	 * Constantes
	 ***************************/
	private final double 
	MINIMUM_ZOOM = (zoom * 0.005),
	MAXIMUM_ZOOM = (zoom * 250);

	private static final String TITRE_ERREUR = "Fenetre Erreur Zoom";
	private static final String MESSAGE_ERREUR = "Vous avez dépasser la limite de zoom.";

	/***************************
	 * CONSTRUCTEUR
	 * @Param: ChangementImage
	 * @Param: float zoom
	 ***************************/
	public OperationZoom(ReferencePerspective _referencePerspective, float _zoom) {
		this.zoom = _zoom;
		this.referencePerspective = _referencePerspective;
		this.zoomActuel = referencePerspective.getZoom();
	}

	/***************************
	 * OPERATION ZOOM
	 ***************************/
	public void executer() {
		float multiplicationZoom = (float) (zoomActuel * zoom);

		//Evite que l'utilisateur zoom a un point de perdre un visuel sur l'image.
		if(multiplicationZoom > MINIMUM_ZOOM && multiplicationZoom < MAXIMUM_ZOOM){
			referencePerspective.setZoom(multiplicationZoom);
		}else{
			//Indique a l'utilisateur qu'il a dépassé la limite du zoom.
			JOptionPane.showMessageDialog(null, MESSAGE_ERREUR, TITRE_ERREUR, JOptionPane.WARNING_MESSAGE);
		}
	}

	/***************************
	 * 
	 * REVENIR OPERATION ZOOM
	 ***************************/
	public void revenir() {
		referencePerspective.setZoom((float)zoomActuel);
	}
}