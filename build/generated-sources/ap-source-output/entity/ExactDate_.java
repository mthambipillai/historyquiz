package entity;

import entity.ExactDateTranslations;
import entity.Tag;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-08-23T18:25:10")
@StaticMetamodel(ExactDate.class)
public class ExactDate_ { 

    public static volatile SingularAttribute<ExactDate, String> image;
    public static volatile CollectionAttribute<ExactDate, Tag> tagCollection;
    public static volatile SingularAttribute<ExactDate, Integer> month;
    public static volatile SingularAttribute<ExactDate, Integer> year;
    public static volatile CollectionAttribute<ExactDate, ExactDateTranslations> exactDateTranslationsCollection;
    public static volatile SingularAttribute<ExactDate, Integer> exactDateId;
    public static volatile SingularAttribute<ExactDate, String> event;
    public static volatile SingularAttribute<ExactDate, Integer> day;

}