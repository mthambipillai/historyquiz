/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;
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
@Table(name = "exact_date")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExactDate.findAll", query = "SELECT e FROM ExactDate e"),
    @NamedQuery(name = "ExactDate.findByExactDateId", query = "SELECT e FROM ExactDate e WHERE e.exactDateId = :exactDateId"),
    @NamedQuery(name = "ExactDate.findByEvent", query = "SELECT e FROM ExactDate e WHERE e.event = :event"),
    @NamedQuery(name = "ExactDate.findByDay", query = "SELECT e FROM ExactDate e WHERE e.day = :day"),
    @NamedQuery(name = "ExactDate.findByMonth", query = "SELECT e FROM ExactDate e WHERE e.month = :month"),
    @NamedQuery(name = "ExactDate.findByYear", query = "SELECT e FROM ExactDate e WHERE e.year = :year"),
    @NamedQuery(name = "ExactDate.findByImage", query = "SELECT e FROM ExactDate e WHERE e.image = :image")})
public class ExactDate extends Question  implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exactDate")
    private Collection<ExactDateTranslations> exactDateTranslationsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "exact_date_id")
    private Integer exactDateId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "event")
    private String event;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day")
    private int day;
    @Basic(optional = false)
    @NotNull
    @Column(name = "month")
    private int month;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "image")
    private String image;
    @ManyToMany(mappedBy = "exactDateCollection")
    private Collection<Tag> tagCollection;
    
    

    public ExactDate() {
    }

    public ExactDate(Integer exactDateId) {
        this.exactDateId = exactDateId;
    }

    public ExactDate(Integer exactDateId, String event, int day, int month, int year, String image) {
        this.exactDateId = exactDateId;
        this.event = event;
        this.day = day;
        this.month = month;
        this.year = year;
        this.image = image;
    }

    public Integer getExactDateId() {
        return exactDateId;
    }

    public void setExactDateId(Integer exactDateId) {
        this.exactDateId = exactDateId;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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
        hash += (exactDateId != null ? exactDateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExactDate)) {
            return false;
        }
        ExactDate other = (ExactDate) object;
        if ((this.exactDateId == null && other.exactDateId != null) || (this.exactDateId != null && !this.exactDateId.equals(other.exactDateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ExactDate[ exactDateId=" + exactDateId + " ]";
    }
    
    @Override
    public boolean containsTag(Tag tag) {
        return tagCollection.contains(tag);
    }
    
    @Override
    public boolean correct(int level, String... entries) {
        assert (entries.length == 3);
        try {
            int absval1 = Math.abs(Integer.valueOf(entries[0]) - day);
            int absval2 = Math.abs(Integer.valueOf(entries[1]) - month);
            int absval3 = Math.abs(Integer.valueOf(entries[2]) - year);
            return absval1 <= level && absval2 <= level && absval3 <= level;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String[] getAnswersTypes() {
        String[] strings = {"day", "month", "year"};
        return strings;
    }
    
    @Override
    public boolean parseAndCorrect(int level, String entry){
        Pattern separator = Pattern.compile("\\.");
        String[] s = separator.split(entry);
        if(s.length==3){
            return correct(level,s[0],s[1],s[2]);
        }else{
            return false;
        }
    }
    
    @Override
    public String getAnswerText() {
        return day+"."+month+"."+year;
    }

    @XmlTransient
    public Collection<ExactDateTranslations> getExactDateTranslationsCollection() {
        return exactDateTranslationsCollection;
    }

    public void setExactDateTranslationsCollection(Collection<ExactDateTranslations> exactDateTranslationsCollection) {
        this.exactDateTranslationsCollection = exactDateTranslationsCollection;
    }
    
    @Override
    public ArrayList<QuestionTranslations> getQuestionsTranslationsCollection(){
        ArrayList<QuestionTranslations> res = new ArrayList<QuestionTranslations>();
        for(ExactDateTranslations edt: exactDateTranslationsCollection){
            res.add(edt);
        }
        return res;
    }
}
