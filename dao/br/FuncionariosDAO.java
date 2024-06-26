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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.br.Funcionarios;
import projeto.jdbc.ConexaoBD;
import view.armazena.br.Frmmenu;
import view.armazena.br.FrmLogin;

/**
 *
 * @author Felli
 */
public class FuncionariosDAO {

    private final Connection con;

    public FuncionariosDAO() {
        this.con = new ConexaoBD().getConnection();
    }

    //Cadastrando FUNCIONARIO
    public void cadastrarFuncionarios(Funcionarios obj) {
        try {

            //inserindo na tabela clientes programaticamente
            String sql = "INSERT INTO tb_funcionarios (nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            //conectando o banco
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivelAcesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());

            //executando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "CADASTRO REALIZADO!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO: " + erro);

        }
    }

    //ALTERANDO FUNCIONARIO
    public void alterarFuncionrio(Funcionarios obj) {
        try {

            //inserindo na tabela clientes programaticamente
            String sql = "UPDATE tb_funcionarios SET nome=?, rg=?, cpf=?, email=?, senha=?, cargo=?, nivel_acesso=?, telefone=?, celular=?, cep=?,"
                    + "endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=? WHERE id=?";

            //conectando o banco
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivelAcesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());

            stmt.setInt(17, obj.getId());

            //executando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "ALTERAÇÃO REALIZADA!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO: " + erro);

        }
    }

    //Excluindo FUNCIONARIO
    public void excluirFuncionario(Funcionarios obj) {
        try {

            //inserindo na tabela clientes programaticamente
            String sql = "DELETE FROM tb_funcionarios WHERE id = ?;";

            //conectando o banco
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            //executando SQL
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "EXCLUSÃO REALIZADA!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ERRO: " + erro);

        }
    }

    //listando funcionarios
    public List<Funcionarios> listarFuncionarios() {
        try {
            List<Funcionarios> lista = new ArrayList<>();

            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }
        return null;
    }

    //consulta por nome
    public Funcionarios consultaNome(String nome) {
        try {
            String sql = "select * from tb_funcionarios where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Funcionarios obj = new Funcionarios();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

            }
            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado, confira os dados na consulta de Funcionários.");
            return null;
        }
    }

    //consulta por cpf
    public Funcionarios consultaCPF(String cpf) {
        try {
            String sql = "select * from tb_funcionarios where cpf = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();
            Funcionarios obj = new Funcionarios();

            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

            }
            return obj;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado, confira os dados na consulta de Funcionários.");
            return null;
        }
    }

    //buscando funcionario por nome e id
    public List<Funcionarios> buscaFuncionarioNome(String nome) {
        try {
            List<Funcionarios> lista = new ArrayList<>();

            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }
        return null;
    }

    public List<Funcionarios> buscaFuncionariosId(String id) {
        try {
            List<Funcionarios> lista = new ArrayList<>();

            String sql = "select * from tb_funcionarios where id like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }
        return null;

    }

    //Login
    public void logar(String email, String senha) {
        try {
            String sql = "SELECT * FROM tb_funcionarios WHERE email=? AND senha=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                //login
                //admin
                if (rs.getString("nivel_acesso").equals("Administrador")) {
                    JOptionPane.showMessageDialog(null, "VOCÊ ESTÁ NO SISTEMA");
                    Frmmenu tela = new Frmmenu();
                    tela.usuarioLogado = rs.getString("nome");
                    tela.setVisible(true);
                } //user padrão
                else if (rs.getString("nivel_acesso").equals("Padrão")) {
                    JOptionPane.showMessageDialog(null, "VOCÊ ESTÁ NO SISTEMA");
                    Frmmenu tela = new Frmmenu();
                    tela.usuarioLogado = rs.getString("nome");
                    
                    tela.menu_posicao.setEnabled(false);
                    tela.menu_controlevendas.setEnabled(false);
                    
                    tela.setVisible(true);
                }
            } else {
                //erro de login
                JOptionPane.showMessageDialog(null, "SENHA OU E-MAIL INCORRETOS, TENTE NOVAMENTE OU CONTATE O SUPORTE.");
                new FrmLogin().setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO:" + e);
        }
    }
}
