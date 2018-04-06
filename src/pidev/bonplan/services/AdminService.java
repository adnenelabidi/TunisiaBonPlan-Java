/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pidev.bonplan.utils.MyConnection;

/**
 *
 * @author Abdelkader
 */
public class AdminService {
   
    
    public int get_Number_User() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(roles) FROM `fosuser` ";
            PreparedStatement  preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
         
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message_Number = rs.getInt(1);
            }
            return Message_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Message_Number;
    }

    public int get_Number_Membre() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(roles) FROM `fosuser` where roles= 'a:1:{i:0;s:11:\"ROLE_MEMBRE\";}'";
        
            PreparedStatement  preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message_Number = rs.getInt(1);
            }
            return Message_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Message_Number;
    }

    public int get_Number_partenaire() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(roles) FROM `fosuser` where roles= 'a:1:{i:0;s:17:\"ROLE_PROPRIETAIRE\";}'";
           
            PreparedStatement  preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Message_Number = rs.getInt(1);
            }
            return Message_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Message_Number;
    }

    public int get_Number_Cadeaux() {
        ResultSet rs = null;
        int Product_Number = 989898;
        try {
            String req = "SELECT COUNT(*) FROM `cadeaux`";
  
         PreparedStatement  preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product_Number = rs.getInt(1);
            }
            return Product_Number;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Product_Number;
    }
    
}
