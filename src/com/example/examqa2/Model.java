package com.example.examqa2;

import java.util.ArrayList;


public class Model {


	//Json to be paresd and data to be recovered out of array indexes
	// public String jsi = "{\"result\":[{\"questionNumber\":1,\"question\":\"what is ur name?\",\"answer\":\"zaheer\"},{\"questionNumber\":2,\"question\":\"what is ur name?\",\"answer\":\"ahmad\"}]}";

	public static Model md=null;
	public ArrayList<Quizinfo> questionArrayList=null;
	
	private Model(){

		questionArrayList = new ArrayList<Quizinfo>();

		/*JsonHelper helper=new JsonHelper();
		helper.populateHashFromJSON(this.jsi, hashmap);*/ 

	}

	public static Model getInstance(){
		if(md==null){
			md = new Model();
			return md;
		}
		return md;
	}
	
	public Quizinfo getQuestionInfo(int currentQuestionNumber)
	{
		if(this.questionArrayList.size() > currentQuestionNumber)
			return this.questionArrayList.get(currentQuestionNumber);
		return null;
	}

}
