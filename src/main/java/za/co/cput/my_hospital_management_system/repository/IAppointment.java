package za.co.cput.my_hospital_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.cput.my_hospital_management_system.model.Appointment;

public interface IAppointment extends JpaRepository<Appointment,Long> {
}
