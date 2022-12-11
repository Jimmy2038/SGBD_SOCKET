import java.io.*;
import java.util.Scanner;
import java.util.Vector;
public class Relation implements Serializable{
  public Relation() {
    
  }
  Vector<Vector<String>> reponse;
  public Relation(Vector<Vector<String>> reponse) {
    this.setReponse(reponse);
  }
  public Vector<Vector<String>> getReponse() {
    return reponse;
  }
  public void setReponse(Vector<Vector<String>> reponse) {
    this.reponse = reponse;
  }
  public void affichage(){
    for (int i = 0; i < reponse.size(); i++) {
      for (int j = 0; j < reponse.get(i).size(); j++) {
        // if (j+1<reponse.get(i).size()) {
          System.out.print(reponse.get(i).get(j)+"  ");
        // }
        
      }
      System.out.println("");
    }
  }
  public Relation verifie() throws Exception{
    Relation r = new Relation();
    // A traiter
      FileInputStream file = new FileInputStream("traitement.txt");   
      Scanner scanner = new Scanner(file);
      
    try
    { 
      
      
      //renvoie true s'il y a une autre ligne à lire
      while(scanner.hasNextLine())
      {
        
       String[] traite = scanner.nextLine().split(" ");
       if("ampidiro".hashCode()==traite[0].hashCode()){
        Insertion(traite[2]);
        throw new Exception("    Inseree");
      }
          if("alaivo_rehetra".hashCode()==traite[0].hashCode()){
            r=maka_rehetra(traite[1]);
          return r;
          }
          if("alaivo".hashCode()==traite[0].hashCode()){
            String[] alaina=traite[1].split(",");
            r=projection(traite[2], alaina);
           return r;
          }
          if ("atambaro".hashCode()==traite[0].hashCode()) {
            r=Union(traite[1], traite[2]);
            return r;
          }
          if ("intersection".hashCode()==traite[0].hashCode()) {
            r=intersection(traite[1], traite[2]);
            return r;
          }
          if ("mifanala".hashCode()==traite[0].hashCode()) {
            r=difference(traite[1], traite[2]);
            return r;
          }
          if ("cartesienne".hashCode()==traite[0].hashCode()) {
            r=produit_cartesienne(traite[1], traite[2]);
            return r;
          }
          if ("ampifandraiso".hashCode()==traite[0].hashCode() && "eto".hashCode()==traite[1].hashCode()) {
            String[] ampitahaina = traite[2].split("=");
            r=Tetha_Join(traite[3], traite[4], ampitahaina[0], ampitahaina[1]);
            return r;
          }
          if ("zarao".hashCode()==traite[0].hashCode() && "eto".hashCode()==traite[2].hashCode() && "aseho".hashCode()==traite[4].hashCode()){
            String[] tab= traite[1].split(",");
            String[] ampitahaina = traite[3].split("=");
            r=division(tab[0], tab[1], ampitahaina[0], ampitahaina[1], traite[5]);
            return r;
          }else{
            throw new Exception("    Commande tsy mi-existe");
          }
          
      }
         
      
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }finally{
      scanner.close(); 
    }
    return r;
  }
  public Relation division(String tab1,String tab2,String attribut,String attribut2,String nom_att_aseho) throws Exception{
    Vector<Vector<String>> val = new Vector<Vector<String>>();
    Relation r = new Relation(val);
      Vector<Vector<String>> R1 = select_All(tab1);
      Vector<Vector<String>> R2 = select_All(tab2);

        int indice1 =0;
        int indice2 =0;
        int verif =0;
        int verif2 =0;
        int indice3 =0;
        int verif3 =0;
        Vector<String> union = new Vector<String>();
        for (int i = 0; i < R1.get(0).size(); i++) {
          if (R1.get(0).get(i).hashCode()==attribut.hashCode()) {
            indice1=i;
            verif++;
          }
          if(R1.get(0).get(i).hashCode()==nom_att_aseho.hashCode()){
            indice3=i;
            union.addElement(R1.get(0).get(indice3));
            val.addElement(union);
            verif3++;
          }
        }
        if(verif==0){//refa tss attribut
            throw new Exception("    Tsy misy io "+attribut+" io ao @ table "+tab1);
          }
          if(verif3==0){//refa tss attribut
            throw new Exception("    Tsy misy io "+nom_att_aseho+" io ao @ table "+tab1);
          }
        for (int i = 0; i < R2.get(0).size(); i++) {
          if (R2.get(0).get(i).hashCode()==attribut2.hashCode()) {
            indice2=i;
            verif2++;
          }
        }
        if(verif2==0){//refa tss attribut
          throw new Exception("    Tsy misy io "+attribut2+" io ao @ table "+tab2);
            
          }
      
        Vector<String> stock_manana = new Vector<String>();
        for (int i = 1; i < R2.size(); i++) {
          for (int j = 1; j < R1.size(); j++) {
            if (R1.get(j).get(indice1).hashCode()==R2.get(i).get(indice2).hashCode()) {
              stock_manana.addElement(R1.get(j).get(indice3));
            }
          }
        }
      
        
        while (stock_manana.size()!=0) {//izy ihany no atao mitety anazy de  refa misy mitovy de solona chiffre le mitovy na asorona
          int isa_mitovy =1;
          Vector<Integer> indice = new Vector<Integer>();
          Vector<String> diviser = new Vector<String>();
          for (int j = 0; j < stock_manana.size(); j++) {
            
            if (0!=j && stock_manana.get(0).hashCode()==stock_manana.get(j).hashCode()) { 
              indice.addElement(j);
              isa_mitovy++;
            }
          }
          for (int j = 0; j < indice.size(); j++) {
            stock_manana.remove((int)indice.get(j));
          }
          if (isa_mitovy==R2.size()-1){
            diviser.addElement(stock_manana.get(0));
            val.addElement(diviser);
          }
          stock_manana.remove(0);
        }
        
        r = new Relation(val);
    
    

    // alaina ny indice mitovy @reo attribut reo
    
    return r;
  }

  public Relation Tetha_Join(String tab1,String tab2,String ampitahaina,String ampitahaina2) throws Exception{
    Vector<Vector<String>> val = new Vector<Vector<String>>();
     Relation  r = new Relation(val);
      Vector<Vector<String>> R1 = select_All(tab1);
      Vector<Vector<String>> R2 = select_All(tab2);
    // alaina ny indice mitovy @reo ampitahaina reo
    int indice1 =0;
    int indice2 =0;
    int verif =0;
    int verif2 =0;
    Vector<String> union = new Vector<String>();
    for (int i = 0; i < R1.get(0).size(); i++) {
      union.addElement(R1.get(0).get(i));
      if (R1.get(0).get(i).hashCode()==ampitahaina.hashCode()) {
        indice1=i;
        verif++;
      }
    }
    if(verif==0){//refa tss attribut
        throw new Exception("    Tsy misy io "+ampitahaina+" io ao @ table "+tab1);
      }
    for (int i = 0; i < R2.get(0).size(); i++) {
      union.addElement(R2.get(0).get(i));
      if (R2.get(0).get(i).hashCode()==ampitahaina2.hashCode()) {
        indice2=i;
        verif2++;
      }
    }
    if(verif2==0){//refa tss attribut
        throw new Exception("    Tsy misy io "+ampitahaina2+" io ao @ table "+tab2);
      }
    val.addElement(union);
    
      for (int i = 1; i < R1.size(); i++) {
        for (int j = 1; j < R2.size(); j++) {
          if (R1.get(i).get(indice1).hashCode()==R2.get(j).get(indice2).hashCode()) {
            Vector<String> joi = new Vector<String>();
            for (int k = 0; k < R1.get(i).size(); k++) {
               joi.addElement(R1.get(i).get(k));
            }
            for (int k = 0; k < R2.get(j).size(); k++) {
              joi.addElement(R2.get(j).get(k));
           }val.addElement(joi);
          }
        }
        
      }
      r = new Relation(val);
    
    return r;
  }

  public int maka_indice(String nom_file,String att) throws Exception{
    int rep=0;
    // contains
    FileInputStream file = new FileInputStream(nom_file+".txt");   
    Scanner scanner = new Scanner(file);  
    
    //renvoie true s'il y a une autre ligne à lire
    while(scanner.hasNextLine())
    {
      String[] ol = scanner.nextLine().split(",");
      for (int i = 0; i < ol.length; i++) {
        if (ol[i].hashCode()==att.hashCode()) {
          rep=i;
        }
      }      
    }
    scanner.close();
    return rep;
  }

  public Vector<String>  concat(Vector<String> tab1,Vector<String> tab2){
    Vector<String> val = new Vector<String>();
    for (int i = 0; i < tab1.size(); i++) {
      val.addElement(tab1.get(i));
    }
    for (int i = 0; i < tab2.size(); i++) {
      val.addElement(tab2.get(i));
    }
    
    return val;
  }

  public Relation produit_cartesienne(String tab1,String tab2) throws Exception{
    Vector<Vector<String>> val = new Vector<Vector<String>>();
    Relation r = new Relation(val);
      Vector<Vector<String>> R1 = select_All(tab1);
      Vector<Vector<String>> R2 = select_All(tab2);
    val.addElement(concat(R1.get(0),R2.get(0)));
    for (int i = 1; i < R1.size(); i++) {
      Vector<String> concat = new Vector<String>();
      for (int j = 1; j < R2.size(); j++) {
          val.addElement(concat(R1.get(i),R2.get(j)));
      }
      val.addElement(concat);
    }
    concat(R1.get(0), R2.get(0));
    
    r = new Relation(val);
  
    return r;
  }

  public Relation difference(String tab1,String tab2) throws Exception{
    Vector<Vector<String>> val = new Vector<Vector<String>>();
    Relation r = new Relation(val);
    
      Vector<Vector<String>> R1 = select_All(tab1);
      Vector<Vector<String>> R2 = select_All(tab2);
    
    for (int i = 0; i < R1.size(); i++) {
      val.addElement(R1.get(i));
    }
    
      for (int j = 1; j < val.size(); j++) {//atao 1 @zay tsy raisiny ny nom sy prenom
        for (int i = 0; i < R2.size(); i++) {
      
        if (tsy_mitovy(R2.get(i), val.get(j))==false) {
          val.remove(j);
        }
      }
      
    }

    
       r = new Relation(val);
    return r;
  }

  public Relation intersection(String tab1,String tab2) throws Exception{
    Vector<Vector<String>> val = new Vector<Vector<String>>();
    Relation r = new Relation(val);
    
      Vector<Vector<String>> R1 = select_All(tab1);
      Vector<Vector<String>> R2 = select_All(tab2);

    for (int i = 0; i < R2.size(); i++) {
      for (int j = 0; j < R1.size(); j++) {
        if (tsy_mitovy(R2.get(i), R1.get(j))==false) { //refa mitovy de apidirina
          val.addElement(R2.get(i));
        }
      }
    }  
    
       r = new Relation(val);
      
    return r;
  }

  public boolean tsy_mitovy(Vector<String> tab1,Vector<String> tab2) throws Exception{
    boolean verifie = true;
    int count = 0;
    if(tab1.size()!=tab2.size()){
      throw new Exception("    La taille du tableau doit etre le meme");
    }
    for (int i = 0; i < tab1.size(); i++) {
      for (int j = 0; j < tab2.size(); j++) {
        if (tab1.get(i).equals(tab2.get(j))==true) {
          count =count+1;
        }
      }
    }
    if (count==tab1.size()) {
      verifie=false;
    }
    return verifie;
  }
  public  Relation Union(String tab1,String tab2) throws Exception{
    Vector<Vector<String>> val = new Vector<Vector<String>>();
    Relation r = new Relation(val);
    
      Vector<Vector<String>> R1 = select_All(tab1);
      Vector<Vector<String>> R2 = select_All(tab2);
    
    for (int i = 0; i < R1.size(); i++) {
      val.addElement(R1.get(i));
    }
    for (int i = 0; i < R2.size(); i++) {
      int count =0;
      for (int j = 0; j < val.size(); j++) {
        if (tsy_mitovy(R2.get(i), val.get(j))==true) {
          count = count+1;
        }
      }
      if (count==val.size()) { //Matoa mitovy @le size ny count de tss mitovy aminy 
          val.addElement(R2.get(i));
      }
    }
      
       r = new Relation(val);
    
    return r;
  } 
  public Vector<Vector<String>>  select_All(String nom_table) throws Exception{
    Vector<Vector<String>> val = new Vector<Vector<String>>();
    
      // contains
      File file = new File(nom_table+".txt");   
      
     
    if(file.exists()){
      Scanner scanner = new Scanner(file);    
      //renvoie true s'il y a une autre ligne à lire
      while(scanner.hasNextLine())
      {
        String[] ol = scanner.nextLine().split(",");
        Vector<String> res = new Vector<String>();
        for (int i = 0; i < ol.length; i++) {
          res.addElement(ol[i]);
        }
        val.addElement(res);
        
      }
      
      scanner.close();    
    }
    else{
      throw new Exception("    Table tsy mi-existe");
    }
    return val;
  }
  public Relation maka_rehetra(String nom_table)throws Exception{
    Vector<Vector<String>> val = new Vector<Vector<String>>();
    Relation r = new Relation();
    
      File file = new File(nom_table+".txt");
      if(file.exists()) {   
      Scanner scanner = new Scanner(file);   
      //renvoie true s'il y a une autre ligne à lire
        while(scanner.hasNextLine())
        {
          String[] ol = scanner.nextLine().split(",");
          Vector<String> res = new Vector<String>();
          for (int i = 0; i < ol.length; i++) {
            res.addElement(ol[i]);
          }
          val.addElement(res);
          
        }
        r=new Relation(val);
        scanner.close();
    } else{
      // TODO: handle exception
       throw new Exception("    Table tsy mi-existe");
    }
      
      
    return r;
  }
  public Relation projection(String nom_table,String[] izay_alaina) throws Exception{
    Vector<Vector<String>> rep = new Vector<Vector<String>>();
    Relation r = new Relation(rep);
    int[] indice = new int[izay_alaina.length];
    
      Vector<Vector<String>> donne_table = select_All(nom_table);
    
    for (int j2 = 0; j2 < izay_alaina.length; j2++) {
        for (int i = 0; i < donne_table.get(0).size(); i++) {
            if(izay_alaina[j2].hashCode() == donne_table.get(0).get(i).hashCode()){
              indice[j2]=i;
              break;
            }
        }
      }
      for (int i = 0; i < indice.length; i++) {
        if (izay_alaina[i].hashCode() != donne_table.get(0).get(indice[i]).hashCode()) {
              throw new Exception("    Tsy misy "+izay_alaina[i]+" ao anaty table");
        }
      }
     for (int j = 0; j < donne_table.size(); j++) {
           Vector<String> val = new Vector<String>();
          for (int k = 0; k < izay_alaina.length; k++) {
            val.addElement(donne_table.get(j).get(indice[k]));  
        }
        rep.addElement(val);
     }
    
       r = new Relation(rep); 
    
    return r;
  }
public void Insertion(String nom_table) throws Exception{
    // contains
      FileInputStream file = new FileInputStream("traitement.txt");   
      Scanner scanner = new Scanner(file);  
      File cmd = new File(nom_table+".txt");   
      
      //renvoie true s'il y a une autre ligne à lire
    
    if(cmd.exists()){ 
      Scanner scan = new Scanner(cmd);
      String[] traite = scanner.nextLine().split(" ");
      String[] table;
      while(scanner.hasNextLine())
      {
        traite = scanner.nextLine().split(" ");
      }
      while (scan.hasNextLine()) {
          table = scan.nextLine().split(",");
           
          String[] inser = traite[1].split(",");
          // String[] reference = table[0].split(",");
          if (inser.length==table.length) {
            Fanoratana.append(nom_table+".txt", traite[1], true, true);
            break;
          }else{
            throw new Exception("    Tsy mitovy isa ny argument"); 
          }
           
        }
      scan.close(); 
    }else{
      scanner.close();
      throw new Exception("    Table tsy mi-existe");
      
    }
      scanner.close();
  }
  
}