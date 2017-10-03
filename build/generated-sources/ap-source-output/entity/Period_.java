package entity;

import entity.PeriodTranslations;
import entity.Tag;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-08-23T18:25:10")
@StaticMetamodel(Period.class)
public class Period_ { 

    public static volatile SingularAttribute<Period, Integer> periodId;
    public static volatile SingularAttribute<Period, String> image;
    public static volatile CollectionAttribute<Period, Tag> tagCollection;
    public static volatile SingularAttribute<Period, Integer> beginYear;
    public static volatile SingularAttribute<Period, String> event;
    public static volatile CollectionAttribute<Period, PeriodTranslations> periodTranslationsCollection;
    public static volatile SingularAttribute<Period, Integer> endYear;

}