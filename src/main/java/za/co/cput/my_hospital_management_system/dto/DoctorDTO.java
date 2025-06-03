package za.co.cput.my_hospital_management_system.dto;

import lombok.Data;

@Data
public class DoctorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String speciality;
    private Long departmentId;
    private String departmentName;
}
