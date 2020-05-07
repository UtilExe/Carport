package FunctionLayer.Objects;

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

    public int getCarportLength() {
        return carportLength;
    }

    public void setCarportLength(int carportLength) {
        this.carportLength = carportLength;
    }

    public int getCarportWidth() {
        return carportWidth;
    }

    public void setCarportWidth(int carportWidth) {
        this.carportWidth = carportWidth;
    }

    public int getCarportHeight() {
        return carportHeight;
    }

    public void setCarportHeight(int carportHeight) {
        this.carportHeight = carportHeight;
    }

    public String getRoofMaterial() {
        return roofMaterial;
    }

    public void setRoofMaterial(String roofMaterial) {
        this.roofMaterial = roofMaterial;
    }

    @Override
    public String toString() {
        return "Caport Længde: " + carportLength + ", Caport Bredde: " + carportWidth + ", Caport Højde: " + carportHeight + "Tag materiale: " + roofMaterial;
    }
}

