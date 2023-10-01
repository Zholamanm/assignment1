import db.DatabaseInitializer;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();
        DatabaseInitializer.insertInitialData();

        Clinic clinic = Clinic.getInstance();
        DoctorsDatabase database = new DoctorsDatabase();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ваше имя: ");
        String patientName = scanner.nextLine();

        DoctorSelection doctorStrategy = new DoctorSelection();
        doctorStrategy.execute(database, scanner);

        TimeSelection timeStrategy = new TimeSelection();
        timeStrategy.execute(database, scanner);

        int doctorId = doctorStrategy.getSelectedDoctorId();
        String time = timeStrategy.getSelectedTime();
        if (database.isTimeAvailable(doctorId, time)) {
            database.addAppointment(doctorId, time, patientName);
            System.out.println("Вы успешно записались на " + time);
        } else {
            System.out.println("Выбранное время недоступно. Пожалуйста, попробуйте еще раз.");
        }

        scanner.close();
        database.closeConnection();
    }
}

