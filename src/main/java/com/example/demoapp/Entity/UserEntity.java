package com.example.demoapp.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_tb")
@EqualsAndHashCode(callSuper = false)
public class UserEntity extends BaseEntity<UserEntity,Long>{

    protected String userName;

}
