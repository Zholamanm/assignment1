package test;

public class MedicalTestFactory {
    public MedicalTest getTest(String testType) {
        if(testType == null) {
         return null;
        }
        if(testType.equalsIgnoreCase("BLOOD")) {
            return new BloodTest();
        } else if(testType.equalsIgnoreCase("URINE")) {
            return new UrinetTest();
        } else if(testType.equalsIgnoreCase("SALIVA")) {
            return new SalivaTest();
        }
        return null;
    }
}
