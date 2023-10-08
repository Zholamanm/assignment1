package equipment;



public class VisionChecker implements MedicalEquipment {
    @Override
    public void use() {
        System.out.println("Проверка зрения с помощью специализированного оборудования.");
    }
}
