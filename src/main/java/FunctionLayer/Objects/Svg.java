package FunctionLayer.Objects;
import FunctionLayer.ValidationValues;
import com.sun.javafx.binding.StringFormatter;

import javax.validation.Valid;
import javax.validation.Validation;

public class Svg {
    private double width;
    private double height;
    private String viewbox;
    private int x;
    private int y;
    private StringBuilder svg = new StringBuilder();
    private final String headerTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" height=\"%s\" width=\"%s\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";
    private final String rectTemplate = "<rect x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String bandTemplate = "<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";
    private final String testTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" height=100 viewBox=\"0, 0, 855, 690\" preserveAspectRatio=\"xMinYMin\"> <defs> <marker id=\"beginArrow\" " +
            "markerWidth=12 markerHeight=12 refX=0 refY=6 orient=auto> <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" /> </marker> " +
            "<marker id=\"endArrow\" \" +\n" +
            "            \"markerWidth=12 markerHeight=12 refX=12 refY=6 orient=auto> <path d=\"M0,6 L12,6 L0,12 L0,0\" style=\"fill: #000000;\" /> </marker> </defs>" +
            "<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:#000000; marker-start: url(#beginArrow); marker-end: url(#endArrow); />";

    // private final String testTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";

    public Svg(double width, double height, String viewbox, int x, int y) {
        this.width = width;
        String strWidth = ValidationValues.fromDoubleToString(width);
        this.height = height;
        String strHeight = ValidationValues.fromDoubleToString(height);
        this.viewbox = viewbox;
        this.x = x;
        this.y = y;
        svg.append(String.format(headerTemplate, strHeight, strWidth, viewbox));
    }

    public void addRect(double x, double y, double width, double height){
        String strX = ValidationValues.fromDoubleToString(x);
        String strY = ValidationValues.fromDoubleToString(y);
        String strWidth = ValidationValues.fromDoubleToString(width);
        String strHeight = ValidationValues.fromDoubleToString(height);
        svg.append(String.format(rectTemplate, strX, strY, strWidth, strHeight));
    }

    public void addBand(double x1, double y1, double x2, double y2) {
        String strX1 = ValidationValues.fromDoubleToString(x1);
        String strY1 = ValidationValues.fromDoubleToString(y1);
        String strX2 = ValidationValues.fromDoubleToString(x2);
        String strY2 = ValidationValues.fromDoubleToString(y2);
        svg.append(String.format(bandTemplate, strX1, strY1, strX2, strY2));
    }

    public void addTest(double x1, double y1, double x2, double y2) {
        String strX1 = ValidationValues.fromDoubleToString(x1);
        String strY1 = ValidationValues.fromDoubleToString(y1);
        String strX2 = ValidationValues.fromDoubleToString(x2);
        String strY2 = ValidationValues.fromDoubleToString(y2);
        svg.append(String.format(testTemplate, strX1, strY1, strX2, strY2));
    }



    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getViewbox() {
        return viewbox;
    }

    public void setViewbox(String viewbox) {
        this.viewbox = viewbox;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return svg.toString();
    }

}
