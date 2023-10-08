import doctors.Doctor;
import doctors.DoctorSelection;
import doctors.DoctorsDatabase;
import medications.DiscountedMedication;
import medications.IMedication;
import medications.Medication;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class UserInterfaceManager {

    private final DoctorsDatabase database;
    private final Scanner scanner;

    public UserInterfaceManager(DoctorsDatabase database, Scanner scanner) {
        this.database = database;
        this.scanner = scanner;
    }

    public void showMainMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Информация о докторах");
        System.out.println("2. Получение справок");
        System.out.println("3. Запись на прием");
        System.out.println("4. Поиск докторов которые сейчас свободны");
        System.out.println("5. Поиск медикаментов");
        System.out.print("Введите номер выбора: ");

        int mainChoice = scanner.nextInt();
        switch (mainChoice) {
            case 1 -> showDoctorInformation();
            case 2 -> System.out.println("Функционал получения справок еще не реализован.");
            case 3 -> bookAppointment();
            case 4 -> findDoctorAvailable();
            case 5 -> showMedInfo();
            default -> System.out.println("Неверный выбор.");
        }
    }

    private void showMedInfo() {
        Clinic clinic = Clinic.getInstance();
        IMedication aspirin = new Medication("Аспирин", "Болеутоляющее", 10.0);
        clinic.addMedication(aspirin);
        IMedication discountedMedication = new DiscountedMedication(aspirin, 0.1);
        clinic.addDiscountedMedication(discountedMedication);
        System.out.println("1. Все медикаменты");
        System.out.println("2. Акции и скидки");
        int mainChoice = scanner.nextInt();
        switch (mainChoice) {
            case 1 -> {
                for (IMedication medication : clinic.getAllMedications()) {
                    System.out.println(medication);
                }
            }
            case 2 -> {
                for (IMedication medication : clinic.getAllMedicationsDiscount()) {
                    System.out.println(medication);
                }
            }
            default -> System.out.println("Неверный выбор!");
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
        Date appointmentDate;
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
    public void findDoctorAvailable() {
        DoctorSelection doctorStrategy = new DoctorSelection();
        doctorStrategy.execute(database, scanner);
        scanner.nextLine();

        int doctorId = doctorStrategy.getSelectedDoctorId();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        String currentFormattedDate = sdf.format(new Date());

        Date appointmentTime;
        try {
            appointmentTime = sdf.parse(currentFormattedDate);
        } catch (ParseException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
            return;
        }

        Timestamp currentTime = new Timestamp(appointmentTime.getTime());
        Timestamp endTime = new Timestamp(appointmentTime.getTime() + 3600 * 1000);

        boolean isDoctorBusy = true;
        for (long time = currentTime.getTime(); time < endTime.getTime(); time += 60 * 1000) {
            Timestamp checkTime = new Timestamp(time);
            if (database.isTimeAvailable(doctorId, checkTime)) {
                isDoctorBusy = false;
            }
            else {
                isDoctorBusy = true;
                break;
            }
        }

        Doctor doctor = database.getDoctorById(doctorId);
        if (isDoctorBusy) {
            System.out.println("Доктор " + doctorStrategy.getSelectedDoctorName() + " " );
            doctor.applyTreatment();
        } else {
            System.out.println("Доктор " + doctorStrategy.getSelectedDoctorName() + " свободен в ближайший час.");
        }
    }


}
