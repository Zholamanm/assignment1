import java.util.ArrayList;
import java.util.List;

public class Clinic {
    private static Clinic instance;
    private List<Doctor> doctors = new ArrayList<>();

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
}