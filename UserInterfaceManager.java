import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.time.LocalTime;

public class UserInterfaceManager {

    private DoctorsDatabase database;
    private Scanner scanner;

    public UserInterfaceManager(DoctorsDatabase database, Scanner scanner) {
        this.database = database;
        this.scanner = scanner;
    }

    public void showMainMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Информация о докторах");
        System.out.println("2. Получение справок");
        System.out.println("3. Запись на прием");
        System.out.print("Введите номер выбора: ");

        int mainChoice = scanner.nextInt();
        switch (mainChoice) {
            case 1:
                showDoctorInformation();
                break;
            case 2:
                System.out.println("Функционал получения справок еще не реализован.");
                break;
            case 3:
                bookAppointment();
                break;
            default:
                System.out.println("Неверный выбор.");
                break;
        }
    }

    private void showDoctorInformation() {
        List<Doctor> doctors = database.getAllDoctors();
        for (Doctor doctor : doctors) {
            System.out.println("Имя: " + doctor.getName());
            System.out.println("Специализация: " + doctor.getClass().getSimpleName());
            System.out.print("Используемое оборудование: ");
            doctor.getEquipment().use();
            System.out.println("---------------");
        }
    }

    private void bookAppointment() {
        System.out.print("Введите ваше имя: ");
        String patientName = scanner.nextLine();

        DoctorSelection doctorStrategy = new DoctorSelection();
        doctorStrategy.execute(database, scanner);
        scanner.nextLine();

        System.out.print("Введите желаемую дату и время (формат HH:mm dd/mm/yy): ");
        String userInput = scanner.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        Date appointmentDate = null;
        try {
            appointmentDate = sdf.parse(userInput);
        } catch (ParseException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
            return;
        }

        int doctorId = doctorStrategy.getSelectedDoctorId();

        Timestamp timestamp = new Timestamp(appointmentDate.getTime());

        if (database.isTimeAvailable(doctorId, timestamp)) {
            database.addAppointment(doctorId, timestamp, patientName);
            System.out.println("Вы успешно записались на " + userInput);
        } else {
            System.out.println("Выбранное время недоступно. Хотите выбрать другое время? (да/нет)");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("да")) {
                bookAppointment();
            } else {
                System.out.println("Запись отменена.");
            }
        }
    }

}
