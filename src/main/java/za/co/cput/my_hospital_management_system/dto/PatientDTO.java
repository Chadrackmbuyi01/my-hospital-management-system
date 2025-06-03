package za.co.cput.my_hospital_management_system.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private LocalDate dataOfBirth;
    private String bloodType;
    private Long departmentId;
    private String departmentName;
}
