/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Player;
import entity.Score;
import entity.Tag;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melkior
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NewScoresManager {
    @PersistenceContext(unitName = "HistoryQuizPU")
    private EntityManager em;
    @Resource
    private SessionContext context;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addNewScores(HashMap<Tag,Integer> tagsScores, Player player, Date date){
        em.flush();
        ArrayList<Score> scores = new ArrayList<Score>();
        
        Score s=null;
        int mostRecentScore=0;
        
        HashMap<Tag,Score> playerBestScores = new HashMap<Tag,Score>();
        for(Score score : player.getScoreCollection()){
            playerBestScores.put(score.getTag(), score);
        }
        
        for(Tag tag : tagsScores.keySet()){
            mostRecentScore = tagsScores.get(tag);
            if(playerBestScores.containsKey(tag)){ //the player already has a score for this tag
                if(mostRecentScore>playerBestScores.get(tag).getBestScore()){ //add the score if it beats the previous best score
                    s = playerBestScores.get(tag);
                    s.setBestScore(mostRecentScore);
                    s.setDate(date);
                    em.merge(s);
                }
            }else{ //the player has no score for this tag, so add the score anyway
                s = new Score(tag.getTagId(),player.getPlayerId());
                s.setTag(tag);
                s.setPlayer(player);
                s.setBestScore(mostRecentScore);
                s.setDate(date);
                em.persist(s);
                em.merge(tag);
                em.merge(player);
                em.flush();
                em.refresh(s);
                em.refresh(em.find(Tag.class, tag.getTagId()));
                em.refresh(em.find(Player.class, player.getPlayerId()));
                em.flush();
                em.clear();
                em.getEntityManagerFactory().getCache().evictAll();
            }
        }
        em.flush();
    }
}
