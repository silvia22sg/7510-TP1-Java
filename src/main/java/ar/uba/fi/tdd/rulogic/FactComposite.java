package ar.uba.fi.tdd.rulogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactComposite extends Rules{

		private Fact head;
		private List<Rules> listFact = new ArrayList<Rules>();
		private Map<String, String> mapVar = new HashMap<String, String>();
		
		public FactComposite(String query) {
			super(query);
	    	String[] elements = query.split(":-", 2);
	    	this.head = new Fact(elements[0]);
	    	this.listFact = parserValues(elements[1]);
		}
    
	    public List<Rules> parserValues(String values) {
	    	String result = values.replaceAll("\\),", "\\) ");
	    	String[] facts = result.split(" ");
	    	listFact.clear();
	    	for(int i=0;i<facts.length;i++) {
	    		Fact elem = new Fact(facts[i]);
	    		listFact.add(elem);
	    	}
	    	
	    	return listFact;
	    }
	    public boolean isFact() {
	    	return this.isFact;
	    }
	    public boolean evaluateFact(Fact f,List<Rules> listRules) {
			 boolean hit = false;
		     String element = f.replaceAllValuesFacts(mapVar);
		      for (int j = 0; j < listRules.size(); j++) {
		    	if (((Rules)listRules.get(j)).isFact()) {
		    	   hit = ((Rules)listRules.get(j)).evaluate(element,listRules);
		           if (hit == true ) {   
		                break;
		           }
		    	}
		      }
		     return hit;
	    }
	    public boolean evaluate(String query,List<Rules> listRules) {	
	    	boolean result = false;
	    	Fact q = new Fact(query);
	    	if (head.compareName(q)) {
	    		result = true;
	    		mapVar.clear();
	    		this.mapVar = head.replaceValue(q);
	    		for (int i = 0; i < listFact.size(); i++) { 
	    			     if (evaluateFact((Fact)listFact.get(i),listRules)==false) {
		    		    	  result=false;
		    		    	  break;
	    			     }
	    		}
	    	}
	    	return result;
	    }
}
