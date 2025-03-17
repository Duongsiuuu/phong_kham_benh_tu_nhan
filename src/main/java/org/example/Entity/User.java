package org.example.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

    @Entity
    @Table(name = "USER")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int userId;

        @Getter @Setter
        private String name;
        @Getter @Setter
        private String username;
        @Getter @Setter
        private String password;
        @Getter @Setter
        private String phone;
        @Getter @Setter
        private String email;
        @Getter @Setter
        private String gender;
        @Getter @Setter
        private String roleCode;
        @Getter @Setter
        private String cccd;
        @Getter @Setter
        private String insuranceNumber;
        @Getter @Setter
        private String address;

        @ManyToOne
        @JoinColumn(name = "department_id")
        @Getter @Setter
        private Department department;
    }