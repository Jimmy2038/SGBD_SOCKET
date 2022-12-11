import java.net.*;
import java.util.Scanner;
import java.io.*;
public class Client {
  public static void main(String[] args) throws Exception {
    while(true){
      Socket s = new Socket("localhost",500);

      PrintWriter pr = new PrintWriter(s.getOutputStream());
      Scanner scan = new Scanner(System.in);
      String req=scan.nextLine();
      pr.println(req);
      pr.flush();
      
      try{
        System.out.println();
        ObjectInputStream obj=new ObjectInputStream(s.getInputStream());
        Relation re=(Relation) obj.readObject();
        re.affichage();
      }catch (Exception e){
        InputStreamReader in=new InputStreamReader(s.getInputStream());
        BufferedReader bf=new BufferedReader(in);
        String exce=bf.readLine();
        System.out.println("MISY OLANA!!!: "+exce);
      }
      finally{
        System.out.println();
        s.close();
      }
    
  }
}
}