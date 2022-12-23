package ru.example.project.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class FirstObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String candidacy;
//    @OneToMany(mappedBy = "firstObject")
//    private Set<SecondObject> secondObjects;

}
