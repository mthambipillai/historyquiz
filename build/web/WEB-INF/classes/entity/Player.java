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
@Table(name = "player")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findByPlayerId", query = "SELECT p FROM Player p WHERE p.playerId = :playerId"),
    @NamedQuery(name = "Player.findByPseudo", query = "SELECT p FROM Player p WHERE p.pseudo = :pseudo"),
    @NamedQuery(name = "Player.findByPassword", query = "SELECT p FROM Player p WHERE p.password = :password"),
    @NamedQuery(name = "Player.findByEmail", query = "SELECT p FROM Player p WHERE p.email = :email")})
public class Player implements Serializable {
    @JoinTable(name = "users_group", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "player_id")}, inverseJoinColumns = {
        @JoinColumn(name = "group_id", referencedColumnName = "group_id")})
    @ManyToMany
    private Collection<Groups> groupsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "player_id")
    private Integer playerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pseudo")
    private String pseudo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private Collection<Score> scoreCollection;

    public Player() {
    }

    public Player(Integer playerId) {
        this.playerId = playerId;
    }

    public Player(Integer playerId, String pseudo, String password, String email) {
        this.playerId = playerId;
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        hash += (playerId != null ? playerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.playerId == null && other.playerId != null) || (this.playerId != null && !this.playerId.equals(other.playerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Player[ playerId=" + playerId + " ]";
    }

    @XmlTransient
    public Collection<Groups> getGroupsCollection() {
        return groupsCollection;
    }

    public void setGroupsCollection(Collection<Groups> groupsCollection) {
        this.groupsCollection = groupsCollection;
    }
    
}
