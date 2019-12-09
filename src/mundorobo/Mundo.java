/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundorobo;
import java.util.Random;
/**
 *
 * @author daniel
 */
public class Mundo {
    private int [][]MatrizMundo;

    
    
    public Mundo(){
        MatrizMundo = new int[12][12];
    }
   
    
    public void SimularMundo(){
        Random aleatorio = new Random();
        int objeto;
        int lata;
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 12; j++){
                objeto = aleatorio.nextInt(2); // objetos aleatorios;
                if(i == 0 || j == 0 || i == 11 || j == 11)
                    this.MatrizMundo[i][j] = 2;
                else{
                    if(objeto == 1){ // verifica se foi sorteado uma lata
                        lata = aleatorio.nextInt(2); //50% de gerar uma lata na matriz;
                        if(lata == 1)
                            this.MatrizMundo[i][j] = 1;
                        else
                            this.MatrizMundo[i][j] = 0;
                    }
                    else
                        this.MatrizMundo[i][j] = 0;
                }
                if(i == 1 && j == 1)
                    this.MatrizMundo[i][j] = 0; // local vazio de iniciação do robo;
            }
        }
    }
    
    public int[][] getMatriz(){
        return this.MatrizMundo;
    }
    
    
}
