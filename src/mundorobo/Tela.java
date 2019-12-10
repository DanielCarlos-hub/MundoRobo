/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundorobo;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class Tela {
    public static String LerString(String sMsg)
    {
        return JOptionPane.showInputDialog(sMsg);
    }
    public static int LerInteiro(String sMsg)
    {
        String sValor = LerString(sMsg);
        return Integer.parseInt(sValor);
        
    }
    public static double LerNumeroReal(String sMsg)
    {
        String sValor = LerString(sMsg);
        return Double.parseDouble(sValor);
    }
    public static void ExibirMensagem(String sMsg)
    {
        JOptionPane.showMessageDialog(null, sMsg);
    }
}
