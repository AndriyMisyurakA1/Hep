package com.example.hep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

/**
 * @author dubois
 * Description d'un �l�ment d'une commande.
 */
/**
 * @author dubois
 *
 */
/**
 * @author dubois
 *
 */
/**
 * @author dubois
 *
 */
/**
 * @author dubois
 *
 */
public class Command {
	
	
	/**
	 * Liste des �l�ments constitutifs de la commande, par ordre d'ajout
	 */
	private List<Element> list;
	
	/**
	 * Acc�s rapide � un �l�ment existant par son nom
	 */
	private Map<String,Element> map;
	
	
	private BaseAdapter adapter;
	
	/**
	 * G�n�re une commande vide.
	 */
	Command(){
		list = new ArrayList<Element>();
		map = new HashMap<String,Element>();
		adapter = null;
	}
	
	/**
	 * Ajoute un �l�ment � la commande.
	 * Si un �l�ment identique existe, on incr�mente juste le nombre.
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
	 * Ajoute un �l�ment identique � celui en position pos
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
	 * G�n�re un adaptateur pour l'affichage de la commande.
	 * Si un adaptateur exite d�j�, il sera retourn�.
	 * 
	 * @param context Activit� utilisant l'adaptateur.
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
	
	/**
	 * Sauvegarde la commande dans un Bundle.
	 * Pour recharger, appeler loadBundle(Bundle bundle) sur une instance de Command.
	 * @return La sauvegarde dans un Bundle.
	 */
	public Bundle getSaveBundle() {
		
		Bundle result = new Bundle();
		
		ArrayList<Bundle> save = new ArrayList<Bundle>();
		for(Element element : list){
			save.add(element.getSaveBundle());
		}
		
		result.putParcelableArrayList("list",save);
		return result;
	}
	
	
	/**
	 * Charge une commande sauvegard�e dans un bundle.
	 * Pour sauvegarder, utiliser la m�thode getSaveBundle().
	 * @param bundle La sauvegarde � restaurer.
	 */
	public void loadBundle(Bundle bundle) {
		list.clear();
		map.clear();
		
		ArrayList<Bundle> l = bundle.getParcelableArrayList("list");
		for(Bundle b : l){
			Element element = new Element(b);
			list.add(element);
			map.put(element.toString(), element);
		}
		
		if (adapter != null){
			adapter.notifyDataSetChanged();
		}
	}
	
	


}
