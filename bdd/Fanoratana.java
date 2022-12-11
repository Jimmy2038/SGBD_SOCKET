import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/** 
 * Ajouter une ligne à un fichier texte
 * http://www.fobec.com/java/970/ajouter-une-ligne-fichier-texte.html
 * Axel 2010
 */
public class Fanoratana {
/**
 * Ouvrir le fichier et ajouter une ligne de texte
 * @param filename
 * @param text
 */
    public static void append(String filename, String text,Boolean no_ecrase,Boolean saut_Ligne) {
        BufferedWriter bufWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename, no_ecrase);
            bufWriter = new BufferedWriter(fileWriter);
            //Insérer un saut de ligne 
           if (saut_Ligne == true) {
            bufWriter.newLine();
           }
            bufWriter.write(text);
            bufWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Fanoratana.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufWriter.close();
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(Fanoratana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }  
 
    /**
     * Exemple
     * @param args 
     */
}