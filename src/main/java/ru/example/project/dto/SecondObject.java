package ru.example.project.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SecondObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    @ManyToOne
//    @JoinColumn(name = "prof_id", nullable = false)
//    private FirstObject firstObject;
    private Long prof_id;

}
