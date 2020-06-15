package Application;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import References.ContenuPerspective;
import References.ReferenceOriginale;
import References.ReferencePerspective;

/*******************************************************************
 * @Titre_Classe: CONTROLEUR
 * 
 * @Resumer: 
 * Garde en memoire les actions portees par l'utilisateur. Regroupe
 * les actions telles que: revenir, retablir, zoom, lecture
 * et ecriture du fichier. Ajout des observateurs necessaires.
 * 
 * @Source: 
 * Inspiration Stack: https://courses.cs.washington.edu/courses/cse143/12au/lectures/UndoRedoStack.java
 * 
 * @Auteur: Alexandre Laroche
 * 			Maxime Gelinas
 * 			Tomy Nguyen
 * 			Xel Said
 * 
 * @Date: 25 juillet 2017
 *******************************************************************/
public class Controleur{

	/***************************
	 * Classes instanciees
	 ***************************/
	private JFileChooser selectionImage = new JFileChooser();
	private JFileChooser selectionBinaire = new JFileChooser();
	private ReferenceOriginale referenceOriginale = new ReferenceOriginale();

	/***************************
	 * Piles Dynamiques
	 ***************************/
	// Piles dynamiques qui garde en mémoire les differentes perspectives de l'image
	private Stack<ReferencePerspective> pilePerspectives = new Stack<ReferencePerspective>();

	/***************************
	 * Instance Controleur
	 ***************************/
	private static Controleur INSTANCE = new Controleur();

	/***************************
	 * CONSTRUCTEUR
	 ***************************/
	public Controleur() {}

	/*************************************************************************
	 * @Titre: AJOUTER PERSPECTIVE
	 * 
	 * @Resumer: 
	 * On ajoute l'etat de la perspective dans la pile dynamique et on
	 * lui ajoute une image de depart.
	 * 
	 * @Param: ReferencePerspective
	 * 
	 *************************************************************************/
	public void ajouterPerspective(ReferencePerspective referencePerspective){
		this.pilePerspectives.add(referencePerspective);
		referencePerspective.setImage(this.referenceOriginale.getImage());
		referencePerspective.restaurer();
	}

	/*************************************************************************
	 * @Titre: CHARGER IMAGE
	 * 
	 * @Resumer: 
	 * Fichier importe est envoye en parametre au modele image original.
	 * L'image importee apparait dans les fenetres de perspectives.
	 * Si l'utilisateur a utilise la roulette de la souris pour zoomer
	 * sur l'image precedante, on restaure le zoom/position a l'etat initial.
	 * 
	 * @throws IOException
	 * @Param: File
	 * 
	 *************************************************************************/
	public void chargerImage(File file) throws IOException {
		referenceOriginale.importerImage(file);
		for (ReferencePerspective referencePerspective : this.pilePerspectives){
			referencePerspective.setImage(this.referenceOriginale.getImage());
			referencePerspective.restaurer();
		}
	}

	/*************************************************************************
	 * @Titre: CHARGER BINAIRE
	 * 
	 * @Resumer: 
	 * Le processus d'importation d'un fichier binaire est non terminé.	 
	 * 
	 * @throws IOException
	 * @Param: File
	 * 
	 *************************************************************************/
	public void chargerBinaire(File file) throws IOException {
		ReferencePerspective perspective = new ReferencePerspective();
	//	ImporterBinaire importerBinaire = new ImporterBinaire(file);
		perspective.importerImagePerspective(file);
		perspective.setImage(perspective.getImage());
	}


	/*************************************************************************
	 * @Titre: SAUVEGARDE IMAGE
	 * 
	 * @Resumer: 
	 * Recuperation du contenu de l'image a sauvegarder et envoi les
	 * parametres au mecanisme d'ecriture de fichier.
	 * 
	 * @throws IOException
	 * @Param: File image
	 * @Param: ReferencePerspective
	 * @Param: Rectangle
	 * 
	 *************************************************************************/
	public void sauvegardeImage(File file, ReferencePerspective reference, Rectangle fenetrePerspectiveBordure) throws IOException {	
		ContenuPerspective contenuImage = reference.getContenu();
		SauvegardeImage sauvegardeImage = new SauvegardeImage(referenceOriginale.getFichier().getAbsolutePath(), file, contenuImage, fenetrePerspectiveBordure);
		sauvegardeImage.setImageDisqueDur();
	}

	/*************************************************************************
	 * @Titre: SAUVEGARDE IMAGE
	 * 
	 * @Resumer: 
	 * Recuperation du contenu de l'image a sauvegarder et envoi les
	 * parametres au mecanisme d'ecriture de fichier.
	 * 
	 * Non terminé
	 * 
	 * @throws IOException
	 * @Param: File image
	 * @Param: ReferencePerspective
	 * @Param: Rectangle
	 * 
	 *************************************************************************/
	public void sauvegardeBinaire(File file, ReferencePerspective reference, Rectangle fenetrePerspectiveBordure) throws IOException{
		ContenuPerspective contenuImage = reference.getContenu();
		SauvegardeImage sauvegardeImage = new SauvegardeImage(referenceOriginale.getFichier().getAbsolutePath(), file, contenuImage, fenetrePerspectiveBordure);
		new SauvegardeBinaire(referenceOriginale.getFichier().getAbsolutePath(), file, sauvegardeImage.getFile());

	}

	/*************************************************************************
	 * @Titre: GET SELECTION FICHIER
	 * 
	 * @Resumer: 
	 * On controle le type de format que l'utilisateur peut importer ou
	 * sauvegarder. Le format supporte est celui d'une image.
	 * 
	 * @Source:
	 * setFileFilter(): https://stackoverflow.com/questions/18575655/how-to-restrict-file-choosers-in-java-to-specific-files
	 * 
	 *************************************************************************/
	public JFileChooser getSelectionFicher(){
		selectionImage.setAcceptAllFileFilterUsed(false);
		selectionImage.setFileFilter(new FileNameExtensionFilter("Fichiers Image", "jpg", "png", "gif", "jpeg"));
		return selectionImage;
	}

	/*************************************************************************
	 * @Titre: GET SELECTION BINAIRE
	 * 
	 * @Resumer: 
	 * On controle le type de format que l'utilisateur peut importer ou
	 * sauvegarder. Le format supporte est celui d'un fichier .bin .
	 * 
	 * @Source:
	 * setFileFilter(): https://stackoverflow.com/questions/18575655/how-to-restrict-file-choosers-in-java-to-specific-files
	 * 
	 *************************************************************************/
	public JFileChooser getSelectionBinaire(){
		selectionBinaire.setAcceptAllFileFilterUsed(false);
		selectionBinaire.setSelectedFile(new File("Sauvegarde.bin"));
		selectionBinaire.setFileFilter(new FileNameExtensionFilter("Fichiers Binaire", "bin"));
		return selectionBinaire;
	}

	/***************************
	 * GET FICHIER IMAGE
	 ***************************/
	public File getFichierImage(){
		return selectionImage.getSelectedFile();
	}

	/***************************
	 * GET FICHIER BINAIRE
	 ***************************/
	public File getFichierBinaire(){
		return selectionBinaire.getSelectedFile();
	}

	/***************************
	 * GET REFERENCE ORIGINAL
	 ***************************/
	public ReferenceOriginale getReferenceOriginale(){
		return this.referenceOriginale;
	}

	/***************************
	 * GET INSTANCE
	 ***************************/
	public static Controleur getInstance(){
		return INSTANCE;
	}
}