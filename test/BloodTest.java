package test;

public class BloodTest implements MedicalTest{
    private Subject notificationSubject;

    public BloodTest (Subject notificationSubject) {
        this.notificationSubject = notificationSubject;
    }
    @Override
    public void takeSample() {
        System.out.println("Берется образец крови для анализа.");
        notificationSubject.notifyObservers();
    }
}
