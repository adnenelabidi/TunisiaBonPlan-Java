/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.bonplan.entites;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author X
 */
public class Reclamation {
    int id,ref_compte,ref_bonplan,ref_deal,ref_mise,numero_mobile,priorite;
    Date date_creation,date_traitement,date_disponibilite;
    String description,objet,status,screenshot,nom,prenom,email;

    public Reclamation() {
    }

    public Reclamation(int id, int ref_compte, int ref_bonplan, int ref_deal, int ref_mise, int numero_mobile, int priorite, Date date_creation, Date date_traitement, Date date_disponibilite, String description, String objet, String status, String screenshot, String nom, String prenom, String email) {
        this.id = id;
        this.ref_compte = ref_compte;
        this.ref_bonplan = ref_bonplan;
        this.ref_deal = ref_deal;
        this.ref_mise = ref_mise;
        this.numero_mobile = numero_mobile;
        this.priorite = priorite;
        this.date_creation = date_creation;
        this.date_traitement = date_traitement;
        this.date_disponibilite = date_disponibilite;
        this.description = description;
        this.objet = objet;
        this.status = status;
        this.screenshot = screenshot;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRef_compte() {
        return ref_compte;
    }

    public void setRef_compte(int ref_compte) {
        this.ref_compte = ref_compte;
    }

    public int getRef_bonplan() {
        return ref_bonplan;
    }

    public void setRef_bonplan(int ref_bonplan) {
        this.ref_bonplan = ref_bonplan;
    }

    public int getRef_deal() {
        return ref_deal;
    }

    public void setRef_deal(int ref_deal) {
        this.ref_deal = ref_deal;
    }

    public int getRef_mise() {
        return ref_mise;
    }

    public void setRef_mise(int ref_mise) {
        this.ref_mise = ref_mise;
    }

    public int getNumero_mobile() {
        return numero_mobile;
    }

    public void setNumero_mobile(int numero_mobile) {
        this.numero_mobile = numero_mobile;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_traitement() {
        return date_traitement;
    }

    public void setDate_traitement(Date date_traitement) {
        this.date_traitement = date_traitement;
    }

    public Date getDate_disponibilite() {
        return date_disponibilite;
    }

    public void setDate_disponibilite(Date date_disponibilite) {
        this.date_disponibilite = date_disponibilite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    
}
