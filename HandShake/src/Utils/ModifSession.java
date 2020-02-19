/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author steph
 */
public class ModifSession {
    
        private static ModifSession instance;
    
    private int id;
    private String cible;
    private String libelle;
    private int quantite;
    private String categorie;
    private int montant;
    
    private ModifSession()
    {
        id = getId();
        cible = getCible();
        libelle = getLibelle();
        quantite = getQuantite();
        categorie = getCategorie();
        montant = getMontant();
                
    }
    
    private ModifSession(int idd,String cib,String lib,int quant,String cat)
    {
        this.id = idd;
        this.cible = cib;
        this.libelle = lib;
        this.quantite = quant;
        this.categorie = cat;
    }
    
    private ModifSession(int idd,String cib,int mont)
    {
        this.id = idd;
        this.cible = cib;
        this.montant = mont;
    }
    
    public static ModifSession getInstance() {
        if(instance == null) {
            instance = new ModifSession();
        }
        return instance;
    }

    public static ModifSession getInstace(int id,String cib,String lib,int quant,String cat) {
        
            instance = new ModifSession(id,cib, lib,quant,cat);
        
        return instance;
    }
    
    public static ModifSession getInstace(int id,String cib,int mont) {
        
            instance = new ModifSession(id,cib, mont);
        
        return instance;
    }

    public int getId() {
        return id;
    }

    public String getCible() {
        return cible;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getMontant() {
        return montant;
    }

    @Override
    public String toString() {
        return "ModifSession{" + "cible=" + cible + ", libelle=" + libelle + ", quantite=" + quantite + ", categorie=" + categorie + ", montant=" + montant + '}';
    }
    
    
    
}
