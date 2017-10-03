package entity;

import entity.Name;
import entity.NameTranslationsPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-08-23T18:25:10")
@StaticMetamodel(NameTranslations.class)
public class NameTranslations_ { 

    public static volatile SingularAttribute<NameTranslations, NameTranslationsPK> nameTranslationsPK;
    public static volatile SingularAttribute<NameTranslations, Boolean> isDefault;
    public static volatile SingularAttribute<NameTranslations, Name> name;
    public static volatile SingularAttribute<NameTranslations, String> event;

}