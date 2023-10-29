package test;

public class UrinetTest extends MedicalTestWithNotifications {
    @Override
    public void takeSample() {
        System.out.println("Берется образец мочи для анализа.");
        testCompleted();
    }
}
