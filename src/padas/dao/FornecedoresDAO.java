/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import padas.jdbc.ConexaoBanco;
import padas.model.Clientes;
import padas.model.Fornecedores;

/**
 *
 * @author domin
 */
public class FornecedoresDAO {
   private Connection conn;
   
   public FornecedoresDAO(){
       this.conn = new ConexaoBanco().pegarConexao();  
   }
   
   public void Salvar(Fornecedores obj){
       try {
           //1º Criar o SQL
           String sql = "insert into Fornecedores (nome, morada, telefone, email)"
                   + "values(?,?,?,?)";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getNome());
           stmt.setString(2,obj.getMorada());
           stmt.setString(3,obj.getTelefone());
           stmt.setString(4,obj.getEmail());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao salvar o fornecedores"+e);
       }
   }
   
      public void Editar(Fornecedores obj){
       try {
           //1º Criar o SQL
           String sql = "update Fornecedores set nome=?, morada=?, email=?, telefone=? where fornecedorID=?";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getNome());
           stmt.setString(2,obj.getMorada());
           stmt.setString(3,obj.getTelefone());
           stmt.setString(4,obj.getEmail());
           stmt.setInt(5,obj.getId());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Fornecedor editado com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao editar o fornecedor"+e);
       }
   }
      
      public void Excluir(Clientes obj){
          try {
              String sql = "delete from Fornecedores where fornecedorID=?";
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt(1, obj.getId());
              stmt.execute();
              stmt.close();
              JOptionPane.showMessageDialog(null, "Fornecedor exluido com sucesso!");
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null,"Erro ao excluir o fornecedor"+e);
          }
      }
   
   public Fornecedores BuscarFornecedores(String nome){
       try {
           String sql = "select * from Fornecedores where nome =?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, nome);
           ResultSet rs = stmt.executeQuery();
           Fornecedores obj = new Fornecedores();
           if(rs.next()){
               obj.setId(rs.getInt("fornecedorID"));
               obj.setNome(rs.getString("Nome"));
               obj.setMorada(rs.getString("Morada"));
               obj.setTelefone(rs.getString("Telefone"));
               obj.setEmail(rs.getString("Email"));
           }//Fechamento do preechimento automático
           return obj; //Retornar o objecto Cliente após a busca
           
       } catch (SQLException erro) { //Caso alguma coisa deia errado
           JOptionPane.showMessageDialog(null, "Erro ao buscar o fornecedor"+ erro);
       }
       return null;
    }
   //Método para listar os clientes do Banco
   public List<Fornecedores>Listar(){
       List<Fornecedores> lista = new ArrayList<>();
       try {
           String sql = "Select * from Fornecedores";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Fornecedores obj = new Fornecedores();
               obj.setId(rs.getInt("fornecedorID"));
               obj.setNome(rs.getString("Nome"));
               obj.setMorada(rs.getString("Morada"));
               obj.setTelefone(rs.getString("Telefone"));
               obj.setEmail(rs.getString("Email"));
               lista.add(obj);//A variável lista servirá para adicionar o obj dentro da lista criada.
           }
           return lista; //Retorno do que estiver dentro da lista.
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao criar a lista de for."+e);
       }
       return null;
   }
//      public List<Fornecedores>Filtrar(String nome){
//       List<Fornecedores> lista = new ArrayList<>();
//       try {
//           String sql = "Select * from tb_Fornecedores where nome like ?";
//           PreparedStatement stmt = conn.prepareStatement(sql);
//           stmt.setString(1, nome);
//           ResultSet rs = stmt.executeQuery();
//           
//           while(rs.next()){
//               Fornecedores obj = new Fornecedores();
//               obj.setId(rs.getInt("id"));
//               obj.setNome(rs.getString("Nome"));
//               obj.setNif(rs.getString("Nif"));
//               obj.setEmail(rs.getString("Email"));
//               obj.setTelefone(rs.getString("Telefone"));
//               obj.setTelefone2(rs.getString("Telefone2"));
//               obj.setCodPostal(rs.getString("codPostal"));
//               obj.setProvincia(rs.getString("Provincia"));
//               obj.setNumero(rs.getInt("numero"));
//               obj.setComplemento(rs.getString("Complemento"));
//               obj.setBairro(rs.getString("Bairro"));
//               obj.setCidade(rs.getString("Cidade"));
//               obj.setPais(rs.getString("Pais")); 
//               lista.add(obj);//A variável lista servirá para adicionar o obj dentro da lista criada.
//           }
//           return lista; //Retorno do que estiver dentro da lista.
//       } catch (SQLException e) {
//           JOptionPane.showMessageDialog(null, "Erro ao criar a lista."+e);
//       }
//       return null;
//   }


}

