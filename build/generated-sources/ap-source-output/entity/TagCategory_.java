package entity;

import entity.Tag;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-08-23T18:25:10")
@StaticMetamodel(TagCategory.class)
public class TagCategory_ { 

    public static volatile CollectionAttribute<TagCategory, Tag> tagCollection;
    public static volatile SingularAttribute<TagCategory, Integer> tagCategoryId;
    public static volatile SingularAttribute<TagCategory, String> tagCategoryName;

}