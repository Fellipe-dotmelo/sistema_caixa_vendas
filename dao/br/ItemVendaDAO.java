/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.br;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import projeto.jdbc.ConexaoBD;
import model.br.ItemVenda;

/**
 *
 * @author Felli
 */
public class ItemVendaDAO {

    private final Connection con;

    public ItemVendaDAO() {
        this.con = new ConexaoBD().getConnection();
    }
    
    //cadastra itens
    public void cadastraItem(ItemVenda obj){
        try {
            // Inserindo na tabela vendas programaticamente
            String sql = "INSERT INTO tb_itensvendas(venda_id, produto_id, qtd, subtotal) VALUES(?, ?, ?, ?)";

            // Conectando ao banco
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            // Executando SQL
            stmt.execute();
            stmt.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ITEM INVÁLIDO!" + erro);

        }
    }
}
