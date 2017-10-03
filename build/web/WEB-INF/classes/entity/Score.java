/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Melkior
 */
@Entity
@Table(name = "score")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Score.findAll", query = "SELECT s FROM Score s"),
    @NamedQuery(name = "Score.findByTagId", query = "SELECT s FROM Score s WHERE s.scorePK.tagId = :tagId"),
    @NamedQuery(name = "Score.findByPlayerId", query = "SELECT s FROM Score s WHERE s.scorePK.playerId = :playerId"),
    @NamedQuery(name = "Score.findByBestScore", query = "SELECT s FROM Score s WHERE s.bestScore = :bestScore"),
    @NamedQuery(name = "Score.findByDate", query = "SELECT s FROM Score s WHERE s.date = :date")})
public class Score implements Serializable, Comparable<Score>{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ScorePK scorePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "best_score")
    private int bestScore;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "player_id", referencedColumnName = "player_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Player player;
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tag tag;

    public Score() {
    }

    public Score(ScorePK scorePK) {
        this.scorePK = scorePK;
    }

    public Score(ScorePK scorePK, int bestScore, Date date) {
        this.scorePK = scorePK;
        this.bestScore = bestScore;
        this.date = date;
    }

    public Score(int tagId, int playerId) {
        this.scorePK = new ScorePK(tagId, playerId);
    }

    public ScorePK getScorePK() {
        return scorePK;
    }

    public void setScorePK(ScorePK scorePK) {
        this.scorePK = scorePK;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scorePK != null ? scorePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Score)) {
            return false;
        }
        Score other = (Score) object;
        if ((this.scorePK == null && other.scorePK != null) || (this.scorePK != null && !this.scorePK.equals(other.scorePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Score[ scorePK=" + scorePK + " ]";
    }

    @Override
    public int compareTo(Score o) {
        return new Integer(this.bestScore).compareTo(o.bestScore);
    }
    
    public String getDateFormatted(){
        return date.getDate()+"."+(date.getMonth()+1)+"."+(date.getYear()+1900);
    }
}
