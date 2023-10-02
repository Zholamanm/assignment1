import db.DatabaseInitializer;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();
        DatabaseInitializer.insertInitialData();

        Clinic clinic = Clinic.getInstance();
        DoctorsDatabase database = new DoctorsDatabase();
        Scanner scanner = new Scanner(System.in);

        UserInterfaceManager uiManager = new UserInterfaceManager(database, scanner);
        uiManager.showMainMenu();

        scanner.close();
        database.closeConnection();
    }
}

