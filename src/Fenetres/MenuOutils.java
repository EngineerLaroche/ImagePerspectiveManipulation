package Fenetres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Application.Controleur;
import Operations.InterfaceOperations;
import Operations.OperationZoom;
import References.ReferencePerspective;


/*******************************************************************
 * @Titre_Classe: MENU OUTILS
 * 
 * @Resumer:
 * Permet d'avoir un panneau qui regroupe les outils de 
 * modifications image tels que: "revenir", "retablir", "restaurer",
 * "sauvegarder", "zoom plus" et "zoom moin".
 * On inclu aussi le type de sauvegarde voulu ainsi que le chargement de fichier.

 * @Auteur: Alexandre Laroche
 * @Date: 25 juillet 2017 
 *******************************************************************/
public class MenuOutils extends JPanel{

	/********************
	 * Constante
	 ********************/
	private static final String
	RESTORE = "Restore",
	SYMBOLE_REVENIR = "<",
	SYMBOLE_RETABLIR = ">",
	SYMBOLE_ZOOM_MOIN = "-",
	SYMBOLE_ZOOM_PLUS = "+",
	ERREUR = "Fenetre erreur",
	IMPOSSIBLE_FICHIER = "Impossible de trouver le fichier",
	FICHIER_SAUVEGARDE_NULL = "Impossible d'enregistrer sans fichier";

	// Les choix des différentes sauvegardes/chargement
	private static String [] TYPE_SAUVEGARDES ={"Save Image", "Save Perspective", "Charger Perspective"};
	private static final long serialVersionUID = 2909454049873302125L;

	/***************************
	 * Classes instanciees
	 ***************************/
	private InterfaceOperations interfaceOperations;
	private ReferencePerspective referencePerspective;
	private MenuOutils menuOutils = (MenuOutils) MenuOutils.this.getParent();

	/********************
	 * Objets Swing
	 ********************/
	private JButton 
	btnRevenir, btnRetablir, 
	btnZoomMoin, btnZoomPlus, 
	btnRestaurer;

	private JComboBox<String> typeSauvegarde;

	/********************
	 * Variables
	 ********************/
	private float 
	zoomArriere = 0.8f,
	zoomAvant = 1f/0.8f;

	/********************
	 * CONSTRUCTEUR
	 ********************/
	public MenuOutils(){
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(400,40));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.setLayout(new BorderLayout());

		boutonOutils();
		panneauOutils();
	}

	/*******************************************************************
	 * @Titre_Classe: BOUTON OUTILS
	 * 
	 * @Resumer:
	 * Creation d'un bouton par outil.
	 * Pour chaque bouton, on lui ajoute un Action Listener.
	 * Les actions sont principalement reliees aux references perspectives.
	 * 
	 * @Source:
	 * JComboBox: https://stackoverflow.com/questions/16339524/jcombobox-actionlistener-how-do-i-really-use-them
	 * 
	 *******************************************************************/
	private void boutonOutils(){

		//Bouton Revenir
		btnRevenir = new JButton(SYMBOLE_REVENIR);
		btnRevenir.setFont(new Font("Arial", Font.BOLD, 14));
		btnRevenir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				referencePerspective.revenir();
			}	
		});

		//Bouton Retablir
		btnRetablir = new JButton(SYMBOLE_RETABLIR);
		btnRetablir.setFont(new Font("Arial", Font.BOLD, 14));
		btnRetablir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				referencePerspective.retablir();
			}	
		});

		//Bouton Restaurer
		btnRestaurer = new JButton(RESTORE);
		btnRestaurer.setFont(new Font("Arial", Font.BOLD, 14));
		btnRestaurer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				referencePerspective.restaurer();
			}
		});

		//Bouton Zoom reculer
		btnZoomMoin = new JButton(SYMBOLE_ZOOM_MOIN);
		btnZoomMoin.setFont(new Font("Arial", Font.BOLD, 14));
		btnZoomMoin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				interfaceOperations = new OperationZoom(referencePerspective, zoomArriere);
				referencePerspective.executerOperations(interfaceOperations);
			}	
		});

		//Bouton zoom avancer
		btnZoomPlus = new JButton(SYMBOLE_ZOOM_PLUS);
		btnZoomPlus.setFont(new Font("Arial", Font.BOLD, 14));
		btnZoomPlus.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				interfaceOperations = new OperationZoom(referencePerspective, zoomAvant);
				referencePerspective.executerOperations(interfaceOperations);
			}	
		});

		//Boite defilant qui affiche differentes options de sauvegarde et chargement
		typeSauvegarde = new JComboBox<String>(TYPE_SAUVEGARDES);
		typeSauvegarde.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String selection = (String)typeSauvegarde.getSelectedItem();

				//Sauvegarde Image
				if(selection.equals(TYPE_SAUVEGARDES[0])){
					optionSaveImage();
				}//Sauvegarde Binaire
				else if(selection.equals(TYPE_SAUVEGARDES[1])){
					optionSaveBinaire();
				}//Charger Binaire
				else if(selection.equals(TYPE_SAUVEGARDES[2])){
					optionChargerBinaire();
				}
			}	
		});
	}

	/*******************************************************************
	 * @Titre_Classe: PANNEAU OUTILS
	 * 
	 * @Resumer:
	 * Ajout des boutons sur trois panneaux differents.
	 * Les trois panneaux sont regroupes sur le panneau principal.
	 * 
	 *******************************************************************/
	private void panneauOutils(){

		JPanel panelEtat = new JPanel();
		panelEtat.setOpaque(false);
		panelEtat.add(btnRevenir, BorderLayout.WEST);
		panelEtat.add(btnRetablir, BorderLayout.EAST);

		JPanel panelSave = new JPanel();
		panelSave.setOpaque(false);
		panelSave.add(btnRestaurer, BorderLayout.WEST);
		panelSave.add(typeSauvegarde, BorderLayout.EAST);

		JPanel panelZoom = new JPanel();
		panelZoom.setOpaque(false);
		panelZoom.add(btnZoomMoin, BorderLayout.WEST);
		panelZoom.add(btnZoomPlus, BorderLayout.EAST);

		this.add(panelEtat, BorderLayout.WEST);
		this.add(panelZoom,  BorderLayout.CENTER);
		this.add(panelSave,BorderLayout.EAST);
	}

	/*******************************************************************
	 * @Titre_Classe: OPTION SAVE IMAGE
	 * 
	 * @Resumer:
	 * On retrouve l'action qui concerne la sauvegarde en un fichier Image.
	 * 
	 *******************************************************************/
	private void optionSaveImage(){
		
		//Si il n'y a pas d'image, un message d'erreur avise l'utilisateur qu'il n'y a rien à sauvegarder
		if(Controleur.getInstance().getFichierImage() == null){
			JOptionPane.showMessageDialog(null, FICHIER_SAUVEGARDE_NULL, ERREUR, JOptionPane.WARNING_MESSAGE);
		}else{
			//Reponse de la fenetre de sauvegarde
			int reponse = Controleur.getInstance().getSelectionFicher().showSaveDialog(menuOutils);
			if(reponse == JFileChooser.APPROVE_OPTION) {
				try {
					//Sauvegarde de l'image modifiee par l'utilisateur
					Controleur.getInstance().sauvegardeImage(Controleur.getInstance().getFichierImage(), referencePerspective, MenuOutils.this.getParent().getBounds());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
	}

	/*******************************************************************
	 * @Titre_Classe: OPTION SAVE BINAIRE
	 * 
	 * @Resumer:
	 * On retrouve l'action qui concerne la sauvegarde en un fichier .bin .
	 * 
	 *******************************************************************/
	private void optionSaveBinaire(){
		
		//Si il n'y a pas d'image, un message d'erreur avise l'utilisateur qu'il n'y a rien à sauvegarder
		if(Controleur.getInstance().getFichierImage() == null){
			JOptionPane.showMessageDialog(null, FICHIER_SAUVEGARDE_NULL, ERREUR, JOptionPane.WARNING_MESSAGE);
		}else{
			//Reponse de la fenetre de sauvegarde
			int reponse = Controleur.getInstance().getSelectionBinaire().showSaveDialog(menuOutils);
			if(reponse == JFileChooser.APPROVE_OPTION) {
				try {
					//Sauvegarde de l'image modifiee par l'utilisateur
					Controleur.getInstance().sauvegardeBinaire(Controleur.getInstance().getFichierBinaire(), referencePerspective, MenuOutils.this.getParent().getBounds());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}
	}
	
	/*******************************************************************
	 * @Titre_Classe: OPTION CHARGER BINAIRE
	 * 
	 * @Resumer:
	 * On retrouve l'action qui concerne l'importation d'un fichier .bin .
	 * 
	 *******************************************************************/
	private void optionChargerBinaire(){
		
		//Reponse de la fenetre d'ouverture de fichier
		int reponse = Controleur.getInstance().getSelectionBinaire().showOpenDialog(menuOutils);
		if(reponse == JFileChooser.APPROVE_OPTION) {
			try {
				//Importation de l'image choisi par l'utilisateur
				Controleur.getInstance().chargerBinaire(Controleur.getInstance().getFichierBinaire());
			} 
			catch (Exception exception) {
				JOptionPane.showMessageDialog(menuOutils, IMPOSSIBLE_FICHIER, ERREUR, JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/******************************
	 * SET REFERENCE PERSPECTIVE
	 * @Param: ReferencePerspective
	 ******************************/
	public void setReferencePerspective(ReferencePerspective _referencePerspective){
		this.referencePerspective = _referencePerspective;
	}
}
