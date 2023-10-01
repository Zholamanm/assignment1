public class Clinic {
    private static Clinic instance;

    private Clinic() {}

    public static Clinic getInstance() {
        if (instance == null) {
            synchronized (Clinic.class) {
                if (instance == null) {
                    instance = new Clinic();
                }
            }
        }
        return instance;
    }
    public void makeDoctorAppointment(Doctor doctor) {
        doctor.makeAppointment();
    }
}