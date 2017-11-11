package ar.uba.fi.tdd.rulogic;

import java.util.List;

public abstract class Rules{
public String data;
public boolean isFact;
	public Rules(String query) {
		this.data = query;
	}
   public abstract boolean evaluate(String query,List<Rules> rules);
   public abstract boolean isFact();
   
}
