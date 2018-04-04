/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.bonplan.entites.offre_experience;
 
import pidev.bonplan.utils.MyConnection;

/**
 *
 * @author SLIMEN
 */
public class PartageExperience {

    public void ajouterExperience(offre_experience p) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "INSERT INTO offre_experience(nom,description,url_image,addrese,datecreation,rating,climatisation,wifi,snackbar,parking,piscine,familiale,paiementparcarte,balcon,visites,fumer,reservations) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setString(1, p.getNom());
            st.setString(2, p.getDescription());
            st.setString(3, p.getUrl_image());
            st.setString(4, p.getAddrese());
            st.setDate(5, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            st.setInt(6, p.getRating());
            st.setBoolean(7, p.getClimatisation());
            st.setBoolean(8, p.getWifi());
            st.setBoolean(9, p.getSnackbar());
            st.setBoolean(10, p.getParking());
            st.setBoolean(11, p.getPiscine());
            st.setBoolean(12, p.getFamiliale());
            st.setBoolean(13, p.getPaiementparcarte());
            st.setBoolean(14, p.getBalcon());
            st.setBoolean(15, p.getVisites());
            st.setBoolean(16, p.getFumer());
            st.setBoolean(17, p.getReservations());

            st.executeUpdate();
            System.out.println("experience ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierExperience(offre_experience p, int id) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "UPDATE  offre_experience set nom=?,description=?,url_image=?,addrese=?,rating=?,climatisation=?,wifi=?,snackbar=?,parking=?,piscine=?,familiale=?,paiementparcarte=?,balcon=?,visites=?,fumer=?,reservations=? where id=?"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setString(1, p.getNom());
            st.setString(2, p.getDescription());
            st.setString(3, p.getUrl_image());
            st.setString(4, p.getAddrese());
            st.setInt(5, p.getRating());
            st.setBoolean(6, p.getClimatisation());
            st.setBoolean(7, p.getWifi());
            st.setBoolean(8, p.getSnackbar());
            st.setBoolean(9, p.getParking());
            st.setBoolean(10, p.getPiscine());
            st.setBoolean(11, p.getFamiliale());
            st.setBoolean(12, p.getPaiementparcarte());
            st.setBoolean(13, p.getBalcon());
            st.setBoolean(14, p.getVisites());
            st.setBoolean(15, p.getFumer());
            st.setBoolean(16, p.getReservations());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void SupprimerExperience(int id) {

        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "DELETE * FROM offre_experience where id=?"; //MAJUSCULE NON OBLIGATOIRE 
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<offre_experience> listerPartageExp() {
        List<offre_experience> myList = new ArrayList<offre_experience>();

        try {
            String requete3 = "SELECT * From offre_experience";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                offre_experience p = new offre_experience();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setUrl_image(rs.getString(5));
                p.setAddrese(rs.getString(6));
                p.setDatecreation(rs.getDate(9));
                p.setRating(rs.getInt(10));
                p.setRating(rs.getInt(10));
                p.setClimatisation(rs.getBoolean(14));
                p.setWifi(rs.getBoolean(15));
                p.setSnackbar(rs.getBoolean(16));
                p.setParking(rs.getBoolean(17));
                p.setPiscine(rs.getBoolean(18));
                p.setFamiliale(rs.getBoolean(19));
                p.setPaiementparcarte(rs.getBoolean(20));
                p.setBalcon(rs.getBoolean(21));
                p.setVisites(rs.getBoolean(22));
                p.setFumer(rs.getBoolean(23));
                p.setReservations(rs.getBoolean(24));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
    
     public List<offre_experience> RechExp(String nom) {
        List<offre_experience> myList = new ArrayList<offre_experience>();

        try {
            String requete3 = "SELECT * From offre_experience where nom='"+nom+"'";
            Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(requete3);
            while (rs.next()) {
                offre_experience p = new offre_experience();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setUrl_image(rs.getString(5));
                p.setAddrese(rs.getString(6));
                p.setDatecreation(rs.getDate(9));
                p.setRating(rs.getInt(10));
                p.setRating(rs.getInt(10));
                p.setClimatisation(rs.getBoolean(14));
                p.setWifi(rs.getBoolean(15));
                p.setSnackbar(rs.getBoolean(16));
                p.setParking(rs.getBoolean(17));
                p.setPiscine(rs.getBoolean(18));
                p.setFamiliale(rs.getBoolean(19));
                p.setPaiementparcarte(rs.getBoolean(20));
                p.setBalcon(rs.getBoolean(21));
                p.setVisites(rs.getBoolean(22));
                p.setFumer(rs.getBoolean(23));
                p.setReservations(rs.getBoolean(24));
                myList.add(p);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;

    }
     
     public List<String> ListRegion()
     {
             
    List<String> list = new ArrayList<String>();
    try {
        String sqlStationName = " select nom from region ";
         Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);

        while (rs.next()) {

            list.add(rs.getString("nom"));

        }

        rs.close();
        st3.close();
         

    } catch (SQLException ex) {
        System.err.println("ERR" + ex);
    }
    return list;
     }
     public List<String> ListCategorie()
     {
             
    List<String> list = new ArrayList<String>();
    try {
        String sqlStationName = " select libelle from categorie ";
         Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);

        while (rs.next()) {

            list.add(rs.getString("libelle"));

        }

        rs.close();
        st3.close();
         

    } catch (SQLException ex) {
        System.err.println("ERR" + ex);
    }
    return list;
     }
     
     public int GetItemId(String nomtable,String comparethis,String comparable){
       
         int i=0;
         try {
        String sqlStationName = " select id from "+nomtable+" where "+comparethis+"='"+comparable+"'";
         Statement st3 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st3.executeQuery(sqlStationName);
            while (rs.next()) {

            i=rs.getInt("id");

        }

        rs.close();
        st3.close();
         

    } catch (SQLException ex) {
        System.err.println("ERR" + ex);
    }
         return i;
     }
     }

