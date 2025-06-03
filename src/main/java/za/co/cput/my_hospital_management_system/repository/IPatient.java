package za.co.cput.my_hospital_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.cput.my_hospital_management_system.model.Patient;

public interface IPatient extends JpaRepository<Patient,Long> {
}
