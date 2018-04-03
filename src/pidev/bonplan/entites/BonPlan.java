/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.entites;

import java.sql.Time;

/**
 *
 * @author Abdelkader
 */
public class BonPlan {
    
   int id;
private  User ref_compte;
private  Categoris  refcategorie;
private  int rating;
private  String  libelle;
private int status;
private String description;
private String image;
private String addresse;
private double prix;
private Time overture;
private Time fermeture;
private int num_tel_local;
private String email;
private int dislike;
private int latitude;
private int longitude;
private int jaime;
private String couverture;

    public BonPlan(int id, User ref_compte, Categoris refcategorie, int rating, String libelle, int status, String description, String image, String addresse, double prix, Time overture, Time fermeture, int num_tel_local, String email, int dislike, int latitude, int longitude, int jaime, String couverture) {
        this.id = id;
        this.ref_compte = ref_compte;
        this.refcategorie = refcategorie;
        this.rating = rating;
        this.libelle = libelle;
        this.status = status;
        this.description = description;
        this.image = image;
        this.addresse = addresse;
        this.prix = prix;
        this.overture = overture;
        this.fermeture = fermeture;
        this.num_tel_local = num_tel_local;
        this.email = email;
        this.dislike = dislike;
        this.latitude = latitude;
        this.longitude = longitude;
        this.jaime = jaime;
        this.couverture = couverture;
    }

    @Override
    public String toString() {
        return "BonPlan{" + "id=" + id + ", ref_compte=" + ref_compte + ", refcategorie=" + refcategorie + ", rating=" + rating + ", libelle=" + libelle + ", status=" + status + ", description=" + description + ", image=" + image + ", addresse=" + addresse + ", prix=" + prix + ", overture=" + overture + ", fermeture=" + fermeture + ", num_tel_local=" + num_tel_local + ", email=" + email + ", dislike=" + dislike + ", latitude=" + latitude + ", longitude=" + longitude + ", jaime=" + jaime + ", couverture=" + couverture + '}';
    }

    public BonPlan(int id, int rating, String libelle, int status, String description, String image, String addresse, double prix, Time overture, Time fermeture, int num_tel_local, String email, int dislike, int latitude, int longitude, int jaime, String couverture) {
        this.id = id;
        this.rating = rating;
        this.libelle = libelle;
        this.status = status;
        this.description = description;
        this.image = image;
        this.addresse = addresse;
        this.prix = prix;
        this.overture = overture;
        this.fermeture = fermeture;
        this.num_tel_local = num_tel_local;
        this.email = email;
        this.dislike = dislike;
        this.latitude = latitude;
        this.longitude = longitude;
        this.jaime = jaime;
        this.couverture = couverture;
    }


    public BonPlan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getRef_compte() {
        return ref_compte;
    }

    public void setRef_compte(User ref_compte) {
        this.ref_compte = ref_compte;
    }

    public Categoris getRefcategorie() {
        return refcategorie;
    }

    public void setRefcategorie(Categoris refcategorie) {
        this.refcategorie = refcategorie;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Time getOverture() {
        return overture;
    }

    public void setOverture(Time overture) {
        this.overture = overture;
    }

    public Time getFermeture() {
        return fermeture;
    }

    public void setFermeture(Time fermeture) {
        this.fermeture = fermeture;
    }

    public int getNum_tel_local() {
        return num_tel_local;
    }

    public void setNum_tel_local(int num_tel_local) {
        this.num_tel_local = num_tel_local;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getJaime() {
        return jaime;
    }

    public void setJaime(int jaime) {
        this.jaime = jaime;
    }

    public String getCouverture() {
        return couverture;
    }

    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    
    
    
    
}
