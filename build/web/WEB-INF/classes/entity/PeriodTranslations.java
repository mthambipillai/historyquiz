/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Melkior
 */
@Entity
@Table(name = "period_translations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodTranslations.findAll", query = "SELECT p FROM PeriodTranslations p"),
    @NamedQuery(name = "PeriodTranslations.findByPeriodId", query = "SELECT p FROM PeriodTranslations p WHERE p.periodTranslationsPK.periodId = :periodId"),
    @NamedQuery(name = "PeriodTranslations.findByLanguageCode", query = "SELECT p FROM PeriodTranslations p WHERE p.periodTranslationsPK.languageCode = :languageCode"),
    @NamedQuery(name = "PeriodTranslations.findByIsDefault", query = "SELECT p FROM PeriodTranslations p WHERE p.isDefault = :isDefault"),
    @NamedQuery(name = "PeriodTranslations.findByEvent", query = "SELECT p FROM PeriodTranslations p WHERE p.event = :event")})
public class PeriodTranslations extends QuestionTranslations implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeriodTranslationsPK periodTranslationsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_default")
    private boolean isDefault;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "event")
    private String event;
    @JoinColumn(name = "period_id", referencedColumnName = "period_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Period period;

    public PeriodTranslations() {
    }

    public PeriodTranslations(PeriodTranslationsPK periodTranslationsPK) {
        this.periodTranslationsPK = periodTranslationsPK;
    }

    public PeriodTranslations(PeriodTranslationsPK periodTranslationsPK, boolean isDefault, String event) {
        this.periodTranslationsPK = periodTranslationsPK;
        this.isDefault = isDefault;
        this.event = event;
    }

    public PeriodTranslations(int periodId, String languageCode) {
        this.periodTranslationsPK = new PeriodTranslationsPK(periodId, languageCode);
    }

    public PeriodTranslationsPK getPeriodTranslationsPK() {
        return periodTranslationsPK;
    }

    public void setPeriodTranslationsPK(PeriodTranslationsPK periodTranslationsPK) {
        this.periodTranslationsPK = periodTranslationsPK;
    }
    
    @Override
    public String getLanguageCode(){
        return periodTranslationsPK.getLanguageCode();
    }

    @Override
    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periodTranslationsPK != null ? periodTranslationsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodTranslations)) {
            return false;
        }
        PeriodTranslations other = (PeriodTranslations) object;
        if ((this.periodTranslationsPK == null && other.periodTranslationsPK != null) || (this.periodTranslationsPK != null && !this.periodTranslationsPK.equals(other.periodTranslationsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PeriodTranslations[ periodTranslationsPK=" + periodTranslationsPK + " ]";
    }
    
}
