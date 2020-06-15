package Fenetres;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*******************************************************************
 * @Titre_Classe: FENETRE PRINCIPALE
 * 
 * @Resumer:
 * Creation d'une fenetre principale avec un JFrame.
 * Ajout du menu avec les parametres du controleur.

 * @Auteur: Alexandre Laroche
 * @Date: 19 juillet 2017 
 *******************************************************************/
public class FenetrePrincipale extends JFrame{

	/***************************
	 * Classes instanciees
	 ***************************/
	private JPanel container = new JPanel();
	
	/********************
	 * Constantes
	 ********************/
	private static final long serialVersionUID = 3287456630299845621L;
	private static final String TITRE_FRAME = "Perspectives";
	private static final int LARGEUR = 1024, HAUTEUR = 768;
	
	/********************
	 * CONSTRUCTEUR
	 ********************/
	public FenetrePrincipale(){

		MenuFenetre menuFenetre = new MenuFenetre();
		JScrollPane scrollBar = new JScrollPane(container,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		
		this.setVisible(true);
		this.setResizable(false);	
		this.setTitle(TITRE_FRAME);
		this.setSize(LARGEUR, HAUTEUR);
		this.setLayout(new BorderLayout());
		this.add(scrollBar, BorderLayout.EAST);
		this.add(menuFenetre, BorderLayout.NORTH);
		this.add(container, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/********************
	 * CONTENEUR
	 * @Param: JComponent
	 ********************/
	public void conteneur(JComponent _component) {
		
		//Ajout des JComponents (Image originale et perspectives) sur JPanel
		container.setLayout(null);
		container.add(_component);
	}
}