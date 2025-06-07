package za.co.cput.my_hospital_management_system.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.cput.my_hospital_management_system.dto.DepartmentDTO;
import za.co.cput.my_hospital_management_system.dto.DoctorDTO;
import za.co.cput.my_hospital_management_system.model.Department;
import za.co.cput.my_hospital_management_system.model.Doctor;
import za.co.cput.my_hospital_management_system.repository.IDepartment;
import za.co.cput.my_hospital_management_system.repository.IDoctor;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private IDoctor doctorRepository;

    @Autowired
    private IDepartment departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        return convertToDTO(doctor);
    }

    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Department department = departmentRepository.findById(doctorDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " +
                        doctorDTO.getDepartmentId()));

        Doctor doctor = convertToEntity(doctorDTO);
        doctor.setDepartment(department);

        Doctor savedDoctor = doctorRepository.save(doctor);
        return convertToDTO(savedDoctor);
    }

    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO){
        Doctor existingDoctor = doctorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Doctor not found with id:" + id));

        existingDoctor.setFirstName(doctorDTO.getFirstName());
        existingDoctor.setLastName(doctorDTO.getLastName());
        existingDoctor.setEmail(doctorDTO.getEmail());
        existingDoctor.setPhone(doctorDTO.getPhone());
        existingDoctor.setSpeciality(doctorDTO.getSpeciality());
        //existingDoctor.setDepartment(doctorDTO.getDepartmentName());

        Doctor updateDoctor = doctorRepository.save(existingDoctor);
        return convertToDTO(updateDoctor);
    }

    public void deleteDoctor(Long id){
        if(!doctorRepository.existsById(id)){
            throw new ResourceNotFoundException("Doctor not found with id:" + id);
        }
        doctorRepository.deleteById(id);
    }

    private DoctorDTO convertToDTO(Doctor doctor) {
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        doctorDTO.setDepartmentId(doctor.getDepartment().getId());
        doctorDTO.setDepartmentName(doctor.getDepartment().getName());
        return doctorDTO;
    }

    private Doctor convertToEntity(DoctorDTO doctorDTO) {
        return modelMapper.map(doctorDTO, Doctor.class);
    }

}
