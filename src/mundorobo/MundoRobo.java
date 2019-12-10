/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundorobo;

import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author daniel
 */
public class MundoRobo {
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        final int robos = 100;
        final int movimentos = 200;
        File f = new File("C:\\Users\\danie\\Desktop\\MundoRobo\\resultado.txt");
        f.createNewFile();
        FileWriter arq = new FileWriter(f.getAbsolutePath());
        PrintWriter gravarArq = new PrintWriter(arq);
        
        int geracoes;
        geracoes = Tela.LerInteiro("Quantas Gerações?");     
        
        int [][]MatrizMundo;
        int [][]MatrizPassos;
        int [][]MatrizSelecao;
        int [][]MatrizCruzamento;
        int [][]MatrizIndividuos;
        int []Pontuacao;
        
        MatrizPassos = new int[robos][movimentos];
        MatrizSelecao = new int[robos][movimentos];
        MatrizCruzamento = new int[robos][movimentos];
        MatrizIndividuos = new int [robos][movimentos];
        Pontuacao = new int [robos];

        Mundo objMundo = new Mundo();
        
        ArrayList<Robo> Robos = new ArrayList<Robo>();
        ArrayList<Integer> passos = new ArrayList<Integer>();
        Random aleatorio = new Random();

        gravarArq.printf("+------------------------------------------------------Resultado------------------------------------------------------+%n%n");
        int q = 0;
        while(q < geracoes){
            objMundo.SimularMundo();
            MatrizMundo = objMundo.getMatriz();
            int posicao;
            int i = 0;
            while( i < robos){
                Robo objRobo = new Robo();
                Robos.add(i, objRobo);
                int j = 0;
                int pontos = 0;
                passos.clear();
                while(j < movimentos){
                    for(int l = 1; l < 11; l++){
                        if(j == movimentos){
                            break;          
                        }
                        for(int c = 1; c < 11; c++){
                            if(j == movimentos){
                                break;
                            }
                            int movimento = aleatorio.nextInt(7);
                            if(movimento == 0){
                                posicao = MatrizMundo[l-1][c];
                                if(posicao == 2){
                                    pontos = pontos - 5;
                                    MatrizMundo[l][c] = MatrizMundo[l+1][c];
                                }
                            }
                            if(movimento == 1){
                               posicao = MatrizMundo[l+1][c];
                               if(posicao == 2){
                                   pontos = pontos - 5;
                                   MatrizMundo[l][c] = MatrizMundo[l-1][c];
                                }
                            }      
                            if(movimento == 2){
                                posicao = MatrizMundo[l][c+1];
                                if(posicao == 2){
                                    pontos = pontos - 5;
                                    MatrizMundo[l][c] = MatrizMundo[l][c-1];
                                }
                            }
                            if(movimento == 3){
                                posicao = MatrizMundo[l][c-1];
                                if(posicao == 2){
                                    pontos = pontos - 5;
                                    MatrizMundo[l][c] = MatrizMundo[l][c+1];
                                }
                            }
                            if(movimento == 4){
                                movimento = aleatorio.nextInt(4);
                                if(movimento == 0){
                                    posicao = MatrizMundo[l-1][c];
                                    if(posicao == 2){
                                        pontos = pontos - 5;
                                        MatrizMundo[l][c] = MatrizMundo[l+1][c];
                                    }
                                }
                                if(movimento == 1){
                                   posicao = MatrizMundo[l+1][c];
                                   if(posicao == 2){
                                       pontos = pontos - 5;
                                       MatrizMundo[l][c] = MatrizMundo[l-1][c];
                                    }
                                }      
                                if(movimento == 2){
                                    posicao = MatrizMundo[l][c+1];
                                    if(posicao == 2){
                                        pontos = pontos - 5;
                                        MatrizMundo[l][c] = MatrizMundo[l][c-1];
                                    }
                                }
                                if(movimento == 3){
                                    posicao = MatrizMundo[l][c-1];
                                    if(posicao == 2){
                                        pontos = pontos - 5;
                                        MatrizMundo[l][c] = MatrizMundo[l][c+1];
                                    }
                                }                        
                            }
                            if(movimento == 5){

                            }
                            if(movimento == 6){
                                posicao = MatrizMundo[l][c];
                                if(posicao == 1){
                                    pontos = pontos + 10;
                                }
                                else
                                    pontos = pontos - 1;
                            }
                            passos.add(j,movimento);
                            MatrizPassos[i][j] = passos.get(j);
                            j++;

                        }

                    }

                }
                Robos.get(i).setPontos(pontos);

                i++;

            }


            for(int m = 0; m < robos; m++){
                int selecao1 = aleatorio.nextInt(robos);
                int selecao2 = aleatorio.nextInt(robos);
                if(selecao1 == selecao2){ // se os rands forem iguais gera novamente;
                    selecao1 = aleatorio.nextInt(robos);
                    selecao2 = aleatorio.nextInt(robos);               
                }
                if(Robos.get(selecao1).getPontos() < Robos.get(selecao2).getPontos()){
                    for(int n = 0; n < MatrizPassos[selecao2].length; n++){
                        MatrizSelecao[m][n] = MatrizPassos[selecao2][n];
                    }
                }
                else{
                    for(int n = 0; n < MatrizPassos[selecao1].length; n++){
                        MatrizSelecao[m][n] = MatrizPassos[selecao1][n];
                    }                
                }
            }


            for(int l = 0; l < MatrizIndividuos.length; l++){
                for(int c = 0; c < MatrizIndividuos[l].length; c++){
                    MatrizIndividuos[l][c] = MatrizSelecao[l][c]; // preenche a matriz de individuos com a matriz de seleção
                }
            }


            for(int l = 0; l < robos; l++){
                int chance_cruzamento = aleatorio.nextInt(100);
                if (chance_cruzamento > 80 && chance_cruzamento <= 100) {
                    int selecao1 = aleatorio.nextInt(robos);
                    int selecao2 = aleatorio.nextInt(robos);
                    if(selecao1 == selecao2){
                        selecao1 = aleatorio.nextInt(robos);
                        selecao2 = aleatorio.nextInt(robos);               
                    }

                    for(int c = 0; c < MatrizCruzamento[l].length; c++){
                        if(c > movimentos / 2){///corte
                            MatrizIndividuos[selecao1][c] = MatrizIndividuos[selecao2][c];
                            MatrizIndividuos[selecao2][c] = MatrizIndividuos[selecao1][c];
                        }
                    }
                }


            }



            ///Mutação
            for(int l = 0; l < robos; l++){
                int mutacao = aleatorio.nextInt(100);
                if(mutacao > 97 && mutacao <= 100){ //3% de chance de mutação
                    int gene = aleatorio.nextInt(200); // sorteia onde aplicar a mutação
                    int selecao1 = aleatorio.nextInt(robos);
                    int selecao2 = aleatorio.nextInt(robos);
                    int selecao3 = aleatorio.nextInt(robos);

                    if(selecao1 == selecao2 || selecao1 == selecao3 || selecao2 == selecao3){ //para garantir que não seja sorteado o mesmo numero
                        selecao1 = aleatorio.nextInt(robos);
                        selecao2 = aleatorio.nextInt(robos); 
                        selecao3 = aleatorio.nextInt(robos);
                    }                

                    MatrizIndividuos[selecao1][gene] = 0;
                    MatrizIndividuos[selecao2][gene] = 0;
                    MatrizIndividuos[selecao3][gene] = 0;
                }
            }
            

         
            ///Gera a pontuação Final de cada geração
            int r = 0;
            while(r < robos) {
                int pontos = 0;
                int k = 0;
                while (k < movimentos) {
                    for (int l = 1; l < 11; l++) {
                        if (k == 200) {
                            break;
                        }
                        for (int c = 1; c < 11; c++) {
                            if (r == 200) {
                                break;
                            }
                            int movimento = MatrizIndividuos[r][k];
                            
                            switch (movimento) {
                                case 0:
                                    posicao = MatrizMundo[l - 1][c];
                                    if (posicao == 2) {
                                        pontos = pontos - 5;
                                        MatrizMundo[l][c] = MatrizMundo[l + 1][c];
                                    }
                                    break;
                                case 1:
                                    posicao = MatrizMundo[l + 1][c];
                                    if (posicao == 2) {
                                        pontos = pontos - 5;
                                        MatrizMundo[l][c] = MatrizMundo[l - 1][c];
                                    }
                                    break;
                                case 2:
                                    posicao = MatrizMundo[l][c + 1];
                                    if (posicao == 2) {
                                        pontos = pontos - 5;
                                        MatrizMundo[l][c] = MatrizMundo[l][c - 1];
                                    }
                                    break;
                                case 3:
                                    posicao = MatrizMundo[l][c - 1];
                                    if (posicao == 2) {
                                        pontos = pontos - 5;
                                        MatrizMundo[l][c] = MatrizMundo[l][c + 1];
                                    }
                                    break;
                                case 4:
                                    movimento = aleatorio.nextInt(4);
                                    switch (movimento) {
                                        case 0:
                                            posicao = MatrizMundo[l - 1][c];
                                            if (posicao == 2) {
                                                pontos = pontos - 5;
                                                MatrizMundo[l][c] = MatrizMundo[l + 1][c];
                                            }
                                        case 1:
                                            posicao = MatrizMundo[l + 1][c];
                                            if (posicao == 2) {
                                                pontos = pontos - 5;
                                                MatrizMundo[l][c] = MatrizMundo[l - 1][c];
                                            }
                                        case 2:
                                            posicao = MatrizMundo[l][c + 1];
                                            if (posicao == 2) {
                                                pontos = pontos - 5;
                                                MatrizMundo[l][c] = MatrizMundo[l][c - 1];
                                            }
                                        case 3:
                                            posicao = MatrizMundo[l][c - 1];
                                            if (posicao == 2) {
                                                pontos = pontos - 5;
                                                MatrizMundo[l][c] = MatrizMundo[l][c + 1];
                                            }
                                    }
                                    break;
                                case 5:
                                    break;
                                case 6:
                                    posicao = MatrizMundo[l][c];
                                    if (posicao == 1) {
                                        pontos = pontos + 10;
                                    } else {
                                        pontos = pontos - 1;
                                    }
                                    break;
                            }
                            k++;

                        }

                    }
                
                }
               
                Pontuacao[r] = pontos;

                r++;
            }

            
            gravarArq.printf("+-----------Geração Nº %d-----------+%n", q);
            gravarArq.printf("+-----------Matriz Mundo-----------+%n");
            for(int l = 0; l < MatrizMundo.length; l++) {
                for(int c = 0; c < MatrizMundo[l].length; c++){
                    gravarArq.printf("|%d", MatrizMundo[l][c]);
                }
                gravarArq.printf("|%n");
            }
            gravarArq.printf("+----------------------------------------------------+%n");
            for (int p = 0; p < Pontuacao.length; p++) {
                gravarArq.printf("Geração %d | Individuo %d | Pontuacao: %d %n", q, p, Pontuacao[p]);
                
            }
            gravarArq.printf("+----------------------------------------------------+%n");
            int maior = Pontuacao[0];
            int individuo = 0;
            for (int p = 0; p < Pontuacao.length; p++) {
                if(Pontuacao[p] > maior){
                    maior = Pontuacao[p];
                    individuo = p;
                }
            }
            gravarArq.printf("Maior Pontuação da Geração %d: Individuo %d | Pontuação: %d %n", q, individuo, maior);
            gravarArq.printf("+------------------------------------------------------------Fim------------------------------------------------------------+%n");
            q++;
        }
        arq.close(); 
           
    }


}
