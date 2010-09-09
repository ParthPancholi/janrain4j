package com.googlecode.janrain4j.demo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Account {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long primaryKey;
    
    public Account() {
    }
    
    public Long getPrimaryKey() {
        return primaryKey;
    }
}
