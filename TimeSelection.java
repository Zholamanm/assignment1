import java.util.Scanner;

public class TimeSelection implements SelectionStrategy {

    private String selectedTime;

    @Override
    public void execute(DoctorsDatabase database, Scanner scanner) {
        String[] timeslots = {
                "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"
        };
        System.out.println("Выберите желаемое время записи:");
        for (int i = 0; i < timeslots.length; i++) {
            System.out.println((i + 1) + " - " + timeslots[i]);
        }

        int choice;
        do {
            System.out.print("Введите номер выбранного времени: ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > timeslots.length);

        selectedTime = timeslots[choice - 1];
    }

    public String getSelectedTime() {
        return selectedTime;
    }
}
