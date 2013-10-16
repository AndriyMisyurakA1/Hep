package com.example.hep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;

/**
 * @author dubois
 * Liste des consommations possibles, triées par catégories.
 * Cette classe se charge de l'analyse du fichier xml décrivant la carte.
 * Elle héberge aussi les constantes relatives aux catégories.
 */
public class Carte {

	public final static int[] IMAGES ={
		R.drawable.biere,
		R.drawable.cocktail,
		R.drawable.hot,
		R.drawable.soft,
		R.drawable.vin
	};

	public final static int BIERE = 0;
	public final static int COCKTAIL = 1;
	public final static int HOT = 2;
	public final static int SOFT = 3;
	public final static int VIN = 4;

	public final List<Conso>[] lists;


	public Carte(XmlResourceParser xpp) throws XmlPullParserException, IOException{	
		
		//instanciation et initialisation du tableau
		lists = new List[IMAGES.length];
		for (int i = 0; i < lists.length;++i) lists[i] = new ArrayList<Conso>();

		//initialisations diverses
		String text = null;
		int current = 0;
		int id = 0;

		//début de l'analyse du xml
		xpp.next();
		int eventType = xpp.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT){// tant que pas fini...
			if(eventType == XmlPullParser.START_TAG){
				if ("cat".equals(xpp.getName())){// début d'une nouvelle catégorie
					current = xpp.getAttributeIntValue(null, "type", -1);
				}
			} else if(eventType == XmlPullParser.END_TAG) {
				if ("conso".equals(xpp.getName())){// fin d'une balise <Conso>
					Conso conso = new Conso(current,text,id++); // on crée l'élément
					lists[current].add(conso); // on l'ajoute dans la catégorie courante
				}
			} else if(eventType == XmlPullParser.TEXT) {
				text = xpp.getText();
			}
			eventType = xpp.next();
		}
	}

}
