package org.example.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "DEPARTMENT")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;

    @Column(nullable = false, unique = true)
    private String departmentName;

//    // Getters and Setters
//    public int getDepartmentId() {
//        return departmentId;
//    }
//
//  public void setDepartmentId(int departmentId) {
//        this.departmentId = departmentId;
//    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}