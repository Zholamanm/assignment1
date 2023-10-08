package equipment;


public class SurgeryTools implements MedicalEquipment {
    @Override
    public void use() {
        System.out.println("Использование хирургических инструментов в операции.");
    }
}
