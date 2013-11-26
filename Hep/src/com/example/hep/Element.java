package com.example.hep;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author dubois
 * Décrit un élement de la commande.
 */
public class Element {
	public final int type;
	public final String description;
	
	public int nb;
	
	/**
	 * Créé un élément de commande.
	 * Le nombre d'éléments est fixé à 1.
	 * @param type Type de conso.
	 * @param name Description.
	 */
	public Element(int type, String name){
		this.type = type;
		this.nb = 1;
		this.description = name;
	}
	
	/**
	 * Affiche l'élément de commande dans une vue
	 * @param view Doit être généré par R.layout.command_element
	 */
	public void setView(View view){
		((ImageView) (view.findViewById(R.id.CommandImageViewType))).setImageResource(Carte.IMAGES[type]);
		((TextView) (view.findViewById(R.id.CommandTextViewQuantity))).setText("x "+nb);
		((TextView) (view.findViewById(R.id.CommandTextViewElement))).setText(description);			
	}

	
	/**
	 * Sauvegarde l'élément de commande dans un Bundle.
	 * @return Un Bundle contenant la description de l'élément.
	 */
	public Bundle getSaveBundle() {
		Bundle result = new Bundle();
		
		result.putInt("type", type);
		result.putString("description", description);
		result.putInt("nb", nb);
		
		return result;
	}
	
	/**
	 * Crée l'élément à partir d'une sauvegarde.
	 * @param bundle Le résultat de la command getSaveBundle().
	 */
	public Element(Bundle bundle){
		type = bundle.getInt("type");
		description = bundle.getString("description");
		nb = bundle.getInt("nb");
	}
	
}