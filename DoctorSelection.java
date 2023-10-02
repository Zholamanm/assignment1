import java.util.List;
import java.util.Scanner;

public class DoctorSelection implements SelectionStrategy {

    private String selectedSpeciality;
    private int selectedDoctorId;
    private String selectedDoctorName;
    private MedicalEquipment equipment;
    @Override
    public void execute(DoctorsDatabase database, Scanner scanner) {
        System.out.println("Выберите врача для записи:");
        System.out.println("1. Окулист");
        System.out.println("2. Терапевт");
        System.out.println("3. Хирург");
        System.out.print("Введите номер выбора: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> selectedSpeciality = "Окулист";
            case 2 -> selectedSpeciality = "Терапевт";
            case 3 -> selectedSpeciality = "Хирург";
            default -> {
                System.out.println("Неверный выбор.");
                return;
            }
        }

        List<String> doctors = database.getDoctorsBySpeciality(selectedSpeciality);
        System.out.println("Выберите врача:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) + ". " + doctors.get(i));
        }
        System.out.print("Введите номер выбора: ");

        int doctorChoice = scanner.nextInt();
        String selectedDoctor = doctors.get(doctorChoice - 1);
        selectedDoctorId = database.getDoctorIdByName(selectedDoctor);
        selectedDoctorName = selectedDoctor;
        if(selectedSpeciality == "Окулист"){
            Doctor oculist = new Oculist(selectedDoctorName, equipment);
        }
        if(selectedSpeciality == "Терапевт"){
            Doctor therapist = new Therapist(selectedDoctorName, equipment);
        }
        if(selectedSpeciality == "Хирург"){
            Doctor surgeon = new Surgeon(selectedDoctorName, equipment);
        }
        if (selectedDoctorId == -1) {
            System.out.println("Ошибка: Доктор не найден.");
            return;
        }
    }

    public int getSelectedDoctorId() {
        return selectedDoctorId;
    }
}
