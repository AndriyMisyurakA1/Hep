package com.example.hep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author dubois
 * Activité de choix d'une consommation.
 * Affiche les consos disponibles pour le paramètre fourni (EXTRA_TYPE) dans l'Intent.
 */
public class SelectActivity extends Activity implements OnItemClickListener {

	// Constantes les données échangées entre activités
	public static final String EXTRA_DESCRIPTION = "description";
	public static final String EXTRA_TYPE = "type";

	// type tel que récupéré dans l'Intent
	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);

		//On récupère le paramètre correspodant au type de conso
		type = getIntent().getExtras().getInt(EXTRA_TYPE);

		View view = findViewById(R.id.CommandLinearLayout);
		
		//entête
		new Element(type,"").setView(view);

		ArrayAdapter<Conso> adapter = new ArrayAdapter<Conso>(this, 
				android.R.layout.simple_list_item_1, MainActivity.carte.lists[type]);

		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		//retour du choix de l'utilisateur
		Intent intent = this.getIntent();
		intent.putExtra(EXTRA_DESCRIPTION, MainActivity.carte.lists[type].get(pos).toString());
		setResult(RESULT_OK, intent);
		finish();
	}
}
