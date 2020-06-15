package Fenetres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import Application.Controleur;

/*******************************************************************
 * @Titre_Classe: MENU FENETRE
 * 
 * @Resumer:
 * Creer le menu de l'application qu'on affiche a la fenetre principale.
 * Le menu contient les onglets "Fichier" et "Editer".
 * Ajout des parametres du controleur par precaution.
 * 
 * @Source: 
 * Utilisation du squelette du Travail Pratique 01 (LOG121)
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 19 juillet 2017 
 *******************************************************************/
public class MenuFenetre extends JMenuBar{

	/********************
	 * Constantes
	 ********************/
	private static final long serialVersionUID = 1536336192561843187L;
	private static final String
	ERREUR = "Fenetre erreur",
	MENU_FICHIER_TITRE = "Fichier",
	MENU_FICHIER_IMPORTER = "Importer",
	MENU_FICHIER_QUITTER = "Quitter",
	IMPOSSIBLE_FICHIER = "Impossible de trouver le fichier",
	MESSAGE_QUITTER_TITRE = "Fenetre quitter",
	MESSAGE_QUITTER_APPLICATION = "Voullez-vous vraiment quitter l'application ?";

	/********************
	 * CONSTRUCTEUR
	 ********************/
	public MenuFenetre() {
		menuFichier();
	}

	/*******************************************************************
	 * @Titre: ONGLET FICHIER
	 * 
	 * @Resumer:
	 * L'onglet "Fichier" regroupe les sous-onglets "Importer" et "Quitter". 
	 * On limite l'importation par des fichiers de type image.
	 * 
	 * @Source:
	 * getSelectedFile():	https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html	
	 * File chooser:		https://docs.oracle.com/javase/7/docs/api/javax/swing/JFileChooser.html
	 * 
	 *******************************************************************/
	protected void menuFichier() {

		MenuFenetre menuFenetre = (MenuFenetre) MenuFenetre.this.getParent();
		JMenu menu = creerMenu(MENU_FICHIER_TITRE, new String[] {MENU_FICHIER_IMPORTER, MENU_FICHIER_QUITTER});

		//Onglet "Importer" muni d'un raccourcis clavier
		menu.getItem(0).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		menu.getItem(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//Reponse de la fenetre d'ouverture de fichier
				int reponse = Controleur.getInstance().getSelectionFicher().showOpenDialog(menuFenetre);
				if(reponse == JFileChooser.APPROVE_OPTION) {
					try {
						//Importation de l'image choisi par l'utilisateur
						Controleur.getInstance().chargerImage(Controleur.getInstance().getFichierImage());
					} 
					catch (Exception exception) {
						JOptionPane.showMessageDialog(menuFenetre, IMPOSSIBLE_FICHIER, ERREUR, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		//Onglet "Quitter" muni d'un raccourcis clavier
		menu.getItem(1).setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menu.getItem(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Message de confirmation avant de quitter completement l'application
				int reponse = JOptionPane.showConfirmDialog(null, MESSAGE_QUITTER_APPLICATION, MESSAGE_QUITTER_TITRE, JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.YES_OPTION) {
					System.exit(0);
				}else{
					return;
				}	
			}
		});
		add(menu);
	}

	/*******************************************************************
	 * @Titre: CREATION MENU 
	 * 
	 * @Resumer:
	 * Permet l'ajout de l'onglet "Fichier" au menu, suivi 
	 * des sous-options.
	 * 
	 * @param titleKey champs principal
	 * @param itemKeys elements
	 * @return menu
	 *
	 *******************************************************************/
	private static JMenu creerMenu(String titleKey, String[] itemKeys) {

		JMenu menu = new JMenu(titleKey);
		for(int i = 0; i < itemKeys.length; ++i) {
			menu.add(new JMenuItem(itemKeys[i]));
		}
		return menu;
	}
}
