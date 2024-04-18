/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.jdbc;

import java.awt.HeadlessException;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Felli
 */
public class TestandoConexao {
    public static void main(String[] args) {
        try {
            
            Connection connection = new ConexaoBD().getConnection();
            JOptionPane.showMessageDialog(null, "Conectei com sucesso ao banco.");
        } catch (HeadlessException erro) {
            JOptionPane.showMessageDialog(null, "A sua conexão está com ERRO: " + erro);
        }
    }
}
