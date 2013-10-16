package com.example.hep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

/**
 * @author dubois
 * Description d'un élément d'une commande.
 */
public class Command {
	
	
	/**
	 * Liste des éléments constitutifs de la commande, par ordre d'ajout
	 */
	private List<Element> list;
	
	/**
	 * Accès rapide à un élément existant par son nom
	 */
	private Map<String,Element> map;
	
	
	private BaseAdapter adapter;
	
	/**
	 * Génère une commande vide.
	 */
	Command(){
		list = new ArrayList<Element>();
		map = new HashMap<String,Element>();
		adapter = null;
	}
	
	/**
	 * Ajoute un élément à la commande.
	 * Si un élément identique existe, on incrémente juste le nombre.
	 * 
	 * @param type Type de conso.
	 * @param name Description.
	 */
	public void add(int type, String name){
		if (map.containsKey(name)){
			Element element = map.get(name);
			element.nb++;
		} else {			
			Element element = new Element(type,name);
			list.add(element);
			map.put(name, element);
		}
		if (adapter != null){
			adapter.notifyDataSetChanged();
		}
	}
	
	
	/**
	 * Ajoute un élément identique à celui en position pos
	 * @param pos Position dans la liste
	 */
	public void add(int pos) {
		Element element = list.get(pos);
		element.nb++;
		
		if (adapter != null){
			adapter.notifyDataSetChanged();
		}
	}
	
	
	/**
	 * Génère un adaptateur pour l'affichage de la commande.
	 * Si un adaptateur exite déjà, il sera retourné.
	 * 
	 * @param context Activité utilisant l'adaptateur.
	 * @return
	 */
	public ListAdapter getAdapter(final Activity context){
		if (adapter == null){
			adapter = new BaseAdapter(){

				@Override
				public int getCount() {
					return list.size();
				}

				@Override
				public Object getItem(int position) {
					return list.get(position);
				}

				@Override
				public long getItemId(int position) {
					return position;
				}

				@Override
				public View getView(int position, View convertView,
						ViewGroup parent) {
					if (convertView == null) 
					    convertView = context.getLayoutInflater().inflate(R.layout.command_element,parent,false);
					((Element) getItem(position)).setView(convertView);
					return convertView;
				}
			};
		}
		return adapter;
	}


}
