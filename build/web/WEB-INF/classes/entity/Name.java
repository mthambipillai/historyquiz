/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
@Table(name = "name")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Name.findAll", query = "SELECT n FROM Name n"),
    @NamedQuery(name = "Name.findByNameId", query = "SELECT n FROM Name n WHERE n.nameId = :nameId"),
    @NamedQuery(name = "Name.findByEvent", query = "SELECT n FROM Name n WHERE n.event = :event"),
    @NamedQuery(name = "Name.findByName", query = "SELECT n FROM Name n WHERE n.name = :name"),
    @NamedQuery(name = "Name.findByImage", query = "SELECT n FROM Name n WHERE n.image = :image")})
public class Name extends Question  implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "name")
    private Collection<NameTranslations> nameTranslationsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "name_id")
    private Integer nameId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "event")
    private String event;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "image")
    private String image;
    @ManyToMany(mappedBy = "nameCollection")
    private Collection<Tag> tagCollection;

    public Name() {
    }

    public Name(Integer nameId) {
        this.nameId = nameId;
    }

    public Name(Integer nameId, String event, String name, String image) {
        this.nameId = nameId;
        this.event = event;
        this.name = name;
        this.image = image;
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (nameId != null ? nameId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Name)) {
            return false;
        }
        Name other = (Name) object;
        if ((this.nameId == null && other.nameId != null) || (this.nameId != null && !this.nameId.equals(other.nameId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Name[ nameId=" + nameId + " ]";
    }

    @Override
    public boolean containsTag(Tag tag) {
        return tagCollection.contains(tag);
    }
    
    @Override
    public boolean correct(int level, String... entries) {
        assert (entries.length == 1);
        String nameToCompare = name.toLowerCase().replaceAll("\\s", "");
	String entryToCompare = entries[0].toLowerCase().replaceAll("\\s", "");
	int len = entryToCompare.length();
        if (len != nameToCompare.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (nameToCompare.charAt(i) != entryToCompare.charAt(i)) {
                count++;
            }
        }
        return count <= level;
    }

    @Override
    public String[] getAnswersTypes() {
        String[] string = {"name"};
        return string;
    }
    
    @Override
    public boolean parseAndCorrect(int level, String entry){
        return correct(level,entry);
    }
    
    @Override
    public String getAnswerText() {
        return name;
    }

    @XmlTransient
    public Collection<NameTranslations> getNameTranslationsCollection() {
        return nameTranslationsCollection;
    }

    public void setNameTranslationsCollection(Collection<NameTranslations> nameTranslationsCollection) {
        this.nameTranslationsCollection = nameTranslationsCollection;
    }
    
    @Override
    public ArrayList<QuestionTranslations> getQuestionsTranslationsCollection(){
        ArrayList<QuestionTranslations> res = new ArrayList<QuestionTranslations>();
        for(NameTranslations nt: nameTranslationsCollection){
            res.add(nt);
        }
        return res;
    }
}
