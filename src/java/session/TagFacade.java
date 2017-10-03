/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Question;
import entity.Tag;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melkior
 */
@Stateless
public class TagFacade extends AbstractFacade<Tag> {
    @PersistenceContext(unitName = "HistoryQuizPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagFacade() {
        super(Tag.class);
    }
    
    public Tag tagForName(String tagName){
        for(Tag tag : findAll()){
            if(tag.getName().equals(tagName)){
                return tag;
            }
        }
        return null;
    }
    
    public ArrayList<Question> questionsForTag(int tagId){
        ArrayList<Question> result = new ArrayList<Question>();
        if(tagId!=0){
            Tag tag = find(tagId);
            result.addAll(tag.getDateCollection());
            result.addAll(tag.getPeriodCollection());
            result.addAll(tag.getExactDateCollection());
            result.addAll(tag.getNameCollection());
        }
        return result;
    }
    
}
