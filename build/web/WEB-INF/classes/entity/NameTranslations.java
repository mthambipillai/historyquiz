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
@Table(name = "name_translations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NameTranslations.findAll", query = "SELECT n FROM NameTranslations n"),
    @NamedQuery(name = "NameTranslations.findByNameId", query = "SELECT n FROM NameTranslations n WHERE n.nameTranslationsPK.nameId = :nameId"),
    @NamedQuery(name = "NameTranslations.findByLanguageCode", query = "SELECT n FROM NameTranslations n WHERE n.nameTranslationsPK.languageCode = :languageCode"),
    @NamedQuery(name = "NameTranslations.findByIsDefault", query = "SELECT n FROM NameTranslations n WHERE n.isDefault = :isDefault"),
    @NamedQuery(name = "NameTranslations.findByEvent", query = "SELECT n FROM NameTranslations n WHERE n.event = :event")})
public class NameTranslations extends QuestionTranslations implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NameTranslationsPK nameTranslationsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_default")
    private boolean isDefault;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "event")
    private String event;
    @JoinColumn(name = "name_id", referencedColumnName = "name_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Name name;

    public NameTranslations() {
    }

    public NameTranslations(NameTranslationsPK nameTranslationsPK) {
        this.nameTranslationsPK = nameTranslationsPK;
    }

    public NameTranslations(NameTranslationsPK nameTranslationsPK, boolean isDefault, String event) {
        this.nameTranslationsPK = nameTranslationsPK;
        this.isDefault = isDefault;
        this.event = event;
    }

    public NameTranslations(int nameId, String languageCode) {
        this.nameTranslationsPK = new NameTranslationsPK(nameId, languageCode);
    }

    public NameTranslationsPK getNameTranslationsPK() {
        return nameTranslationsPK;
    }

    public void setNameTranslationsPK(NameTranslationsPK nameTranslationsPK) {
        this.nameTranslationsPK = nameTranslationsPK;
    }
    
    @Override
    public String getLanguageCode(){
        return nameTranslationsPK.getLanguageCode();
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

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nameTranslationsPK != null ? nameTranslationsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NameTranslations)) {
            return false;
        }
        NameTranslations other = (NameTranslations) object;
        if ((this.nameTranslationsPK == null && other.nameTranslationsPK != null) || (this.nameTranslationsPK != null && !this.nameTranslationsPK.equals(other.nameTranslationsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.NameTranslations[ nameTranslationsPK=" + nameTranslationsPK + " ]";
    }
    
}
