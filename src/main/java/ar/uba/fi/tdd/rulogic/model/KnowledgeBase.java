package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KnowledgeBase {
	
	public List listFacts = new ArrayList<String>();
	public List listRule = new ArrayList<String>();
	
	public KnowledgeBase(String file) {
		
	}
	public boolean isFacts(String query) {
		Pattern p = Pattern.compile("/^[^\\(]*\\([^)]*\\)$/");
        Matcher matcher = p.matcher(query);
        return matcher.find();
	}
	public boolean isRule(String query) {
		Pattern p = Pattern.compile("/^[^\\(]*\\([^)]*\\):-([^\\(]*\\([^)]*\\), *)*([^\\(]*\\([^)]*\\))$/");
        Matcher matcher = p.matcher(query);
        return matcher.find();
	}
	public boolean evaluateFacts(String elem, List<String> listFacts) {
	  boolean hit = false;
	      for (int i = 0; i < listFacts.size(); i++) { 
	         if (listFacts.get(i) == elem) {
	           hit = true;
	           break;
	         }
	      }
	  return hit;
	}
	public Map<String, String> generateMap(String[] listVariables,String[] listValues){
		  //Generate hash-map relating variables with value, ex: (X Y) and (pepe juan) -> {X pepe, Y juan}
		  Map<String, String> mapVariables = new HashMap<String, String>();
		  if (listVariables.length == listValues.length ){
		    for (int i = 0; i < listVariables.length; i++){
		    	mapVariables.put(listVariables[i], listValues[i]);
	        }
		  }
		  return mapVariables;
		 }
	public boolean evaluateRule(String query,List<String> listRules,List<String> listFacts){
		  //Get the Query name, ex: varon(maria) -> varon
		  String xlist[] = null;//query.replaceAll("^/\)|\(/", " ").split(" ");
		  String nameQuery = xlist[0];
		  //Search the list of Rules by Query Name, ex: hijo -> (hija(X,Y):-mujer(X),padre(Y,X) hijo(X,Y):-varon(X),padre(Y,X))
		  boolean valid = false;
		  String yElem = "";
		  String newFacts[] = new String[2];
		  for (int i = 0; i < listRules.size(); i++) { 
		    yElem = listRules.get(i);
		   // newFacts = (listRules[i].replace("/\)|\(/g"," ")).split(" ");
		    String nameRule = newFacts[0];
		    if (nameRule == nameQuery){
		      valid = true;
		      break;
		    }
		  }
		  //ex: hijo(pepe,juan) ->[pepe juan]
		  String valueList[] = xlist[1].split(",");
		  //ex: hija(X,Y) -> [X Y]
		  String varList[] = newFacts[1].split(",");
		  if (valid == true) {
		    //In assignValue enter as parameters, ex: 1-> varon(X),padre(Y,X), 2-> {X pepe, Y juan}, 3-> lista de Facts
		    Map map = this.generateMap(varList,valueList);
		    valid = this.assignValue((yElem.split(":-"))[1],map,listFacts);
		  }
		  return valid;
	}
	public boolean assignValue(String text,Map<String, String> mapVariables,List listFacts){
		  //Replace the variable by the value, ex: varon(X),padre(Y,X) -> varon(pepe),padre(juan,pepe)
		  
		for (Map.Entry<String, String> entry : mapVariables.entrySet()) {
			//text= text.replace(new RegE(entry.getKey(), 'g'),entry.getValue());
		}
		  //Generate vector, ej: varon(pepe),padre(juan,pepe) -> [varon(pepe) padre(juan,pepe)]
		  String listFactsQuery[] = (text.replaceAll("",") ")).split(" ");
		  //Check if the list of Query Facts are in the Facts list of the database, if one fails is false.
		  boolean valid = true;
		  for (int i = 0; i < listFactsQuery.length; i++){
		    if (!this.evaluateFacts(listFactsQuery[i],listFacts)){
		        valid = false;
		        break;
		    } 
		  }
		  return valid;
		 }
		 
	public boolean answer(String query) {
		String q = query.replaceAll("/\n|\t/g", "");
		boolean resultado = true;
		  if (this.isFacts(q)){
			  
		    resultado = this.evaluateFacts(q,this.listFacts);
		    if (!resultado){
		  //    resultado = this.evaluateRule(q,this.listRule,this.listFacts);
		    }
		  }
		  return resultado;
	}

}
