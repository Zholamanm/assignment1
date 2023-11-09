package test;

public class SalivaTest implements MedicalTest{
    private Subject notificationSubject;

    public SalivaTest (Subject notificationSubject) {
        this.notificationSubject = notificationSubject;
    }
    @Override
    public void takeSample() {
        System.out.println("Берется образец слюни для анализа.");
        notificationSubject.notifyObservers();
    }
}
