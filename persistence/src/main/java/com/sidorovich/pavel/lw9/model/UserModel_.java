package com.sidorovich.pavel.lw9.model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserModel.class)
public abstract class UserModel_ {

    public static volatile SingularAttribute<UserModel, Long> id;
    public static volatile SingularAttribute<UserModel, String> firstName;
    public static volatile SingularAttribute<UserModel, Boolean> isMale;
    public static volatile SingularAttribute<UserModel, String> idNumber;
    public static volatile SingularAttribute<UserModel, String> homeTel;
    public static volatile SingularAttribute<UserModel, CityModel> city;

    public static final String TABLE = "user_details";
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String IS_MALE = "is_male";
    public static final String ID_NUMBER = "id_number";
    public static final String HOME_TEL = "home_tel";
    public static final String CITY_ID = "city_id";

}
