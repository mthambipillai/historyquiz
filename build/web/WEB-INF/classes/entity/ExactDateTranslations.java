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
@Table(name = "exact_date_translations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExactDateTranslations.findAll", query = "SELECT e FROM ExactDateTranslations e"),
    @NamedQuery(name = "ExactDateTranslations.findByExactDateId", query = "SELECT e FROM ExactDateTranslations e WHERE e.exactDateTranslationsPK.exactDateId = :exactDateId"),
    @NamedQuery(name = "ExactDateTranslations.findByLanguageCode", query = "SELECT e FROM ExactDateTranslations e WHERE e.exactDateTranslationsPK.languageCode = :languageCode"),
    @NamedQuery(name = "ExactDateTranslations.findByIsDefault", query = "SELECT e FROM ExactDateTranslations e WHERE e.isDefault = :isDefault"),
    @NamedQuery(name = "ExactDateTranslations.findByEvent", query = "SELECT e FROM ExactDateTranslations e WHERE e.event = :event")})
public class ExactDateTranslations extends QuestionTranslations implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExactDateTranslationsPK exactDateTranslationsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_default")
    private boolean isDefault;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "event")
    private String event;
    @JoinColumn(name = "exact_date_id", referencedColumnName = "exact_date_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ExactDate exactDate;

    public ExactDateTranslations() {
    }

    public ExactDateTranslations(ExactDateTranslationsPK exactDateTranslationsPK) {
        this.exactDateTranslationsPK = exactDateTranslationsPK;
    }

    public ExactDateTranslations(ExactDateTranslationsPK exactDateTranslationsPK, boolean isDefault, String event) {
        this.exactDateTranslationsPK = exactDateTranslationsPK;
        this.isDefault = isDefault;
        this.event = event;
    }

    public ExactDateTranslations(int exactDateId, String languageCode) {
        this.exactDateTranslationsPK = new ExactDateTranslationsPK(exactDateId, languageCode);
    }

    public ExactDateTranslationsPK getExactDateTranslationsPK() {
        return exactDateTranslationsPK;
    }

    public void setExactDateTranslationsPK(ExactDateTranslationsPK exactDateTranslationsPK) {
        this.exactDateTranslationsPK = exactDateTranslationsPK;
    }
    
    @Override
    public String getLanguageCode(){
        return exactDateTranslationsPK.getLanguageCode();
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

    public ExactDate getExactDate() {
        return exactDate;
    }

    public void setExactDate(ExactDate exactDate) {
        this.exactDate = exactDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exactDateTranslationsPK != null ? exactDateTranslationsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExactDateTranslations)) {
            return false;
        }
        ExactDateTranslations other = (ExactDateTranslations) object;
        if ((this.exactDateTranslationsPK == null && other.exactDateTranslationsPK != null) || (this.exactDateTranslationsPK != null && !this.exactDateTranslationsPK.equals(other.exactDateTranslationsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ExactDateTranslations[ exactDateTranslationsPK=" + exactDateTranslationsPK + " ]";
    }
    
}
