package com.training.debonair.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass // để spring-jpa hiểu các properties này là column
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO-INCREMENT
    @Column(name = "id") // column name
    private Integer id;

    @Column(name = "status", nullable = true)
    private Boolean status = Boolean.TRUE;

    @Column(name = "created_date", nullable = true)
    private Date createdDate;

    @Column(name = "updated_date", nullable = true)
    private Date updatedDate;

}
