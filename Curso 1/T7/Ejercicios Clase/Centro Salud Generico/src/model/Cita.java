package model;

public class Cita {
    private String fechaCita;
    private String especialidad;
    private Doctor doctor;
    private Paciente paciente;

    public Cita() {
    }

    public Cita(String fechaCita, String especialidad, Doctor doctor, Paciente paciente) {
        this.fechaCita = fechaCita;
        this.especialidad = especialidad;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
