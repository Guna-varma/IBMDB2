package com.projectdb2.ibmdb2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "employeeId")
    private String employeeId;

    @Column(name ="firstName")
    private String firstName;

    @Column(name ="lastName")
    private String lastName;

    @Column(name ="bloodGroup")
    private String bloodGroup;

}
