package cacapalavras;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Exe {

    public static void main(String[] args) {     
      CacaPalavras cp = new CacaPalavras("Arq/arquivo.txt");
      cp.preencheMat();
      System.out.println("\n\n\n\n");
      cp.apresentaMat();
    
 }
}
  
//Arq/arquivo.txt