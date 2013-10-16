package com.example.hep;

/**
 * @author dubois
 * Description d'un élément de la carte.
 */
public class Conso {
	public final String nom;
	public final int type;
	public final int id;
	
	public Conso(int type, String nom, int id) {
		super();
		this.nom = nom;
		this.type = type;
		this.id = id;
	}
	
	public String toString(){
		return nom;
	}
}