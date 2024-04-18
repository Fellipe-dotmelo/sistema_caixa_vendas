/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.br;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.br.Clientes;
import model.br.Vendas;
import projeto.jdbc.ConexaoBD;

/**
 *
 * @author Felli
 */
public class VendasDAO {

    private final Connection con;

    public VendasDAO() {
        this.con = new ConexaoBD().getConnection();
    }

    //Cadastrando VENDAS
    public void cadastrarVenda(Vendas obj) {
        try {
            // Verificar se o cliente é válido
            // Inserindo na tabela vendas programaticamente
            String sql = "INSERT INTO tb_vendas(cliente_id, data_venda, total_venda, observacoes) VALUES(?, ?, ?, ?)";

            // Conectando ao banco
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());

            // Executando SQL
            stmt.execute();
            stmt.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ERRO AO REGISTRAR VENDA" + erro);

        }
    }

//retorna a ultima venda
    public int retornaUltimaVenda() {
        try {
            int idvenda = 0;

            String sql = "SELECT max(id) as max_id from tb_vendas";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idvenda = rs.getInt("max_id");
            }

            return idvenda;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vendas> listarVendas(LocalDate data_inicio, LocalDate data_fim) {

        //filtra vendas - por datas
        try {
            //criando lista
            List<Vendas> lista = new ArrayList<>();

            //comando sql
            //v é um apelido ou ALIAS para produto na query
            String sql = "SELECT v.id, `date_format('data_venda', '%d/%m/%Y)' AS data_formatada`, c.nome, v.totalvenda, v.pagamento FROM venda AS v INNER JOIN clientes AS c ON (v.cliente_id = c.id)"
                    + " WHERE v.datavenda BETWEEN ? AND ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio.toString());
            stmt.setString(2, data_fim.toString());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();

                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("data_formatada"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("v.observacoes"));

                obj.setCliente(c);

                lista.add(obj);
            }
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
            return null;
        }
    }
}
