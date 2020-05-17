package FunctionLayer.Entities;

public class Carport {

    private int carportLength;
    private int carportWidth;
    private int carportHeight;
    private String roofMaterial;

    public Carport(int carportLength, int carportWidth, int carportHeight, String roofMaterial) {
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.carportHeight = carportHeight;
        this.roofMaterial = roofMaterial;
    }

    @Override
    public String toString() {
        return "Caport Længde: " + carportLength + ", Caport Bredde: " + carportWidth + ", Caport Højde: " + carportHeight + ", Tag materiale: " + roofMaterial;
    }
}

