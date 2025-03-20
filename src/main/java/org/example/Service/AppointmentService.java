package org.example.Service;

import org.example.Entity.Appointment;
import org.example.Entity.Department;
import org.example.Entity.User;
import org.example.Repository.AppointmentRepository;
import org.example.Repository.DepartmentRepository;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Appointment createAppointment(Appointment appointment, String username, boolean forSelf) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Department department = departmentRepository.findById(appointment.getDepartment().getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        appointment.setUser(user);
        appointment.setDepartment(department);

        if (!forSelf) {
            if (appointment.getRelativeName() == null || appointment.getRelativeIdCard() == null) {
                throw new IllegalArgumentException("Relative name and ID card must be provided for relative appointments.");
            }
        }

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> listAppointments(String username) {
        User user = userRepository.findByUsername(username);
        List<Appointment> appointments = appointmentRepository.findByUser(user);
        appointments.forEach(appointment -> {
            Department department = departmentRepository.findById(appointment.getDepartment().getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            appointment.getDepartment().setDepartmentName(department.getDepartmentName());
        });
        return appointments;
    }

    public Appointment updateAppointment(int id, Appointment appointment, String username) {
        Appointment existingAppointment = appointmentRepository.findByAppointmentIdAndUserUsername(id, username);
        if (existingAppointment != null) {
            existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
            existingAppointment.setStatus(appointment.getStatus());
            existingAppointment.setDepartment(appointment.getDepartment());
            Department department = departmentRepository.findById(appointment.getDepartment().getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            existingAppointment.getDepartment().setDepartmentName(department.getDepartmentName());
            return appointmentRepository.save(existingAppointment);
        }
        throw new RuntimeException("Appointment not found or you do not have permission to update it.");
    }

    public void deleteAppointment(int id, String username) {
        Appointment existingAppointment = appointmentRepository.findByAppointmentIdAndUserUsername(id, username);
        if (existingAppointment != null) {
            appointmentRepository.delete(existingAppointment);
        } else {
            throw new RuntimeException("Appointment not found or you do not have permission to delete it.");
        }
    }
}