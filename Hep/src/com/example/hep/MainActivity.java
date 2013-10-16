package com.example.hep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * @author dubois
 * Activité principale de l'application
 * Affiche la commande actuelle, ainsi que les icônes des types de commande.
 * Appuyer sur un élément de la commande en ajoute un de même type.
 */
public class MainActivity extends Activity implements OnItemClickListener {

	//constante (arbitraire) pour le type de travail demandé
	private static final int SELECT = 1;

	// La commande en cours
	private Command command;
	
	public static Carte carte = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		// Chargement de la carte. le try .. catch sert à gérer d'éventuelles erreurs dans le ficheir xml
		if (carte == null) {
			try {
				carte = new Carte(getResources().getXml(R.xml.carte));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		setContentView(R.layout.activity_main);

		command = new Command();

		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(command.getAdapter(this));
		listView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onImageClick(View view){
		int type = -1;
		switch (view.getId()){ //quel type conso ajouter ?
		case R.id.imageView1 :
			type = 0;
			break;
		case R.id.imageView2 :
			type = 1;
			break;
		case R.id.imageView3 :
			type = 2;
			break;
		case R.id.imageView4 :
			type = 3;
			break;
		case R.id.imageView5 :
			type = 4;
			break;
		}

		//Paramétrage et lancement de l'activité de choix.
		Intent intent = new Intent(this, SelectActivity.class);
		intent.putExtra(SelectActivity.EXTRA_TYPE, type);
		startActivityForResult(intent, SELECT);
	}

	// On récupère les informations correspondant au choix.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);	

		if (requestCode == SELECT && resultCode == RESULT_OK){// On vérifie qu'il s'agit bien de la réponse à notre question 
			int type = data.getExtras().getInt(SelectActivity.EXTRA_TYPE);
			String description = data.getExtras().getString(SelectActivity.EXTRA_DESCRIPTION);
			command.add(type, description ); //ajout à la commande
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		command.add(pos);		
	}
}
