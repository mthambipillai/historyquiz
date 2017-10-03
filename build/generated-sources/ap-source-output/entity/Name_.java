package entity;

import entity.NameTranslations;
import entity.Tag;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-08-23T18:25:10")
@StaticMetamodel(Name.class)
public class Name_ { 

    public static volatile SingularAttribute<Name, String> image;
    public static volatile CollectionAttribute<Name, Tag> tagCollection;
    public static volatile SingularAttribute<Name, String> name;
    public static volatile SingularAttribute<Name, Integer> nameId;
    public static volatile SingularAttribute<Name, String> event;
    public static volatile CollectionAttribute<Name, NameTranslations> nameTranslationsCollection;

}