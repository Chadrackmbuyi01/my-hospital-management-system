package za.co.cput.my_hospital_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.regex.Pattern;

@Entity
@Data
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Doctor> doctors;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Staff> staff;

    @OneToMany(mappedBy = "department")
    private Set<Patient> patients;
}
