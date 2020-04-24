package FunctionLayer;

public class Carport {

    private int carportLength;
    private int carportWidth;
    private int carportHeight;
    private boolean hasShed;
    private int shedWidth;
    private int shedLength;
    private boolean roofIsFlat;
    private int roofPitch;
    private String roofMaterial;

    public Carport(int carportLength, int carportWidth, int carportHeight, boolean hasShed, int shedWidth,
                   int shedLength, boolean roofIsFlat, int roofPitch, String roofMaterial) {
        this.carportLength = carportLength;
        this.carportWidth = carportWidth;
        this.carportHeight = carportHeight;
        this.hasShed = hasShed;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.roofIsFlat = roofIsFlat;
        this.roofPitch = roofPitch;
        this.roofMaterial = roofMaterial;
    }

    public int getCarportLength() {
        return carportLength;
    }

    public int getCarportWidth() {
        return carportWidth;
    }

    public int getCarportHeight() { return carportHeight; }

    public boolean isHasShed() {
        return hasShed;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public boolean isRoofIsFlat() {
        return roofIsFlat;
    }

    public int getRoofPitch() {
        return roofPitch;
    }

    public String getRoofMaterial() {
        return roofMaterial;
    }

    public int calcPrice(Carport carport) {
        int result = 0;
        return result;
    }



}
