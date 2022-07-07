package com.dorr.spring.neo4j.login.componment.entity;

public class Group {
    enum GroupRole{
        ADMIN,
        USER,
        DEVELOPER,
        CUSTOMER
    }

    enum VipDif{
        NO_DIAMONG,
        DIAMONG,
        SLIVER,
        GLODEN,
        PLATLIN_GLOGEN,
        BLACK_GOLDEN
    }

    private GroupRole role;
    private String name;
    private VipDif vip;

}
