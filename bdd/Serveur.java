import java.net.*;
import java.util.Scanner;
import java.io.*;
public class Serveur {
  public static void main(String[] args) throws Exception {
    while(true){
      ServerSocket  ss = new ServerSocket(500);
      Socket s = ss.accept();
        
      System.out.println("Client connected");

      InputStreamReader in = new InputStreamReader(s.getInputStream());
      BufferedReader bf = new BufferedReader(in);
      String requet = bf.readLine();
      Relation r = new Relation();
      try (Scanner scan = new Scanner(System.in)) {
          Fanoratana.append("traitement.txt", requet,false,false);
      }
      try {
        Relation rela= new Relation();
        r=rela.verifie();
        
      } catch (Exception e) {
            PrintWriter pr= new PrintWriter(s.getOutputStream());
            pr.println(e.getMessage());
            pr.flush();
      
      }
      System.out.println("Requet envoyer");
      System.out.println();
        OutputStream getobj=s.getOutputStream();
        ObjectOutputStream sendobj=new ObjectOutputStream(getobj);
        sendobj.writeObject(r);
        ss.close();
    }
    
  }
}