/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Felli
 */
public class ConexaoBD {
    public Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdvendas", "fellipemelo", "3]ewuKuqso");
                  
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
            
    }

}