/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Player;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melkior
 */
@Stateless
public class PlayerFacade extends AbstractFacade<Player> {
    @PersistenceContext(unitName = "HistoryQuizPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlayerFacade() {
        super(Player.class);
    }
    
    public Player playerForPseudo(String pseudo, Collection<Player> players){
        if(pseudo==null){
            return null;
        }
        em.flush();
        for(Player p : players){
            if(p.getPseudo().equals(pseudo)){
                return find(p.getPlayerId());
            }
        }
        return null;
    }
    
}
