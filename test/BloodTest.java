package test;

public class BloodTest extends MedicalTestWithNotifications{
    @Override
    public void takeSample() {
        System.out.println("Берется образец крови для анализа.");
        testCompleted();
    }
}
