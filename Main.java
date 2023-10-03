import db.DatabaseInitializer;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();
        DatabaseInitializer.insertInitialData();

        DoctorsDatabase database = new DoctorsDatabase();
        Scanner scanner = new Scanner(System.in);

        UserInterfaceManager uiManager = new UserInterfaceManager(database, scanner);
        uiManager.showMainMenu();

        scanner.close();
        database.closeConnection();
    }
}

