package com.training.debonair.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Categories extends BaseEntity {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "seo", length = 100, nullable = false)
    private String seo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Categories parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
    private Set<Categories> childs = new HashSet<Categories>();
    public void addChild(Categories category) {
        this.childs.add(category);
        category.setParent(this);
    }
    public void deleteChild(Categories category) {
        this.childs.remove(category);
        category.setParent(null);
    }
}
