/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.entites;

/**
 *
 * @author Abdelkader
 */
public class Categoris {
    int id;
    private  Categoris idcategoriemere;
    private  String libelle;
    private  String discription;
    private  String image;

    public Categoris() {
    }

    public Categoris(int id, Categoris idcategoriemere, String libelle, String discription, String image) {
        this.id = id;
        this.idcategoriemere = idcategoriemere;
        this.libelle = libelle;
        this.discription = discription;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Categoris{" + "id=" + id + ", idcategoriemere=" + idcategoriemere + ", libelle=" + libelle + ", discription=" + discription + ", image=" + image + '}';
    }

    public Categoris getIdcategoriemere() {
        return idcategoriemere;
    }

    public void setIdcategoriemere(Categoris idcategoriemere) {
        this.idcategoriemere = idcategoriemere;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
    
    
    
    
    
    
    
}
