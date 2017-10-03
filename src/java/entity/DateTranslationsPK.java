/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Melkior
 */
@Embeddable
public class DateTranslationsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_id")
    private int dateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "language_code")
    private String languageCode;

    public DateTranslationsPK() {
    }

    public DateTranslationsPK(int dateId, String languageCode) {
        this.dateId = dateId;
        this.languageCode = languageCode;
    }

    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) dateId;
        hash += (languageCode != null ? languageCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DateTranslationsPK)) {
            return false;
        }
        DateTranslationsPK other = (DateTranslationsPK) object;
        if (this.dateId != other.dateId) {
            return false;
        }
        if ((this.languageCode == null && other.languageCode != null) || (this.languageCode != null && !this.languageCode.equals(other.languageCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DateTranslationsPK[ dateId=" + dateId + ", languageCode=" + languageCode + " ]";
    }
    
}
