package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.uba.fi.tdd.rulogic.Fact;
import ar.uba.fi.tdd.rulogic.FactComposite;
import ar.uba.fi.tdd.rulogic.Rules;

public class KnowledgeBase {
	
	private List<Rules> listRules;
	
	public KnowledgeBase(String nameFile) throws IOException{
		this.listRules = new ArrayList<Rules>();
		this.parserDB(nameFile);
	}
	private void parserDB(String nameFile) throws IOException{
		FileReader input = new FileReader(nameFile);
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;

		while ( (myLine = bufRead.readLine()) != null)
		{    
			String elem = myLine.replaceAll("[\\s+|/.|\t|\n/g]", "");
		    if (this.isFacts(elem)) {
		    	Fact fact = new Fact(elem);
		    	listRules.add(fact);
		    }
		    if (this.isRule(elem)) {
		    	FactComposite rule = new FactComposite(elem);
		    	listRules.add(rule);
		    }
		}
	}
	private boolean isFacts(String query) {
		Pattern p = Pattern.compile("^[^\\(]*\\([^)]*\\)$");
        Matcher matcher = p.matcher(query);
        return matcher.find();
	}
	private boolean isRule(String query) {
		Pattern p = Pattern.compile("^[^\\(]*\\([^)]*\\):-([^\\(]*\\([^)]*\\), *)*([^\\(]*\\([^)]*\\))$");
        Matcher matcher = p.matcher(query);
        return matcher.find();
	}
	public boolean answer(String query) {
		  String q = query.replaceAll("[\\s+|/.|\t|\n/g]", "");
		  boolean hit = false;
		  if (this.isFacts(q)) {
		      for (int i = 0; i < listRules.size(); i++) { 
		    	   hit = ((Rules)listRules.get(i)).evaluate(q,listRules);
		           if (hit == true ) {   
		                return hit;
		           }
		      }
		  }
	      return hit;
	}

}
