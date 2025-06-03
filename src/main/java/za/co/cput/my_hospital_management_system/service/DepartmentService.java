package za.co.cput.my_hospital_management_system.service;

import org.modelmapper.ModelMapper;
//import za.co.cput.my_hospital_management_system.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.cput.my_hospital_management_system.dto.DepartmentDTO;
import za.co.cput.my_hospital_management_system.model.Department;
import za.co.cput.my_hospital_management_system.repository.IDepartment;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private IDepartment departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<DepartmentDTO> getAllDepartments(){
        return departmentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Long id){
        Department department = departmentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Department not found with id:" + id));
        return convertToDTO(department);
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO){
        Department department = convertToEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return convertToDTO(savedDepartment);
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO){
        Department existingDepartment = departmentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Department not found with id:" + id));

        existingDepartment.setName(departmentDTO.getName());
        existingDepartment.setDescription(departmentDTO.getDescription());

        Department updateDepartment = departmentRepository.save(existingDepartment);
        return convertToDTO(updateDepartment);
    }

    public void deleteDepartment(Long id){
        if(!departmentRepository.existsById(id)){
            throw new ResourceNotFoundException("Department not found with id:" + id);
        }
        departmentRepository.deleteById(id);
    }

    private DepartmentDTO convertToDTO(Department department){
        return modelMapper.map(department, DepartmentDTO.class);
    }

    private Department convertToEntity(DepartmentDTO departmentDTO){
        return modelMapper.map(departmentDTO, Department.class);
    }


}
