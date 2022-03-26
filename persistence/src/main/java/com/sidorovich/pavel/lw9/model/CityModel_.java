package com.sidorovich.pavel.lw9.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CityModel.class)
public abstract class CityModel_ {

    public static volatile SingularAttribute<CityModel, Long> id;
    public static volatile SingularAttribute<CityModel, String> cityName;

    public static final String TABLE = "city";
    public static final String ID = "id";
    public static final String CITY_NAME = "city_name";

}
