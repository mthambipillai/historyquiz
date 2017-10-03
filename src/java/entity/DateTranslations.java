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
@Table(name = "date_translations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DateTranslations.findAll", query = "SELECT d FROM DateTranslations d"),
    @NamedQuery(name = "DateTranslations.findByDateId", query = "SELECT d FROM DateTranslations d WHERE d.dateTranslationsPK.dateId = :dateId"),
    @NamedQuery(name = "DateTranslations.findByLanguageCode", query = "SELECT d FROM DateTranslations d WHERE d.dateTranslationsPK.languageCode = :languageCode"),
    @NamedQuery(name = "DateTranslations.findByIsDefault", query = "SELECT d FROM DateTranslations d WHERE d.isDefault = :isDefault"),
    @NamedQuery(name = "DateTranslations.findByEvent", query = "SELECT d FROM DateTranslations d WHERE d.event = :event")})
public class DateTranslations extends QuestionTranslations implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DateTranslationsPK dateTranslationsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_default")
    private boolean isDefault;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "event")
    private String event;
    @JoinColumn(name = "date_id", referencedColumnName = "date_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Date date;

    public DateTranslations() {
    }

    public DateTranslations(DateTranslationsPK dateTranslationsPK) {
        this.dateTranslationsPK = dateTranslationsPK;
    }

    public DateTranslations(DateTranslationsPK dateTranslationsPK, boolean isDefault, String event) {
        this.dateTranslationsPK = dateTranslationsPK;
        this.isDefault = isDefault;
        this.event = event;
    }

    public DateTranslations(int dateId, String languageCode) {
        this.dateTranslationsPK = new DateTranslationsPK(dateId, languageCode);
    }

    public DateTranslationsPK getDateTranslationsPK() {
        return dateTranslationsPK;
    }

    public void setDateTranslationsPK(DateTranslationsPK dateTranslationsPK) {
        this.dateTranslationsPK = dateTranslationsPK;
    }
    
    @Override
    public String getLanguageCode(){
        return dateTranslationsPK.getLanguageCode();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dateTranslationsPK != null ? dateTranslationsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DateTranslations)) {
            return false;
        }
        DateTranslations other = (DateTranslations) object;
        if ((this.dateTranslationsPK == null && other.dateTranslationsPK != null) || (this.dateTranslationsPK != null && !this.dateTranslationsPK.equals(other.dateTranslationsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DateTranslations[ dateTranslationsPK=" + dateTranslationsPK + " ]";
    }
    
}
