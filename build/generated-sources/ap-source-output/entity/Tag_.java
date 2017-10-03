package entity;

import entity.Date;
import entity.ExactDate;
import entity.Name;
import entity.Period;
import entity.Score;
import entity.TagCategory;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-08-23T18:25:10")
@StaticMetamodel(Tag.class)
public class Tag_ { 

    public static volatile CollectionAttribute<Tag, Score> scoreCollection;
    public static volatile SingularAttribute<Tag, Integer> tagId;
    public static volatile CollectionAttribute<Tag, Date> dateCollection;
    public static volatile CollectionAttribute<Tag, ExactDate> exactDateCollection;
    public static volatile SingularAttribute<Tag, String> name;
    public static volatile CollectionAttribute<Tag, Period> periodCollection;
    public static volatile SingularAttribute<Tag, TagCategory> categoryId;
    public static volatile CollectionAttribute<Tag, Name> nameCollection;

}