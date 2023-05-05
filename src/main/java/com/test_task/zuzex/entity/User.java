package com.test_task.zuzex.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apartments")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "password")
    private String password;
    @OneToOne(
        mappedBy = "owner",
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
        },
        fetch = FetchType.EAGER
    )
    private Apartment property;
    @ManyToOne(
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
        },
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
