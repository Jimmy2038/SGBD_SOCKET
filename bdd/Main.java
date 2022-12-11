import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
public class Main{
  
  public static void main(String[] args) throws Exception{
    System.out.print("Inserer un mot:");
    
    try (Scanner scan = new Scanner(System.in)) {
       Fanoratana.append("traitement.txt", scan.nextLine(),false,false);
    }
        Relation r = new Relation();
        // Vector<Vector<String>> a=r.select_All("Table");
        // Vector<Vector<String>> c=r.select_All("Tab2");
       String[] b = new String[3];
       b[0]="Prenom";
       b[1]="Nom";
       b[2]="Age";
      //  r.projection("Table", b);
      //  FileInputStream file = new FileInputStream("traitement.txt");   
      // Scanner scanner = new Scanner(file);
      // while(scanner.hasNextLine())
      // {
      //   String[] traite = scanner.nextLine().split(" ");
      //   for (int i = 0; i < traite.length; i++) {
      //     System.out.println(traite[i]);
      //   }
      // }
      //  String[] test = b[2].split(",");
      //  for (int i = 0; i < test.length; i++) {
      //   System.out.println(test[i]);
      //  }
      //  scanner.close();
      try {
         Relation re = new Relation();
       re=r.verifie();
       re.affichage();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
     
        //r.projection("Table", b);
          //  r.division("Table", "Tab2","Nom","Nom","Nom");
      //  System.out.println(r.Azoalaina(a.get(0), c.get(3))); 
      // r.maka_indice("Table", "Age");
}
}