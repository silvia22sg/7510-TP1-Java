package ar.uba.fi.tdd.rulogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fact extends Rules{
	private String name;
	private List<String> listValues = new ArrayList<String>();

	public Fact(String query) {
		super(query);
		super.isFact = true;
		try {
			String[] elements = query.split("[\\(]"); 	
	    	this.name = elements[0];
	    	this.listValues = this.parserValues(elements[1].replaceAll("\\)", ""));
		}catch(Exception e) {
		}
	}
	//Carga una lista de valores ingresados por parametro
    public List<String> parserValues(String query) {
    	List<String> listaVal = new ArrayList<String>();
    	String[] elements = query.split("\\,");
    	for(int i=0;i<elements.length;i++) {
    		listaVal.add(elements[i]);
    	}
    	return listaVal;
    }
    public boolean compareName(Fact query) {
    	return this.name.equalsIgnoreCase(query.getName());
    }
    //Carga en un map los valores de las variables con el correspondiente ingresado
    //por parametro
    public Map<String, String> replaceValue(Fact query) {
    	  Map<String, String> mapVariables = new HashMap<String, String>();
		  if (listValues.size() == query.getListValues().size() ){
		    for (int i = 0; i < listValues.size(); i++){
		    	mapVariables.put(listValues.get(i), query.getListValues().get(i));
	        }
		  }
		  return mapVariables;
    }
    //Reemplaza las variables con los valores ingresados
    public String replaceAllValuesFacts( Map<String, String> mapVariables) {
    String result = this.data;
		for (Map.Entry<String, String> entry : mapVariables.entrySet()) {
			result = result.replaceAll(entry.getKey(),entry.getValue());
		}
	  return result;
    }
    public String getName() {
    	return this.name;
    }
    public List<String> getListValues() {
    	return this.listValues;
    }
    public boolean isFact() {
    	return this.isFact;
    }
    public boolean evaluate(String query,List<Rules> r) {
	   return super.data.equals(query);
    }
}
