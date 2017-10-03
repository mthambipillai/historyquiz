package entity;

import entity.Player;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-08-23T18:25:10")
@StaticMetamodel(Groups.class)
public class Groups_ { 

    public static volatile CollectionAttribute<Groups, Player> playerCollection;
    public static volatile SingularAttribute<Groups, String> groupName;
    public static volatile SingularAttribute<Groups, Integer> groupId;

}