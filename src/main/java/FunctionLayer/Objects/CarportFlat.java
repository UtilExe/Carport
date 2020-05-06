package FunctionLayer.Objects;

public class CarportFlat extends Carport {

    private boolean hasShed;
    private int shedWidth;
    private int shedLength;

    public CarportFlat(int carportLength, int carportWidth, int carportHeight, String roofMaterial, boolean hasShed, int
            shedWidth, int shedLength) {
        super(carportLength, carportWidth, carportHeight, roofMaterial);
        this.hasShed = hasShed;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }

    public boolean isHasShed() {
        return hasShed;
    }

    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }
}
