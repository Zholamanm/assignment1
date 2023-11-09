package test;

public class MedicalTestFactory {
    public MedicalTest getTest(String testType, Subject notificationSubject) {
        if(testType == null) {
         return null;
        }
        if(testType.equalsIgnoreCase("BLOOD")) {
            return new BloodTest(notificationSubject);
        } else if(testType.equalsIgnoreCase("URINE")) {
            return new UrinetTest(notificationSubject);
        } else if(testType.equalsIgnoreCase("SALIVA")) {
            return new SalivaTest(notificationSubject);
        }
        return null;
    }
}
