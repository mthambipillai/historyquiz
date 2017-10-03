package entity;

import entity.Player;
import entity.ScorePK;
import entity.Tag;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-08-23T18:25:10")
@StaticMetamodel(Score.class)
public class Score_ { 

    public static volatile SingularAttribute<Score, ScorePK> scorePK;
    public static volatile SingularAttribute<Score, Date> date;
    public static volatile SingularAttribute<Score, Integer> bestScore;
    public static volatile SingularAttribute<Score, Tag> tag;
    public static volatile SingularAttribute<Score, Player> player;

}