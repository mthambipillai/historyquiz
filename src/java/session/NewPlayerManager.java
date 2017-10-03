/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Groups;
import entity.Player;
import java.util.ArrayList;
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
public class NewPlayerManager {
    @PersistenceContext(unitName = "HistoryQuizPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Player addPlayer(String pseudo, String password, String email, Groups g){
        try{
            Player player = new Player();
            player.setPseudo(pseudo);
            player.setPassword(password);
            player.setEmail(email);
            ArrayList<Groups> playerGroups = new ArrayList<Groups>();
            playerGroups.add(g);
            player.setGroupsCollection(playerGroups);

            em.persist(player);
            em.flush();
            em.clear();
            em.getEntityManagerFactory().getCache().evictAll();
            return player;
        }catch(Exception e){
            context.setRollbackOnly();
            return null;
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void changePassword(Player player,String newPassord){
        try{
            player.setPassword(newPassord);
            em.merge(player);
            em.flush();
            em.clear();
            em.getEntityManagerFactory().getCache().evictAll();
        }catch(Exception e){
            context.setRollbackOnly();
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void changePseudo(Player player,String newPseudo){
        try{
            player.setPseudo(newPseudo);
            em.merge(player);
            em.flush();
            em.clear();
            em.getEntityManagerFactory().getCache().evictAll();
        }catch(Exception e){
            context.setRollbackOnly();
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void changeEmail(Player player,String newEmail){
        try{
            player.setEmail(newEmail);
            em.merge(player);
            em.flush();
            em.clear();
            em.getEntityManagerFactory().getCache().evictAll();
        }catch(Exception e){
            context.setRollbackOnly();
        }
    }
}