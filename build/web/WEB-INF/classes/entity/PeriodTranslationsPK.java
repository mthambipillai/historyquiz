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
public class PeriodTranslationsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "period_id")
    private int periodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "language_code")
    private String languageCode;

    public PeriodTranslationsPK() {
    }

    public PeriodTranslationsPK(int periodId, String languageCode) {
        this.periodId = periodId;
        this.languageCode = languageCode;
    }

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
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
        hash += (int) periodId;
        hash += (languageCode != null ? languageCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodTranslationsPK)) {
            return false;
        }
        PeriodTranslationsPK other = (PeriodTranslationsPK) object;
        if (this.periodId != other.periodId) {
            return false;
        }
        if ((this.languageCode == null && other.languageCode != null) || (this.languageCode != null && !this.languageCode.equals(other.languageCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PeriodTranslationsPK[ periodId=" + periodId + ", languageCode=" + languageCode + " ]";
    }
    
}
