package Operations;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;
import Fenetres.ImagePerspective;
import References.PositionImage;
import References.ReferencePerspective;

/*******************************************************************
 * @Titre_Classe: OPERATIONS SOURIS
 * 
 * @Resumer: 
 * Supporte les mecanismes de deplacements souris.
 * Permet ce cliquer, deplacer et relacher une perspective.
 * 
 * @Source: 
 * Inspiration MouseAdapter: http://www.java2s.com/Code/Java/Event/extendsMouseAdapterandimplementsMouseMotionListenertocreateamousedrawingprogramm.htm
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 25 juillet 2017
 *******************************************************************/
public class OperationsSouris extends MouseAdapter {

	/***************************
	 * Classes instanciees
	 ***************************/
	private Point point;
	private InterfaceOperations interfaceOperations;
	private ReferencePerspective referencePerspective;
	private PositionImage positionImage, positionSouris, distanceSouris;

	/********************
	 * Variables
	 ********************/
	private float 
	zoomArriere = 0.8f,
	zoomAvant = 1f/0.8f;

	/***************************
	 * CONSTRUCTEUR
	 * @Param: ReferencePerspective
	 ***************************/
	public OperationsSouris(ReferencePerspective _referencePerspective) {
		this.referencePerspective = _referencePerspective;
	}

	/***************************
	 * DEPLACEMENT SOURIS
	 * @Param: MouseEvent
	 ***************************/
	public void mouseDragged(MouseEvent mouseEvent) {

		point = mouseEvent.getPoint();
		Object source = mouseEvent.getSource();

		if(source instanceof ImagePerspective) {
			ImagePerspective imagePerspective = (ImagePerspective) source;
			positionSouris = new PositionImage(point);
			distanceSouris = positionSouris.differencePosition(positionImage);
			imagePerspective.setPositionImage(referencePerspective.getPositionImage().additionPosition(distanceSouris));
		}
	}

	/***************************
	 * RELACHE SOURIS
	 * @Param: MouseEvent
	 ***************************/
	public void mouseReleased(MouseEvent mouseEvent) {

		point = mouseEvent.getPoint();
		positionSouris = new PositionImage(point);
		distanceSouris = positionSouris.differencePosition(positionImage);

		//Garde en memoire la nouvelle position de l'image
		int positionX = distanceSouris.getPositionX();
		int positionY = distanceSouris.getPositionY();

		if(positionX != 0 && positionY != 0) {
			OperationTranslation operationTranslation = new OperationTranslation(referencePerspective, distanceSouris);
			referencePerspective.executerOperations(operationTranslation);
		}
	}

	/***************************
	 * APPUYER SOURIS
	 * @Param: MouseEvent
	 ***************************/
	public void mousePressed(MouseEvent mouseEvent) {
		point = mouseEvent.getPoint();
		positionImage = new PositionImage(point);
	}

	/***************************
	 * ROULETTE ZOOM SOURIS
	 * @Source: https://stackoverflow.com/questions/13230254/overriding-the-mousewheellistener-in-swing
	 ***************************/
	public void rouletteSouris(JPanel panelPerspective){
		panelPerspective.addMouseWheelListener(new MouseWheelListener(){

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getWheelRotation() < 0){
					interfaceOperations = new OperationZoom(referencePerspective, zoomAvant);
					referencePerspective.executerOperations(interfaceOperations);
				}
				else if(e.getWheelRotation() > 0){
					interfaceOperations = new OperationZoom(referencePerspective, zoomArriere);
					referencePerspective.executerOperations(interfaceOperations);
				}
			}
		});
	}
}