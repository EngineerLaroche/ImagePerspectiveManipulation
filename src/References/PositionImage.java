package References;
import java.awt.Point;

/*******************************************************************
 * @Titre_Classe: POSITION IMAGE
 * 
 * @Résumer:
 * Cette classe regroupe tout ce qui concerne la position en un
 * point et les coordonnees d'une image. Elle supporte le
 * processus de translation à l'aide d'une souris.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 26 juillet 2017 
 *******************************************************************/
public class PositionImage{
	
	/********************
	 * Variables
	 ********************/
	private int positionX, positionY;

	/***************************
	 * CONSTRUCTEUR
	 * @Param: position X et Y
	 ***************************/
	public PositionImage(int _positionX, int _positionY){
		this.positionX = _positionX;
		this.positionY = _positionY;
	}
	
	/***************************
	 * CONSTRUCTEUR
	 * @Param: Point
	 ***************************/
	public PositionImage(Point point){
		this.positionX = (int) point.getX();
		this.positionY = (int) point.getY();
	}
	
	/********************
	 * GET COORDONNEE
	 * @return: copie position
	 ********************/
	public PositionImage getPositionImage() {
		PositionImage positionImage = new PositionImage(positionX, positionY);
		return positionImage;
	}
	
	/********************
	 * DIFFERENCE POSITION
	 * @Param: coordonnee
	 * @return: delta position
	 ********************/
	public PositionImage differencePosition(PositionImage _coordonnee) {
		int differenceX = getPositionX() - _coordonnee.getPositionX();
		int differenceY = getPositionY() - _coordonnee.getPositionY();
		PositionImage positionImage = new PositionImage(differenceX, differenceY);
		return positionImage;
	}
	
	/********************
	 * ADDITION POSITION
	 * @Param: coordonnee
	 * @return: addition position
	 ********************/
	public PositionImage additionPosition(PositionImage _coordonnee) {
		int additionX = getPositionX() + _coordonnee.getPositionX();
		int additionY = getPositionY() + _coordonnee.getPositionY();
		PositionImage positionImage = new PositionImage(additionX, additionY);
		return positionImage;
	}
	
	/********************
	 * GET POSITION X
	 * @return: position X
	 ********************/
	public int getPositionX() {
		return positionX;	
	}
	
	/********************
	 * GET POSITION Y
	 * @return: position Y
	 ********************/
	public int getPositionY() {
		return positionY;	
	}
	
	/********************
	 * SET POSITION X
	 * @Param: position X
	 ********************/
	public void setPositionX(int _positionX) {
		this.positionX = _positionX;
	}
	
	/********************
	 * SET POSITION Y
	 * @Param: position Y
	 ********************/
	public void setPositionY(int _positionY) {
		this.positionY = _positionY;
	}
}