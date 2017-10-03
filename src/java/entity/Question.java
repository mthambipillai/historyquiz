/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 *
 * @author Melkior
 */
public abstract class Question {
    private String language="";
    public void setLanguage(String l){
        this.language = l;
    }
    public String getEvent(){
        return getEvent(this.language);
    }
    public abstract String getImage();

    public abstract boolean containsTag(Tag tag);

    public abstract Collection<Tag> getTagCollection();
    public abstract boolean correct(int level,String...entries);
    public abstract String[] getAnswersTypes();
    //Level 0 is the hardest
    public abstract boolean parseAndCorrect(int level, String entry);
    public abstract String getAnswerText();
    
    public abstract Collection<QuestionTranslations> getQuestionsTranslationsCollection();
    
    public String getEvent(String language) throws NoSuchElementException{
        for(QuestionTranslations qt: getQuestionsTranslationsCollection()){
            if(qt.getLanguageCode().equals(language)){
                return qt.getEvent();
            }
        }
        for(QuestionTranslations qt: getQuestionsTranslationsCollection()){
            if(qt.getIsDefault()){
                return qt.getEvent();
            }
        }
        throw new NoSuchElementException();
    }
}
