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
@Table(name = "tag_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TagCategory.findAll", query = "SELECT t FROM TagCategory t"),
    @NamedQuery(name = "TagCategory.findByTagCategoryId", query = "SELECT t FROM TagCategory t WHERE t.tagCategoryId = :tagCategoryId"),
    @NamedQuery(name = "TagCategory.findByTagCategoryName", query = "SELECT t FROM TagCategory t WHERE t.tagCategoryName = :tagCategoryName")})
public class TagCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tag_category_id")
    private Integer tagCategoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tag_category_name")
    private String tagCategoryName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<Tag> tagCollection;

    public TagCategory() {
    }

    public TagCategory(Integer tagCategoryId) {
        this.tagCategoryId = tagCategoryId;
    }

    public TagCategory(Integer tagCategoryId, String tagCategoryName) {
        this.tagCategoryId = tagCategoryId;
        this.tagCategoryName = tagCategoryName;
    }

    public Integer getTagCategoryId() {
        return tagCategoryId;
    }

    public void setTagCategoryId(Integer tagCategoryId) {
        this.tagCategoryId = tagCategoryId;
    }

    public String getTagCategoryName() {
        return tagCategoryName;
    }

    public void setTagCategoryName(String tagCategoryName) {
        this.tagCategoryName = tagCategoryName;
    }

    @XmlTransient
    public Collection<Tag> getTagCollection() {
        return tagCollection;
    }

    public void setTagCollection(Collection<Tag> tagCollection) {
        this.tagCollection = tagCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagCategoryId != null ? tagCategoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagCategory)) {
            return false;
        }
        TagCategory other = (TagCategory) object;
        if ((this.tagCategoryId == null && other.tagCategoryId != null) || (this.tagCategoryId != null && !this.tagCategoryId.equals(other.tagCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TagCategory[ tagCategoryId=" + tagCategoryId + " ]";
    }
    
}
