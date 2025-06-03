package za.co.cput.my_hospital_management_system.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.management.relation.Role;
import java.util.Collection;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    // Link to doctor/patient/staff if applicable
    private Long associatedId;
    private String associatedType; // "DOCTOR", "PATIENT", "STAFF", "ADMIN"

}
