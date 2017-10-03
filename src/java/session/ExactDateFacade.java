/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ExactDate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melkior
 */
@Stateless
public class ExactDateFacade extends AbstractFacade<ExactDate> {
    @PersistenceContext(unitName = "HistoryQuizPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExactDateFacade() {
        super(ExactDate.class);
    }
    
}
