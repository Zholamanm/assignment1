package doctors;

import doctors.DoctorsDatabase;

import java.util.Scanner;

public interface SelectionStrategy {
    void execute(DoctorsDatabase database, Scanner scanner);
}
