package lld.AppointmentBookingSystem.Service;

import lld.AppointmentBookingSystem.entities.Appointment;
import lld.AppointmentBookingSystem.entities.Doctor;
import lld.AppointmentBookingSystem.entities.Patient;
import lld.AppointmentBookingSystem.entities.Status;
import lld.AppointmentBookingSystem.inMemoryDb.InMemroyDb;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class AppointmentService {
    private static final Integer DURATION = 30;
    public List<String> getAvailableAppointments(Integer doctorId,LocalDate date){
        Map<Integer, List<Appointment>> doctorSlotMap = InMemroyDb.getDoctorSlots();
        LocalTime startDay=LocalTime.of(9,0);
        LocalTime endDay=LocalTime.of(17,0);
        Duration duration = Duration.ofMinutes(30);

        List<LocalTime[]>allSlots= new ArrayList<>();
        for(LocalTime day=startDay;day.plus(duration).isBefore(endDay.plusMinutes(1));day=day.plus(duration)){
            allSlots.add(new LocalTime[]{day,day.plus(duration)});
        }
        List<Appointment> doctorSlots = doctorSlotMap
                .getOrDefault(doctorId, new ArrayList<>()).
        stream().filter(
                each->isSameDay(each.getStartTime(),date)
                        && each.getStatus()==Status.SCHEDULED
        ).toList();

        return getFilteredSlots(allSlots,doctorSlots);

    }
    public String bookAppointment(Integer patientId,Integer doctorId,LocalDateTime startTime){
        // check if patient and doctor is valid
        validateDoctorAndPatientDate(patientId,doctorId);

        // check if time is overlapping
        if(isAppointmentOverlapping(doctorId,startTime) || isPatientAppointmentOverLapping(patientId,startTime)){
            throw new RuntimeException("you can not book the current slot ");
        }


        // book appointment
        bookExecution(doctorId,patientId,startTime);
        return "booked successfully";
    }

    private boolean isPatientAppointmentOverLapping(Integer patientId, LocalDateTime startTime) {
        LocalDateTime endTime = startTime.plus(Duration.ofMinutes(DURATION));
        return InMemroyDb.getDoctorSlots().values().stream()
                .flatMap(List::stream)
                .anyMatch(appointment ->
                        appointment.getPatient().getId().equals(patientId) &&
                                appointment.getStatus() == Status.SCHEDULED &&
                                appointment.getStartTime().isBefore(endTime) &&
                                appointment.getEndTime().isAfter(startTime)
                );
    }

    private void bookExecution(Integer doctorId, Integer patientId, LocalDateTime startTime) {
        Doctor doctor = InMemroyDb.getDoctorMap().get(doctorId);
        Patient patient = InMemroyDb.getPatientMap().get(patientId);
        Appointment appointment = new Appointment(UUID.randomUUID().variant(), startTime,startTime.plus(Duration.ofMinutes(30)),Status.SCHEDULED,doctor,patient);
        List<Appointment> appointments = InMemroyDb.getDoctorSlots().get(doctorId);
        appointments.add(appointment);
        InMemroyDb.getDoctorSlots().put(doctorId,appointments);
    }

    private boolean isAppointmentOverlapping(Integer doctorId, LocalDateTime startTime) {
        List<String> availableAppointments = getAvailableAppointments(doctorId, startTime.toLocalDate());
        LocalDateTime endTime = startTime.plus(Duration.ofMinutes(DURATION));
        String formattedSlot = formatSlot(startTime.toLocalTime(), endTime.toLocalTime());

        return !availableAppointments.contains(formattedSlot);
    }

    private void validateDoctorAndPatientDate(Integer patientId, Integer doctorId) {
        if(Objects.isNull(InMemroyDb.getDoctorMap().getOrDefault(doctorId,null)) ||
                Objects.isNull(InMemroyDb.getPatientMap().getOrDefault(patientId,null))){
            throw  new RuntimeException("invalid doctor or patient");
        }
    }

    private List<String> getFilteredSlots(List<LocalTime[]> allSlots, List<Appointment> doctorSlots) {

        List<String> filteredSlots= new ArrayList<>();
        for(LocalTime[] slots:allSlots){
            boolean overlapSlot = false;
            for(Appointment doctorSlot:doctorSlots){
                LocalDateTime startTime = doctorSlot.getStartTime();
                LocalDateTime endTime = doctorSlot.getEndTime();
                if(slots[0].isBefore(endTime.toLocalTime()) &&
                        slots[1].isAfter(startTime.toLocalTime())){
                    overlapSlot=true;
                    break;
                }
            }
            if(!overlapSlot){
                filteredSlots.add(formatSlot(slots[0],slots[1]));
            }
        }
        return filteredSlots;
    }

    private String formatSlot(LocalTime start, LocalTime end) {
        return start+"-"+end;
    }

    private boolean isSameDay(LocalDateTime startTime, LocalDate date) {
        return startTime.toLocalDate().equals(date);
    }

    public String cancelAppointment(int appointmentId){
        Optional<Appointment> appointment = InMemroyDb.getDoctorSlots().values().stream()
                .flatMap(Collection::stream)
                .filter(each -> each.getId().equals(appointmentId))
                .findFirst();

        if(appointment.isPresent()){
            Appointment appointment1 = appointment.get();
            if(appointment1.getStatus()==Status.CANCELLED){
                throw  new RuntimeException("appointment already cancelled");
            }else{
                appointment1.setStatus(Status.CANCELLED);
            }
            return "appointment cancelled";
        }
        throw new RuntimeException("could not found appointment for id "+appointmentId);
    }

}
