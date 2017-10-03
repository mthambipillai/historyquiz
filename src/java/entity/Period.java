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
@Table(name = "period")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Period.findAll", query = "SELECT p FROM Period p"),
    @NamedQuery(name = "Period.findByPeriodId", query = "SELECT p FROM Period p WHERE p.periodId = :periodId"),
    @NamedQuery(name = "Period.findByEvent", query = "SELECT p FROM Period p WHERE p.event = :event"),
    @NamedQuery(name = "Period.findByBeginYear", query = "SELECT p FROM Period p WHERE p.beginYear = :beginYear"),
    @NamedQuery(name = "Period.findByEndYear", query = "SELECT p FROM Period p WHERE p.endYear = :endYear"),
    @NamedQuery(name = "Period.findByImage", query = "SELECT p FROM Period p WHERE p.image = :image")})
public class Period extends Question  implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "period")
    private Collection<PeriodTranslations> periodTranslationsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "period_id")
    private Integer periodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "event")
    private String event;
    @Basic(optional = false)
    @NotNull
    @Column(name = "begin_year")
    private int beginYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_year")
    private int endYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "image")
    private String image;
    @ManyToMany(mappedBy = "periodCollection")
    private Collection<Tag> tagCollection;
    
    

    public Period() {
    }

    public Period(Integer periodId) {
        this.periodId = periodId;
    }

    public Period(Integer periodId, String event, int beginYear, int endYear, String image) {
        this.periodId = periodId;
        this.event = event;
        this.beginYear = beginYear;
        this.endYear = endYear;
        this.image = image;
    }

    public Integer getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Integer periodId) {
        this.periodId = periodId;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(int beginYear) {
        this.beginYear = beginYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
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
        hash += (periodId != null ? periodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Period)) {
            return false;
        }
        Period other = (Period) object;
        if ((this.periodId == null && other.periodId != null) || (this.periodId != null && !this.periodId.equals(other.periodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Period[ periodId=" + periodId + " ]";
    }

    @Override
    public boolean containsTag(Tag tag) {
        return tagCollection.contains(tag);
    }
    
    @Override
    public boolean correct(int level, String... entries) {
        assert (entries.length == 2);
        try {
            int absval1 = Math.abs(Integer.valueOf(entries[0]) - beginYear);
            int absval2 = Math.abs(Integer.valueOf(entries[1]) - endYear);
            return absval1 <= level && absval2 <= level;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String[] getAnswersTypes() {
        String[] strings = {"beginYear", "endYear"};
        return strings;
    }

    @Override
    public boolean parseAndCorrect(int level, String entry) {
        Pattern separator = Pattern.compile("-");
        String[] s = separator.split(entry);
        if(s.length==2){
            return correct(level,s[0],s[1]);
        }else{
            return false;
        }
    }
    
    @Override
    public String getAnswerText() {
        return beginYear+"-"+endYear;
    }

    @XmlTransient
    public Collection<PeriodTranslations> getPeriodTranslationsCollection() {
        return periodTranslationsCollection;
    }

    public void setPeriodTranslationsCollection(Collection<PeriodTranslations> periodTranslationsCollection) {
        this.periodTranslationsCollection = periodTranslationsCollection;
    }
    
    @Override
    public ArrayList<QuestionTranslations> getQuestionsTranslationsCollection(){
        ArrayList<QuestionTranslations> res = new ArrayList<QuestionTranslations>();
        for(PeriodTranslations pt: periodTranslationsCollection){
            res.add(pt);
        }
        return res;
    }
}
