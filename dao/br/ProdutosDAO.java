/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.br;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.br.Fornecedores;
import model.br.Produtos;
import projeto.jdbc.ConexaoBD;

/**
 *
 * @author Felli
 */
public class ProdutosDAO {

    private final Connection con;

    public ProdutosDAO() {
        this.con = new ConexaoBD().getConnection();
    }

    //Cadastrando PRODUTOS
    public void CadastrarProdutos(Produtos obj) {
        try {
            //inserindo na tabela clientes programaticamente
            String sql = "insert into tb_produtos(descricao, preco, qtd_estoque, for_id) values (?,?,?,?)";

            //conectando o banco
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());

            //executando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "PRODUTO CADASTRADO!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ERRO: " + erro);

        }
    }

    //alterando PRODUTOS
    public void alterarProdutos(Produtos obj) {
        try {
            //inserindo na tabela clientes programaticamente
            String sql = "UPDATE tb_produtos SET descricao=?, preco=?, qtd_estoque=?, for_id=? WHERE id=?;";

            //conectando o banco
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());

            //executando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "ATUALIZAÇÃO DO PRODUTO REALIZADA!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ERRO: " + erro);

        }
    }

    //alterando PRODUTOS
    public void excluirProdutos(Produtos obj) {
        try {
            //inserindo na tabela clientes programaticamente
            String sql = "DELETE from tb_produtos WHERE id=?;";

            //conectando o banco
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            //executando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "PRODUTO EXCLUÍDO!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ERRO: " + erro);

        }
    }

    //listando PRODUTOS
    public List<Produtos> listarProdutos() {
        try {
            //criando lista
            List<Produtos> lista = new ArrayList<>();

            //comando sql
            //p é um apelido ou ALIAS para produto na query
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos AS p  INNER JOIN tb_fornecedores AS f on (p.for_id = f.id)";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);

                lista.add(obj);
            }
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
            return null;
        }
    }

    public List<Produtos> listarProdutosNome(String nome) {
        try {
            //criando a lista
            //p é um apelido ou ALIAS para produto na query
            List<Produtos> lista = new ArrayList<>();
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos AS p  INNER JOIN tb_fornecedores AS f on (p.for_id = f.id)"
                    + "WHERE p.descricao LIKE ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);

                lista.add(obj);
            }
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
            return null;
        }
    }

    //consultar por nome na tela de cadastro
    public Produtos consultaNome(String nome) {
        try {
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque, f.nome FROM tb_produtos AS p  INNER JOIN tb_fornecedores AS f on (p.for_id = f.id)"
                    + "WHERE p.descricao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);
            }
            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }

        return null;
    }

    //consultar por ood de produto na tela de vendas
    public Produtos consultaCodigo(int id) {
        try {
            String sql = "SELECT * FROM tb_produtos WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));
            }
            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }

        return null;
    }

//baixa no estoque
    public void baixarEstoque(int id, int qtd_nova) {
        try {
            String sql = "UPDATE tb_produtos SET qtd_estoque = ? WHERE id = ?";
            //conexão banco
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);

            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }

    }

    //atualizando estoque após baixa
    public int retornaEstoqueAtual(int id) {
        try {
            int qtd_estoque = 0;
            String sql = "SELECT qtd_estoque, FROM tb_produtos WHERE id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produtos p = new Produtos();
                qtd_estoque = (rs.getInt("qtd_estoque"));
            }
            return qtd_estoque;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
