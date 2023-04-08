package med.voll.api.util;

import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.Patient;


public class RequestFiledValidationUtil {

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static boolean isPatientActive(Patient patient) {
        return patient.isActive();
    }

    public static boolean isDoctorActive(Doctor doctor) {
        return doctor.isActive();
    }


}
