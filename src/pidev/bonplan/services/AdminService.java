/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import pidev.bonplan.entites.User;
import pidev.bonplan.utils.MyConnection;

/**
 *
 * @author Abdelkader
 */
public class AdminService {
   
    
    public int get_Number_Reclamation() {
        ResultSet rs = null;
        int Message_Number = 989898;
        try {
            String req = "SELECT COUNT(*) FROM `reclamation` ";
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
 public int get_Number_Comment() {
        ResultSet rs = null;
        int Number_CommentUser = 0;
        try {
            String req = "SELECT COUNT(*) FROM `comment`";
  
         PreparedStatement  preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
   
         rs = preparedStatement.executeQuery();
            
           
            while (rs.next()) {
                Number_CommentUser = rs.getInt(1);
            }
            return Number_CommentUser;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Number_CommentUser;
    }
 public int get_Number_BonPlan() {
        ResultSet rs = null;
        int Number_BonPlanUser = 0;
        try {
            String req = "SELECT COUNT(*) FROM `bon_plan`";
  
         PreparedStatement  preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
 
            rs = preparedStatement.executeQuery();
            
        
            while (rs.next()) {
                Number_BonPlanUser = rs.getInt(1);
            }
            return Number_BonPlanUser;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Number_BonPlanUser;
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
    public int get_Number_CommentUser(int user,Date DateDebut1,Date DateFin1) {
        ResultSet rs = null;
        int Number_CommentUser = 0;
        try {
            String req = "SELECT COUNT(*) FROM `comment` WHERE `author_id`=? and created_at BETWEEN ? AND ? ";
  
         PreparedStatement  preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
          preparedStatement.setInt(1, user );  
          preparedStatement.setDate(2, DateDebut1 );  
          preparedStatement.setDate(3, DateFin1 );  
         rs = preparedStatement.executeQuery();
            
           
            while (rs.next()) {
                Number_CommentUser = rs.getInt(1);
            }
            return Number_CommentUser;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Number_CommentUser;
    }
    public int get_Number_BonPlanUser(int user) {
        ResultSet rs = null;
        int Number_BonPlanUser = 0;
        try {
            String req = "SELECT COUNT(*) FROM `bon_plan` WHERE `ref_compte`=?";
  
         PreparedStatement  preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
             preparedStatement.setInt(1, user);
            rs = preparedStatement.executeQuery();
            
        
            while (rs.next()) {
                Number_BonPlanUser = rs.getInt(1);
            }
            return Number_BonPlanUser;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Number_BonPlanUser;
    }
    
    public int get_Number_OffreExperience(int user,Date DateDebut1,Date DateFin1) {
        ResultSet rs = null;
        int Number_OffreExperience = 0;
        try {
          String req = "SELECT COUNT(*) FROM `offre_experience` WHERE `ref_compte`=? and datecreation BETWEEN ? AND ? ";
  
         PreparedStatement  preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(req);
          preparedStatement.setInt(1, user );  
          preparedStatement.setDate(2, DateDebut1 );  
          preparedStatement.setDate(3, DateFin1 );  
         rs = preparedStatement.executeQuery();
            
           
            while (rs.next()) {
                Number_OffreExperience = rs.getInt(1);
            }
            return Number_OffreExperience;
        } catch (Exception e) {
            System.out.println("Error on DB connection");
            System.out.println(e.getMessage());
        }
        return Number_OffreExperience;
    }
    
}
