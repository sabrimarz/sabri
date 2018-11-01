/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adfitness.entities;

import java.time.LocalDate;

/**
 *
 * @author SabriMarz
 */
public class fiche {
    
    private int id ; 
    private int idclient ;
    private LocalDate naissance ;
    private int diabete ,hypo , hyper ,perte,saignement,fumeur,pratiquant,predre,strength ;

    public fiche() {
        this.diabete=0 ;
        this.hypo=0;
        this.hyper=0 ;
        this.perte=0;
        this.saignement=0 ;
        this.fumeur=0;
        this.pratiquant=0 ;
        this.predre=0;
        this.strength=0;
    }

 

    public fiche(int id, int idclient, LocalDate naissance, int diabete, int hypo, int hyper, int perte, int saignement, int fumeur, int pratiquant, int predre, int strength) {
        this.id = id;
        this.idclient = idclient;
        this.naissance = naissance;
        this.diabete = diabete;
        this.hypo = hypo;
        this.hyper = hyper;
        this.perte = perte;
        this.saignement = saignement;
        this.fumeur = fumeur;
        this.pratiquant = pratiquant;
        this.predre = predre;
        this.strength = strength;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    public void setDiabete(int diabete) {
        this.diabete = diabete;
    }

    public void setHypo(int hypo) {
        this.hypo = hypo;
    }

    public void setHyper(int hyper) {
        this.hyper = hyper;
    }

    public void setPerte(int perte) {
        this.perte = perte;
    }

    public void setSaignement(int saignement) {
        this.saignement = saignement;
    }

    public void setFumeur(int fumeur) {
        this.fumeur = fumeur;
    }

    public void setPratiquant(int pratiquant) {
        this.pratiquant = pratiquant;
    }

    public void setPredre(int predre) {
        this.predre = predre;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getId() {
        return id;
    }

    public int getIdclient() {
        return idclient;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public int getDiabete() {
        return diabete;
    }

    public int getHypo() {
        return hypo;
    }

    public int getHyper() {
        return hyper;
    }

    public int getPerte() {
        return perte;
    }

    public int getSaignement() {
        return saignement;
    }

    public int getFumeur() {
        return fumeur;
    }

    public int getPratiquant() {
        return pratiquant;
    }

    public int getPredre() {
        return predre;
    }

    public int getStrength() {
        return strength;
    }
    
    
}
