package entity;

import entity.DateTranslations;
import entity.Tag;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-08-23T18:25:10")
@StaticMetamodel(Date.class)
public class Date_ { 

    public static volatile SingularAttribute<Date, String> image;
    public static volatile CollectionAttribute<Date, Tag> tagCollection;
    public static volatile SingularAttribute<Date, Integer> year;
    public static volatile SingularAttribute<Date, Integer> dateId;
    public static volatile CollectionAttribute<Date, DateTranslations> dateTranslationsCollection;
    public static volatile SingularAttribute<Date, String> event;

}