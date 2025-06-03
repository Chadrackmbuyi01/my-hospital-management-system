package za.co.cput.my_hospital_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.cput.my_hospital_management_system.model.Prescription;

public interface IPrescription extends JpaRepository<Prescription,Long> {
}
