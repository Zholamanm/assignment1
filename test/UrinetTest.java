package test;

public class UrinetTest implements MedicalTest {
    private Subject notificationSubject;

    public UrinetTest (Subject notificationSubject) {
        this.notificationSubject = notificationSubject;
    }
    @Override
    public void takeSample() {
        System.out.println("Берется образец мочи для анализа.");
        notificationSubject.notifyObservers();
    }
}
