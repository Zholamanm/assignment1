package test;

public class SalivaTest extends MedicalTestWithNotifications {
    @Override
    public void takeSample() {
        System.out.println("Берется образец слюни для анализа.");
        testCompleted();
    }
}
