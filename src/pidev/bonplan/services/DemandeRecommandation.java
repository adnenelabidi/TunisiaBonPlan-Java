/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
 
import javafxapplication1.forum.entities.demande_experience;
 
import pidev.bonplan.utils.MyConnection;

/**
 *
 * @author SLIMEN
 */
public class DemandeRecommandation {
    
      public void ajouterDemande(demande_experience p) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "INSERT INTO demande_experience(nom,descripion,addresse,image,datecreation) VALUES(?,?,?,?,?)"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setString(1, p.getNom());
            st.setString(2, p.getDescripion());
            st.setString(3, p.getAddresse());
            st.setString(4, p.getImage());
            st.setDate(5, new java.sql.Date(Calendar.getInstance().getTime().getTime()));

            st.executeUpdate();
            System.out.println("demande ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
      
          public void modifierDemande(demande_experience p, int id) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "UPDATE  demande_experience set nom=?,descripion=?,addresse=?,image=? where id=?"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setInt(5, id);
            st.setString(1, p.getNom());
            st.setString(2, p.getDescripion());
            st.setString(3,p.getAddresse());
            st.setString(4,p.getImage());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
          
            public void SupprimerDemande(int id) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "DELETE * FROM demande_experience where id=?"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setInt(1, id);
  
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
            
            public List<demande_experience> listerDemandes() {
        List<demande_experience> myList = new ArrayList<demande_experience>();

        try {
            String requete3 = "SELECT * From demande_experience";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                demande_experience p = new demande_experience();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescripion(rs.getString(3));
                p.setImage(rs.getString(5));
                p.setAddresse(rs.getString(4));
                p.setDatecreation(rs.getDate(6));
              
        
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
        public List<demande_experience> RechDemande(String nom) {
        List<demande_experience> myList = new ArrayList<demande_experience>();

        try {
            String requete3 = "SELECT * From demande_experience where nom='"+nom+"'";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                demande_experience p = new demande_experience();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescripion(rs.getString(3));
                p.setImage(rs.getString(5));
                p.setAddresse(rs.getString(4));
                p.setDatecreation(rs.getDate(6));
              
        
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
    
}
