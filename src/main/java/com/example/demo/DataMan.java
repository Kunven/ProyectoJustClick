package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;  

public class DataMan {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/miniproyecto";
   static final String USER = "postgres";
   static final String PASS = "123";      
   //Valida si el registro existe
   public Boolean existe(String name){
      
      String query = "select name from miniproyecto.strings WHERE name like '%"+name+"%'";
      List<String> List = new ArrayList<String>();  
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);    
         Statement stmt = conn.createStatement();         
         ResultSet rs = stmt.executeQuery(query);
         
      ) {		      
         while(rs.next()){                        
            List.add(""+rs.getString(1));      
         }
         if(List.size() > 0){
            return true;
         }else{
            return false;
         }         
      } catch (SQLException e) {          
          return false;       
      }     
   }
   //Retorna el registro existente que no se haya pasado del limite, si hay registro con nombres duplicados retorna el ingresado mas recientemente
   public string Consultar(String name) {
      // Open a connection          
      String query = "select * from miniproyecto.strings WHERE name like '%"+name+"%' AND counter<maxvalue";
      string obj = null;      
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);          
         Statement stmt = conn.createStatement();       
         ResultSet rs = stmt.executeQuery(query);
      ) {		               
         while(rs.next()){
            obj = new string();
            //Display values          
            obj.id = rs.getInt(1);
            obj.name = rs.getString(2);
            obj.counter = rs.getInt(3);
            obj.maxvalue = rs.getInt(4);
         }
         return obj;
      } catch (SQLException e) {          
          return obj;                 
      } 
   }
   //Actualizo el contador
   public string Actualizar(String name){
      String query = "UPDATE miniproyecto.strings SET counter = (select counter+1 from miniproyecto.strings where name like '%"+name+"%') WHERE name like '%"+name+"%'";
      string obj = null;
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);          
         Statement stmt = conn.createStatement();                
      ) {		      
         stmt.executeUpdate(query);         
         //retorno el objeto para mostrar informacion del contador al usuario
         obj = Consultar(name);
         return obj;
      } catch (SQLException e) {          
          return obj;
      } 
   }   
   
}
