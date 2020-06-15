package References;

/**
 * Structure pour le contenu à transmettre d'une perspective
 * 
 * @author Maxime Gelinas
 * @date 05-08-17
 */
public class ContenuPerspective {
	private PositionImage positionImage;
	private float zoom;
	
	/**
	 * Constructeur
	 * @param position une position
	 * @param zoom un zoom
	 */
	public ContenuPerspective(PositionImage position, float zoom){
		this.positionImage = position;
		this.zoom = zoom;
	}
	
	/**
	 * Obtient une position
	 * @return une position
	 */
	public PositionImage getPosition(){
		return this.positionImage;
	}
	
	/**
	 * Obtient un zoom
	 * @return un zoom
	 */
	public float getZoom(){
		return this.zoom;
	}
}
