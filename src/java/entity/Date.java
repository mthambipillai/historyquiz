/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "date")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Date.findAll", query = "SELECT d FROM Date d"),
    @NamedQuery(name = "Date.findByDateId", query = "SELECT d FROM Date d WHERE d.dateId = :dateId"),
    @NamedQuery(name = "Date.findByEvent", query = "SELECT d FROM Date d WHERE d.event = :event"),
    @NamedQuery(name = "Date.findByYear", query = "SELECT d FROM Date d WHERE d.year = :year"),
    @NamedQuery(name = "Date.findByImage", query = "SELECT d FROM Date d WHERE d.image = :image")})
public class Date extends Question implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "date")
    private Collection<DateTranslations> dateTranslationsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "date_id")
    private Integer dateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "event")
    private String event;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "image")
    private String image;
    @ManyToMany(mappedBy = "dateCollection")
    private Collection<Tag> tagCollection;

    public Date() {
    }

    public Date(Integer dateId) {
        this.dateId = dateId;
    }

    public Date(Integer dateId, String event, int year, String image) {
        this.dateId = dateId;
        this.event = event;
        this.year = year;
        this.image = image;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    @Override
    public Collection<Tag> getTagCollection() {
        return tagCollection;
    }

    public void setTagCollection(Collection<Tag> tagCollection) {
        this.tagCollection = tagCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dateId != null ? dateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Date)) {
            return false;
        }
        Date other = (Date) object;
        if ((this.dateId == null && other.dateId != null) || (this.dateId != null && !this.dateId.equals(other.dateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Date[ dateId=" + dateId + " ]";
    }

    @Override
    public boolean containsTag(Tag tag) {
        return tagCollection.contains(tag);
    }

    @Override
    public boolean correct(int level, String... entries) {
        assert(entries.length==1);
        try
        {
            int absval = Math.abs(Integer.valueOf(entries[0])-year);
            return absval<=level;
	}
	catch(NumberFormatException e)
	{
            return false;
	}
    }

    @Override
    public String[] getAnswersTypes() {
        String[] string = {"year"};
	return string;
    }

    @Override
    public boolean parseAndCorrect(int level, String entry){
        return correct(level,entry);
    }

    @Override
    public String getAnswerText() {
        return ""+year;
    }

    @XmlTransient
    public Collection<DateTranslations> getDateTranslationsCollection() {
        return dateTranslationsCollection;
    }

    public void setDateTranslationsCollection(Collection<DateTranslations> dateTranslationsCollection) {
        this.dateTranslationsCollection = dateTranslationsCollection;
    }
    
    @Override
    public ArrayList<QuestionTranslations> getQuestionsTranslationsCollection(){
        ArrayList<QuestionTranslations> res = new ArrayList<QuestionTranslations>();
        for(DateTranslations dt: dateTranslationsCollection){
            res.add(dt);
        }
        return res;
    }
}
