package Operations;
import References.PositionImage;
import References.ReferencePerspective;

/*******************************************************************
 * @Titre_Classe: OPERATION TRANSLATION
 * 
 * @Resumer: 
 * Supporte le mecanisme de deplacement d'une perspective a l'ecran .
 * On garde en memoire une copie avec positionActuel pour nous
 * permettre de revenir a un etet ulterieur de la translation.
 * 
 * @Source: 
 * Inspiration MouseAdapter: http://www.java2s.com/Code/Java/Event/extendsMouseAdapterandimplementsMouseMotionListenertocreateamousedrawingprogramm.htm
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 25 juillet 2017
 *******************************************************************/
public class OperationTranslation implements InterfaceOperations{
	
	/***************************
	 * Classes instanciees
	 ***************************/
	private PositionImage positionActuelle, positionImage;
	private ReferencePerspective referencePerspective;
	
	/***************************
	 * CONSTRUCTEUR
	 * @Param: ChangementImage
	 * @Param: PositionsImage
	 ***************************/
	public OperationTranslation(ReferencePerspective _referencePerspective, PositionImage _positionsImage) {
		this.positionImage = _positionsImage;
		this.referencePerspective = _referencePerspective;
		this.positionActuelle = _referencePerspective.getPositionImage();
	}
	
	/***************************
	 * EXECUTER TRANSLATION
	 ***************************/
	public void executer() {
		referencePerspective.setPosition(positionActuelle.additionPosition(positionImage));
	}
	
	/***************************
	 * REVENIR TRANSLATION
	 ***************************/
	public void revenir() {
		referencePerspective.setPosition(positionActuelle);	
	}
}