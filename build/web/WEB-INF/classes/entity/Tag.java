/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Melkior
 */
@Entity
@Table(name = "tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
    @NamedQuery(name = "Tag.findByTagId", query = "SELECT t FROM Tag t WHERE t.tagId = :tagId"),
    @NamedQuery(name = "Tag.findByName", query = "SELECT t FROM Tag t WHERE t.name = :name")})
public class Tag implements Serializable {
    @JoinColumn(name = "category_id", referencedColumnName = "tag_category_id")
    @ManyToOne(optional = false)
    private TagCategory categoryId;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tag_id")
    private Integer tagId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "tag_exact_date", joinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")}, inverseJoinColumns = {
        @JoinColumn(name = "exact_date_id", referencedColumnName = "exact_date_id")})
    @ManyToMany
    private Collection<ExactDate> exactDateCollection;
    @JoinTable(name = "tag_period", joinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")}, inverseJoinColumns = {
        @JoinColumn(name = "period_id", referencedColumnName = "period_id")})
    @ManyToMany
    private Collection<Period> periodCollection;
    @JoinTable(name = "tag_date", joinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")}, inverseJoinColumns = {
        @JoinColumn(name = "date_id", referencedColumnName = "date_id")})
    @ManyToMany
    private Collection<Date> dateCollection;
    @JoinTable(name = "tag_name", joinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")}, inverseJoinColumns = {
        @JoinColumn(name = "name_id", referencedColumnName = "name_id")})
    @ManyToMany
    private Collection<Name> nameCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tag")
    private Collection<Score> scoreCollection;

    public Tag() {
    }

    public Tag(Integer tagId) {
        this.tagId = tagId;
    }

    public Tag(Integer tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<ExactDate> getExactDateCollection() {
        return exactDateCollection;
    }

    public void setExactDateCollection(Collection<ExactDate> exactDateCollection) {
        this.exactDateCollection = exactDateCollection;
    }

    @XmlTransient
    public Collection<Period> getPeriodCollection() {
        return periodCollection;
    }

    public void setPeriodCollection(Collection<Period> periodCollection) {
        this.periodCollection = periodCollection;
    }

    @XmlTransient
    public Collection<Date> getDateCollection() {
        return dateCollection;
    }

    public void setDateCollection(Collection<Date> dateCollection) {
        this.dateCollection = dateCollection;
    }

    @XmlTransient
    public Collection<Name> getNameCollection() {
        return nameCollection;
    }

    public void setNameCollection(Collection<Name> nameCollection) {
        this.nameCollection = nameCollection;
    }

    @XmlTransient
    public Collection<Score> getScoreCollection() {
        return scoreCollection;
    }

    public void setScoreCollection(Collection<Score> scoreCollection) {
        this.scoreCollection = scoreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagId != null ? tagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.tagId == null && other.tagId != null) || (this.tagId != null && !this.tagId.equals(other.tagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tag[ tagId=" + tagId + " ]";
    }

    public TagCategory getTagCategory() {
        return categoryId;
    }

    public void setTagCategory(TagCategory categoryId) {
        this.categoryId = categoryId;
    }
    
}
