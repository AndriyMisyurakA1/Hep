package com.example.hep;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author dubois
 * D�crit un �lement de la commande.
 */
public class Element {
	public final int type;
	public final String description;
	
	public int nb;
	
	/**
	 * Cr�� un �l�ment de commande.
	 * Le nombre d'�l�ments est fix� � 1.
	 * @param type Type de conso.
	 * @param name Description.
	 */
	public Element(int type, String name){
		this.type = type;
		this.nb = 1;
		this.description = name;
	}
	
	/**
	 * Affiche l'�l�ment de commande dans une vue
	 * @param view Doit �tre g�n�r� par R.layout.command_element
	 */
	public void setView(View view){
		((ImageView) (view.findViewById(R.id.CommandImageViewType))).setImageResource(Carte.IMAGES[type]);
		((TextView) (view.findViewById(R.id.CommandTextViewQuantity))).setText("x "+nb);
		((TextView) (view.findViewById(R.id.CommandTextViewElement))).setText(description);			
	}
}