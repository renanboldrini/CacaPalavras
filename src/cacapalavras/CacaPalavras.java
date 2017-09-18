
package cacapalavras;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Random;

public class CacaPalavras {

    public CacaPalavras(String arquivo) {
        this.populaMat();
        this.lerPalavras(arquivo);
        this.apresentaMat();
    }
    char [][] matchar = new char[30][30];
    
    private int gravaPalavra(int cont, String linha) { //retorna contador
        int mod = cont % 10;
         switch (mod) {
            case 0:
                func0(linha, 3, (matchar.length - 7), 1, 1, 'c');
                return (1+cont);
            case 1:
                func0(linha, 3, (matchar.length - 7), (matchar.length-2), 1, 'c');
                return (1+cont);
            case 2:
                return func1(linha, cont, 0, 20, 1, -1, 'n');
            case 3:
                return func1(linha, cont, 0, matchar.length, 1, -1, 'n');
            case 4:
                return func1(linha, cont, (matchar.length - 6), matchar.length, -1, 1, 'i');
            case 5:
                func0(linha, 1, 1, 6, (matchar.length-20), 'l');
                return (1+cont);
            case 6:
                return func2(linha, cont, 0, 3, 1, -1, 'n');
            case 7:
                func0(linha, (matchar.length-2), 1, 6, (matchar.length-18), 'l');
                return (1+cont);    
            case 8:
                return func2(linha, cont, (matchar.length-3), matchar.length, -1, 1, 'i'); 
            case 9:
                 Random g = new Random();
                 char v = 'l';
		 if(g.nextInt(2)==0)
			 v = 'c';
                func0(linha, 10, (matchar.length - 20), 10, (matchar.length - 20), v);
                return cont;
            default:
                 System.out.println("Errrrrooooouuuu");
         } 
         
       return cont;
    }
    
    
    public void func0(String linha, int c1, int c2, int l1, int l2, char v) {
        boolean cond = false;
         Random g = new Random();
        while(cond == false){
            int c = c1+g.nextInt(c2);   
            int l = l1+g.nextInt(l2);
            if(testaEsp(l, c, 1,'c','n') == true && testaEsp(l, c, 1,'l','n') == true){
            if(testaEsp(l, c, (linha.length()+1), v, 'n') == true){
                escreve(l, c, linha, v, 'n');
                cond = true;
            }
            if(testaEsp(l, c, (linha.length()+1), v, 'i') == true){
                escreve(l, c, linha, v, 'i');
                cond = true;
            }
        }
        }        
    }//final func 0
    
	public int func1(String linha, int cont, int l1, int l2, int t1, int t2, char s) {  // revisar l2, desnecessário
       for(int l = l1; l < l2; l++){
           for(int c = 0; c < matchar.length; c++){
               if(matchar[l][c] == linha.charAt(0)){
                 if(testaEsp((l+(t1)), c, linha.length(), 'l', s)==true){
                     escreve(l, c, linha, 'l', s);
                     return (1+cont);
                 }  
                }
				if(matchar[l][c] == linha.charAt(1)){
                 if(testaEsp((l+t1), c, (linha.length()-2), 'l', s)==true && testaEsp((l+(t2)), c, 1, 'l', s)==true){
                     escreve((l+(t2)), c, linha, 'l', s);
                     return (1+cont);
                 }  
                }
           }
       }
       func0(linha, 10, (matchar.length - 20), 10, (matchar.length - 20), 'c');
       return cont;
    }//final func 1       
        
public int func2(String linha, int cont, int c1, int c2, int t1, int t2, char s) { 
       for(int l = 0; l < matchar.length; l++){
           for(int c = c1; c < c2; c++){
               if(matchar[l][c] == linha.charAt(0)){
                 if(testaEsp(l, (c+(t1)), linha.length(), 'c', s)==true){
                     escreve(l, c, linha, 'c', s);
                     return (1+cont);
                 }  
                }
				if(matchar[l][c] == linha.charAt(1)){
                 if(testaEsp(l, (c+(t1)), (linha.length()-1), 'c', s)==true && testaEsp(l, (c+(t2)), 1, 'c', s)==true){
                     escreve(l, c+(t2), linha, 'c', s);
                     return (1+cont);
                 }  
                }
           }
       }
       func0(linha, 10, (matchar.length - 20), 10, (matchar.length - 20), 'c');
       return cont;
    }//final func 2
 
 public void populaMat(){
        for (char[] matchar1 : this.matchar) {
            for (int c = 0; c< this.matchar[0].length; c++) {
                matchar1[c] = '.';
            }
        }
 }//populaMat()
 
  public void preencheMat(){
        for (char[] matchar1 : this.matchar) {
            Random g = new Random();
            for (int c = 0; c< this.matchar[0].length; c++) {
                if(matchar1[c] == '.')
                    matchar1[c] = (char) (g.nextInt(25)+(int)('a'));
            }
        }
 }//populaMat()
 
	  public boolean testaEsp(int l, int c, int tamanho, char variavel, char sentido){      
//linha / coluna / tamanho da palavra / variavel = escrita em 'l'inha ou 'c'oluna / sentido = 'n'ormal ou 'i'nverso
         if(sentido == 'n'){
             if(variavel == 'c'){
                 if(c + tamanho > (matchar.length - 1)){
                     System.out.println("não cabe na linha " + l);
                     return false;
                 }
             }
             if(variavel == 'l'){
                 if(l + tamanho > (matchar.length - 1)){
                     System.out.println("não cabe na coluna " + c);
                     return false;
                 }
             }
             for(int i = 0; i < tamanho; i++){
                 if (this.matchar[l][c] != '.')
					 return false;
				 if (variavel == 'c'){
					 if(l+1 < matchar.length){
						 if (this.matchar[l+1][c] != '.')
							return false;
					 }
					 if(l-1 > -1){
						 if (this.matchar[l-1][c] != '.')
							return false;
					 }
				 }
				 if (variavel == 'l'){
					 if(c+1 < matchar.length){
						 if (this.matchar[l][c+1] != '.')
							return false;
					 }
					 if(c-1 > -1){
						 if (this.matchar[l][c-1] != '.')
							return false;
					 }
				 }		 
                 if(variavel == 'l')
                     l++;
                 else
                     c++;
                 }
             }
         else{
             if(variavel == 'c'){
                 if(c - tamanho < 0){
                     System.out.println("não cabe na linha " + l);
                     return false;
                 }
             }
             if(variavel == 'l'){
                 if(l - tamanho < 0){
                     System.out.println("não cabe na coluna " + c);
                     return false;
                 }
             }
             for(int i = (tamanho - 1); i >= 0; i--){
                 if (this.matchar[l][c] != '.')
					 return false;
				 
				 if (variavel == 'c'){
					 if(l+1 < matchar.length){
						 if (this.matchar[l+1][c] != '.')
							return false;
					 }
					 if(l-1 > -1){
						 if (this.matchar[l-1][c] != '.')
							return false;
					 }
				 }
				 if (variavel == 'l'){
					 if(c+1 < matchar.length){
						 if (this.matchar[l][c+1] != '.')
							return false;
					 }
					 if(c-1 > -1){
						 if (this.matchar[l][c-1] != '.')
							return false;
					 }
				 }
                 if(variavel == 'l')
                     l--;
                 else
                     c--;
                 }
         }
		return true;
   }//testaEsp()
 
  public void apresentaMat(){
        for (char[] matchar1 : this.matchar) {
            for (int c = 0; c< this.matchar[0].length; c++) {
                System.out.print(" " + matchar1[c] + " ");
            }
            System.out.print("\n");
        }
 }//apresentaMat()
  
  
   public void escreve(int l, int c, String palavra, char variavel, char sentido){      
//linha / coluna / variavel = escrita em 'l'inha ou 'c'oluna / sentido = 'n'ormal ou 'i'nverso
         if(sentido == 'n'){
             for(int i = 0; i < palavra.length(); i++){
                 this.matchar[l][c] = palavra.charAt(i);
                 if(variavel == 'l')
                     l++;
                 else
                     c++;
                 }
             }
         else
             for(int i = (palavra.length() - 1); i >= 0; i--){
                 this.matchar[l][c] = palavra.charAt((palavra.length() - 1) - i);
                 if(variavel == 'l')
                     l--;
                 else
                     c--;
                 }
   }//escreve()
  
   public void lerPalavras(String nome){
       int cont = 0;
     try (FileReader arq = new FileReader(nome)) {
               BufferedReader lerArq = new BufferedReader(arq);
               
               String linha = lerArq.readLine(); 

while (linha != null) {
    System.out.printf(cont+"%s\n", linha);
    cont = gravaPalavra(cont, linha);
    linha = lerArq.readLine(); // lê da segunda até a última linha
}    
    } catch (IOException e) {
        System.err.printf("Erro na abertura do arquivo: %s.\n",
          e.getMessage());
    }
 }//lerPalavras()
    
}// Class CacaPalavras
