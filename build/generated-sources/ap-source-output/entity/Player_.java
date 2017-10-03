package entity;

import entity.Groups;
import entity.Score;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-08-23T18:25:10")
@StaticMetamodel(Player.class)
public class Player_ { 

    public static volatile SingularAttribute<Player, String> password;
    public static volatile CollectionAttribute<Player, Score> scoreCollection;
    public static volatile CollectionAttribute<Player, Groups> groupsCollection;
    public static volatile SingularAttribute<Player, String> pseudo;
    public static volatile SingularAttribute<Player, String> email;
    public static volatile SingularAttribute<Player, Integer> playerId;

}