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
import padas.model.Categorias;

/**
 *
 * @author domin
 */
public class CategoriasDAO {
    //Método construtor
    private Connection conn;
   
   public CategoriasDAO(){
       this.conn = new ConexaoBanco().pegarConexao();  
   }
   
   public void Salvar(Categorias obj){
       try {
           //1º Criar o SQL
           String sql = "insert into categorias (nome)"
                   + "values(?)";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getNome());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao salvar a categoria"+e);
       }
   }
   
      public void Editar(Categorias obj){
       try {
           //1º Criar o SQL
           String sql = "update Categorias set nome=? where CategoriaID=?";
           //2ºPreparar a conexão SQL para se conectar com o Banco
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,obj.getNome());
           stmt.setInt(2,obj.getId());
           //3ºExecutar 
           stmt.execute();
           //4ºFechar conexão
           stmt.close();
           JOptionPane.showMessageDialog(null, "Categoria editado com sucesso!");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao editar o categoria"+e);
       }
   }
      
      public void Excluir(Categorias obj){
          try {
              String sql = "delete from Categorias where CategoriaID=?";
              PreparedStatement stmt = conn.prepareStatement(sql);
              stmt.setInt(1, obj.getId());
              stmt.execute();
              stmt.close();
              JOptionPane.showMessageDialog(null, "Categoria exluido com sucesso!");
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null,"Erro ao excluir o categoria"+e);
          }
      }
   
   public Categorias BuscarCliente(String nome){
       try {
           String sql = "select * from Categorias where nome =?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, nome);
           ResultSet rs = stmt.executeQuery();
           Categorias obj = new Categorias();
           if(rs.next()){
               obj.setId(rs.getInt("CategoriaID"));
               obj.setNome(rs.getString("Nome"));
           }//Fechamento do preechimento automático
           return obj; //Retornar o objecto Cliente após a busca
           
       } catch (SQLException erro) { //Caso alguma coisa deia errado
           JOptionPane.showMessageDialog(null, "Erro ao buscar o categoria"+ erro);
       }
       return null;
    }
      
   //Método para listar os clientes do Banco
   public List<Categorias>Listar(){
       List<Categorias> lista = new ArrayList<>();
       try {
           String sql = "Select * from Categorias";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               Categorias obj = new Categorias();
               obj.setId(rs.getInt("CategoriaID"));
               obj.setNome(rs.getString("Nome"));
               lista.add(obj);//A variável lista servirá para adicionar o obj dentro da lista criada.
           }
           return lista; //Retorno do que estiver dentro da lista.
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro ao criar a lista."+e);
       }
       return null;
   }


}

