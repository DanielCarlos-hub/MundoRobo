/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundorobo;

/**
 *
 * @author daniel
 */
public class MundoRobo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int [][]MatrizMundo;
        Mundo objMundo = new Mundo();
        
        objMundo.SimularMundo();
        MatrizMundo = objMundo.getMatriz();
        
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                System.out.print(" | " + MatrizMundo[i][j]);
                if(j == 10)
                   System.out.print(" |");
            }
            System.out.print("\n");
        }
        
    }
    
}
