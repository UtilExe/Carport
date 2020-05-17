package FunctionLayer.Entities;

public class CarportPitch extends Carport {

    private boolean hasShed;
    private int shedWidth;
    private int shedLength;
    private boolean hasPitch;
    private int roofPitch;

    public CarportPitch(int carportLength, int carportWidth, int carportHeight, String roofMaterial, boolean hasShed,
                        int shedWidth, int shedLength, boolean hasPitch, int roofPitch) {
        super(carportLength, carportWidth, carportHeight, roofMaterial);
        this.hasShed = hasShed;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.hasPitch = hasPitch;
        this.roofPitch = roofPitch;
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

    public int getRoofPitch() {
        return roofPitch;
    }

    public void setRoofPitch(int roofPitch) {
        this.roofPitch = roofPitch;
    }
}

