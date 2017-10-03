/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import entity.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author Melkior
 */
public class Training {
    private ArrayList<Question> questionsList;
    private String language;
    private HashMap<Question, Double> questionsScores;
    private Question currentQuestion;
    private Random r = new Random();
    private String currentAnswerResult="";
    
    public Training(ArrayList<Question> list, String language){
        questionsList = new ArrayList<Question>(list);
        this.language = language;
        questionsScores = new HashMap<Question, Double>();
        for(Question q: questionsList){
            questionsScores.put(q,1.0);
        }
    }
    
    public String askNextQuestion()
    {
        HashMap<Question,Double> probs = computeProbs();
        HashMap<Double,Question> intervalQuestions = new HashMap<Double,Question>();
        ArrayList<Double> intervalPoints = new ArrayList<Double>();
        intervalPoints.add(0.0);
        int j=0;
        double p;
        Question q;
        for(Entry<Question,Double> e : probs.entrySet()){
        	q = e.getKey();
        	p = e.getValue();
        	intervalPoints.add(intervalPoints.get(j)+p);
        	intervalQuestions.put(intervalPoints.get(j),q);
        	j++;
        }
        double n = r.nextDouble();
        for(int i=0;i<intervalPoints.size()-1;i++){
            if(n>=intervalPoints.get(i) && n<=intervalPoints.get(i+1)){
                currentQuestion = intervalQuestions.get(intervalPoints.get(i));
                return currentQuestion.getEvent();
            }
        }
        return null;
    }
    
    /*
    use this function in case the user answers via a single entry (one textbox with text separators '-' '.' for example)
    */
    public String answer(String entry)
    {
        if(currentQuestion==null)
        {
                throw new NullPointerException("A question must be asked first");
        }
        //always level 0 (hardest) in this mode
        if(currentQuestion.parseAndCorrect(0,entry))
        {
            //increment score
            questionsScores.put(currentQuestion, questionsScores.get(currentQuestion)+1);
            currentAnswerResult = "Correct. ("+currentQuestion.getAnswerText()+")";
            return currentAnswerResult;
        }
        else
        {
            currentAnswerResult = "Incorrect : "+currentQuestion.getAnswerText();
            return currentAnswerResult;
        }
    }
    
    private HashMap<Question,Double> computeProbs(){
        HashMap<Question,Double> invertedScores = new HashMap<Question,Double>();
        double sum=0;
        double val=0;
        for(Question q: questionsScores.keySet()){
        	val = 1.0/questionsScores.get(q);
            invertedScores.put(q,val);
            sum+=val;
        }
        
        HashMap<Question,Double> probs = new HashMap<Question,Double>();
        double p;
        
        for(Question q: invertedScores.keySet()){
            p = invertedScores.get(q) / sum;
            probs.put(q,p);
        }
        return probs;
    }
    public String getCurrentPictureFileName()
    {
        return currentQuestion.getImage();
    }
    public String getCurrentEvent(){
        if(currentQuestion==null){
            throw new NullPointerException("no current question yet");
        }else{
            return currentQuestion.getEvent(language);
        }
    }
    public String getCurrentAnswerResult(){
        return currentAnswerResult;
    }
    public String getCurrentQuestionType(){
        String result = "";
        String[] types = currentQuestion.getAnswersTypes();
        for(int i=0;i<types.length-1;i++){
            result+=types[i];
        }
        result+=types[types.length-1];
        return result;
    }
    public String[] getAnswersTypes()
    {
        return currentQuestion.getAnswersTypes();
    }
}
