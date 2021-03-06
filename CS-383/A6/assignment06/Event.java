package assignment06;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;



public class Event{
	private static String[] variables;
	private static String[] evidence;
	private static boolean[] values;
	private static Gson gson;
	private static JsonParser parser;
	private static JsonArray jsonNodes;
	
	
	public Event(String s) {
		gson = new Gson();
		parser = new JsonParser();
		jsonNodes = parser.parse(s).getAsJsonArray(); // size = 1
		queryFromQueryString(s);

	}

	/**
	 *
	 * @param s a JSON encoded Bayes Net
	 * @return a map, from node names to nodes
	 */
	public static void queryFromQueryString(String s) {


		//System.out.println(jsonNodes.size()); // whole string as array with size 1

		//        for(JsonElement e: jsonNodes){ // each element as arrays, with size 3
		//        	for(JsonElement c : e.getAsJsonArray()){ // what we want to convert to string. size : 2,0,0
		//        		for(JsonElement d : c.getAsJsonArray()){ // I want to store as c.getAsJsonArray()
		//        			System.out.println(d.getAsString()); //strings
		//        		}
		//        	}
		//
		//        }


		variables = gson.fromJson(jsonNodes.get(0).getAsJsonArray().get(0).getAsJsonArray(), String[].class);
		evidence = gson.fromJson(jsonNodes.get(0).getAsJsonArray().get(1).getAsJsonArray(), String[].class);
		values = gson.fromJson(jsonNodes.get(0).getAsJsonArray().get(2).getAsJsonArray(), boolean[].class);
	}

	// Getters

	public String[] getVariable(){
		return variables;
	}

	public String[] getEvidence(){
		return evidence;
	}

	public boolean[] getValues(){
		return values;
	}

	public String toString(){
		return jsonNodes.toString().replace("[[[", "[[").replace("]]]", "]]");
//		StringBuilder s = new StringBuilder();
//		s.append("[[");
//		for(String q : query){
//			s.append("\"" + q + "\",");
//		}
//		s.append("],[");
//		for(String e : evidence){
//			s.append("\"" + e + "\",");
//		}
//		s.append("],[");
//
//		for(boolean v : values){
//			s.append(v+ ",");
//		}
//		s.append("]]");
//		return s.toString().replace(",]", "]");
	}

}