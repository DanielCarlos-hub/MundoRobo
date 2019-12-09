/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundorobo;

import java.util.ArrayList;


/**
 *
 * @author daniel
 */
public class Robo {
    private int pontos;
    private ArrayList<Integer> movimentos;
    
    public void setPontos(int pontos){
        this.pontos = pontos;
    }
    public int getPontos(){
        return pontos;
    }
    
    public void setMovimentos(ArrayList<Integer> movimentos){
        this.movimentos = movimentos;
    }
    public ArrayList<Integer> getMovimentos(){
        return movimentos;
    }
    
    
    
    
}
