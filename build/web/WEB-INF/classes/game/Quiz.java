/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import entity.Question;
import entity.Tag;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author Melkior
 */
public class Quiz
{

    private ArrayList<Question> questionsList;
    private HashMap<Tag,ArrayList<Question>> tagsQuestions;
    private HashMap<Tag,Integer> tagsScores;
    private Question currentQuestion;
    private int initSize;
    private int score=0;
    private int nbAskedQuestions=0;
    private int level; //0 is the hardest, it allows no mistakes
    private String currentAnswerResult="";
    private String language;

    public Quiz(ArrayList<Question> list,HashMap<Tag,ArrayList<Question>> tagsQuestions, int level, String language)
    {
        questionsList = new ArrayList<Question>(list);
        this.tagsQuestions = new HashMap<Tag,ArrayList<Question>>(tagsQuestions);
        tagsScores = new HashMap<Tag,Integer>();

        for(Tag tag : tagsQuestions.keySet()){
            tagsScores.put(tag, 0);
        }

        initSize=questionsList.size();
        this.level = level;
        score=0;
        nbAskedQuestions=0;
        this.language = language;
    }

    public void restart(ArrayList<Question> list,HashMap<Tag,ArrayList<Question>> tagsQuestions, int level)
    {
        questionsList = new ArrayList<Question>(list);
        this.tagsQuestions = new HashMap<Tag,ArrayList<Question>>(tagsQuestions);

        for(Tag tag : tagsQuestions.keySet()){
            tagsScores.put(tag, 0);
        }
        score = 0;
        nbAskedQuestions=0;
        initSize=questionsList.size();
        this.level = level;
    }
    public int getCurrentSize()
    {
        return questionsList.size();
    }
    public String askNextQuestion()
    {
        if(questionsList.isEmpty())
        {
                throw new NoSuchElementException("No more questions in the quiz list");
        }
        nbAskedQuestions++;
        int i= new Random().nextInt(questionsList.size());
        currentQuestion = questionsList.get(i);
        questionsList.remove(i);
        return currentQuestion.getEvent();
    }
    public String getCurrentEvent(){
        if(currentQuestion==null){
            throw new NullPointerException("no current question yet");
        }else{
            return currentQuestion.getEvent(language);
        }
    }
    /*
    use this function in case the user answers via multiple entries (textboxes for example)
    */
    public String answer(String...entries)
    {
        if(currentQuestion==null)
        {
                throw new NullPointerException("A question must be asked first");
        }

        if(currentQuestion.correct(level,entries))
        {
            updateTagsScores(currentQuestion);
            score++;
            currentAnswerResult = "Correct. ("+currentQuestion.getAnswerText()+")";
            return currentAnswerResult;
        }
        else
        {
            currentAnswerResult = "Incorrect : "+currentQuestion.getAnswerText();
            return currentAnswerResult;
        }
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

        if(currentQuestion.parseAndCorrect(level,entry))
        {
            updateTagsScores(currentQuestion);
            score++;
            currentAnswerResult = "Correct. ("+currentQuestion.getAnswerText()+")";
            return currentAnswerResult;
        }
        else
        {
            currentAnswerResult = "Incorrect : "+currentQuestion.getAnswerText();
            return currentAnswerResult;
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
    public String getScore()
    {
        return score + " / " + initSize;
    }
    public String getNbQuestions(){
        return nbAskedQuestions+" / "+ initSize;
    }
    public String[] getAnswersTypes()
    {
        return currentQuestion.getAnswersTypes();
    }
    public static ArrayList<Tag> getInitAll()
    {
        ArrayList<Tag> all = new ArrayList<Tag>();
        return all;
    }
    public String getCurrentPictureFileName()
    {
        return currentQuestion.getImage();
    }


    private void updateTagsScores(Question currentQuestion){
        for(Tag tag : tagsQuestions.keySet()){
            if(tagsQuestions.get(tag).contains(currentQuestion)){
                increaseTagScore(tag);
            }
        }
    }
    private void increaseTagScore(Tag tag){
        tagsScores.put(tag, tagsScores.get(tag)+1);
    }

    private String getTagScore(Tag tag){
        return tag.getName()+" : "+tagsScores.get(tag)+"/"+tagsQuestions.get(tag).size();
    }
    public String getTagsScoresString(){
        String result="";
        for(Tag tag: tagsScores.keySet()){
            result+=getTagScore(tag)+"<br/>";
        }
        return result;
    }
    public HashMap<Tag,Integer> getTagsScores(){
        return tagsScores;
    }

    public void setLanguage(String newLanguage){
        language = newLanguage;
    }
}

