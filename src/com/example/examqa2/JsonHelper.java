/**
 * 
 */
package com.example.examqa2;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;



import com.google.gson.Gson;

/**
 * @author CodehoppersPC
 *
 */
public class JsonHelper {

	public static void populateHashFromJSON(String js,ArrayList<Quizinfo> hashmap)
	{
		try{
			
		Gson gson = new Gson();
	     
		JSONObject jsonobj;
	      
		jsonobj = new JSONObject(js);
			
		JSONArray array=new JSONArray();
			
		array = (JSONArray) jsonobj.get("Response");
		
			for(int i = 0; i < array.length(); i++)
	        {
	        	jsonobj = (JSONObject)array.get(i);
	        	Quizinfo inf = gson.fromJson(jsonobj.toString(),Quizinfo.class);
			        
	        	hashmap.add(inf);
	        }
		 
		 } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
				
	}
	

}
