package Fenetres;

import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import References.ReferenceOriginale;

import java.awt.*;

/*******************************************************************
 * @Titre_Classe: IMAGE ORIGINAL
 * 
 * @Résumer:
 * Tout ce qui concerne l'image et son affichage a l'ecran.
 * On utilise des mutateurs et un observateur pour supporter le tout. 
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 19 juillet 2017 
 *******************************************************************/
public class ImageOriginal extends JPanel implements Observer {

	/********************
	 * Constantes
	 ********************/
	private static final String ORIGINAL = "ORIGINAL";
	private static final long serialVersionUID = 7737311500263531518L;

	/***************************
	 * Classes instanciées
	 ***************************/
	private Image image;

	/********************
	 * Variables
	 ********************/
	private int positionX, positionY, largeur, hauteur;

	/********************
	 * Primitive
	 ********************/
	private boolean afficheOriginal = true;

	/***************************
	 * CONSTRUCTEUR
	 * @Param: position X et Y
	 * @Param: largeur et hauteur
	 ***************************/
	public ImageOriginal(int _positionX, int _positionY, int _largeur, int _hauteur) {

		this.positionX = _positionX;
		this.positionY = _positionY;
		this.largeur = _largeur;
		this.hauteur = _hauteur;

		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		this.setBounds(positionX, positionY, largeur, hauteur);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 8));
	}
	
	/*************************************************************************
	 * @Titre: GRAPHIQUE IMAGE
	 * 
	 * @Resumer:
	 * Supporte l'affichage de l'image original. 
	 * Sera utilisé comme base dans la classe ImagePerspective pour 
	 * afficher une perspective de l'image original.
	 * Affiche "ORIGINAL" sur la fenetre pour aider a reperer l'image original.
	 * 
	 * @Param: Graphics
	 * 
	 *************************************************************************/
	public void graphiqueImage(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		
		if(afficheOriginal){
			g.setColor(Color.GRAY);
			g.setFont(new Font("Font", Font.BOLD, 40));
			g.drawString(ORIGINAL, 110, 220);
			afficheOriginal = false;
		}
	}

	/********************
	 * PAINT COMPONENT
	 * @Param:Graphics
	 ********************/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphiqueImage(g);
	}

	/********************
	 * UPDATE IMAGE
	 * @Param:Observable
	 * @Param:Object
	 ********************/
	@Override
	public void update(Observable o, Object arg) {

		if(o instanceof ReferenceOriginale) {
			ReferenceOriginale referenceOriginale = (ReferenceOriginale) o;
			this.image = referenceOriginale.getImage();
			this.repaint();
		}
	}
	
	/********************
	 * GET IMAGE
	 * @return: Image
	 ********************/
	public Image getImage(){
		return image;
	}
}