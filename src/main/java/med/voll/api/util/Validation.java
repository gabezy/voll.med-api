package med.voll.api.util;

import med.voll.api.appointment.Appointment;
import med.voll.api.doctor.Doctor;
import med.voll.api.patient.Patient;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Validation {

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static boolean isPatientActive(Patient patient) {
        return patient.isActive();
    }

    public static boolean isDoctorActive(Doctor doctor) {
        return doctor.isActive();
    }
    public static boolean isAppointmentDateValid(LocalDateTime appointmentDate) {
        int dayOfWeek = appointmentDate.getDayOfWeek().getValue();
        int hour = appointmentDate.getHour();
        return dayOfWeek != 7 && (hour >= 7 && hour <= 19) && isAppointmentDateAtLeast30MinutesEarly(appointmentDate);
    }

    public static boolean isAppointmentDateAtLeast30MinutesEarly(LocalDateTime appointmentDate) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration duration = Duration.between(currentDateTime, appointmentDate);
        long minutes = duration.toMinutes();
        long MIN_APPOINTMENT_GAP = 30;
        return minutes >= MIN_APPOINTMENT_GAP;
    }

    public static boolean patientHasAppointmentInTheSameDay(List<Appointment> appointmentList, LocalDateTime appointmentDateTime) {
        boolean appointmentInSameDay = false;
        LocalDate appointmentDate = appointmentDateTime.toLocalDate();
        for (Appointment appointment : appointmentList) {
            LocalDate date = appointment.getDate().toLocalDate();
            if (appointmentDate.isEqual(date)) {
                appointmentInSameDay = true;
                break;
            }
        }
        return  appointmentInSameDay;
    }

    public static boolean doctorHasAppointmentInTheTime(List<Appointment> appointmentList, LocalDateTime appointmentDateTime) {
        // TODO: extends function to check if the appointmentDateTime is between the 1 hour after gap
        boolean appointmentInSameTime = false;
        for (Appointment appointment : appointmentList) {
            LocalDateTime date = appointment.getDate();
            if (appointmentDateTime.isEqual(date)) {
                appointmentInSameTime = true;
                break;
            }
        }
        return  appointmentInSameTime;
    }

}
