package org.example.Repository;

import org.example.Entity.Appointment;
import org.example.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByUser(User user);
    Appointment findByAppointmentIdAndUserUsername(int appointmentId, String username);
}