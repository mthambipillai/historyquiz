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

/**
 *
 * @author Melkior
 */
@Embeddable
public class ScorePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "tag_id")
    private int tagId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "player_id")
    private int playerId;

    public ScorePK() {
    }

    public ScorePK(int tagId, int playerId) {
        this.tagId = tagId;
        this.playerId = playerId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tagId;
        hash += (int) playerId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScorePK)) {
            return false;
        }
        ScorePK other = (ScorePK) object;
        if (this.tagId != other.tagId) {
            return false;
        }
        if (this.playerId != other.playerId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ScorePK[ tagId=" + tagId + ", playerId=" + playerId + " ]";
    }
    
}
