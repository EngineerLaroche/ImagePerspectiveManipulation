package Application;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import References.ContenuPerspective;
import References.ReferencePerspective;





/*******************************************************************
 * @Titre_Classe: IMPORTER BINAIRE
 * 
 * @Resumer: 
 * Recupere le fichier .bin et le transforme a un fichier image.
 * Classe non terminé !
 * 
 * @Source: 
 * Inspiration: //http://www.programcreek.com/2009/02/java-convert-image-to-byte-array-convert-byte-array-to-image/
 * 
 * @Auteur: Alexandre Laroche
 * 			Maxime Gelinas
 * 			Tomy Nguyen
 * 			Xel Said
 * 
 * @Date: 25 juillet 2017
 *******************************************************************/
public class ImporterBinaire {
	
	/***************************
	 * Classes instanciees
	 ***************************/
	private ObjectInputStream inputStream;

	/***************************
	 * CONSTRUCTEUR
	 ***************************/
	public ImporterBinaire(File file) throws IOException {

		inputStream = null;
		try {
			inputStream = new ObjectInputStream(new FileInputStream(file));
			ContenuPerspective imgRecue = (ContenuPerspective) inputStream.readObject();
			ReferencePerspective reference = new ReferencePerspective();
			reference.setContenu(imgRecue.getZoom(), imgRecue.getPosition());
		} catch (ClassNotFoundException exc) {
			exc.printStackTrace();
		} catch (IOException exc) {
			exc.printStackTrace();
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}
	}
}
