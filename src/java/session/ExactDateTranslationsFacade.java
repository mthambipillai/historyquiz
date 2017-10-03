/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ExactDateTranslations;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melkior
 */
@Stateless
public class ExactDateTranslationsFacade extends AbstractFacade<ExactDateTranslations> {
    @PersistenceContext(unitName = "HistoryQuizPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExactDateTranslationsFacade() {
        super(ExactDateTranslations.class);
    }
    
}
