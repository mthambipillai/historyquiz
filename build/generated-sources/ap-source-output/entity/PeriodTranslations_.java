package entity;

import entity.Period;
import entity.PeriodTranslationsPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-08-23T18:25:10")
@StaticMetamodel(PeriodTranslations.class)
public class PeriodTranslations_ { 

    public static volatile SingularAttribute<PeriodTranslations, PeriodTranslationsPK> periodTranslationsPK;
    public static volatile SingularAttribute<PeriodTranslations, Boolean> isDefault;
    public static volatile SingularAttribute<PeriodTranslations, Period> period;
    public static volatile SingularAttribute<PeriodTranslations, String> event;

}