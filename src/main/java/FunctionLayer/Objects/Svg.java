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
    private final String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%s\" width=\"%s\" style=\"stroke:#000000; fill: #ffffff\" />";

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

    public void addRect(int x, int y, double height, double width){
        String strHeight = ValidationValues.fromDoubleToString(height);
        String strWidth = ValidationValues.fromDoubleToString(width);
        svg.append(String.format(rectTemplate, x, y, strHeight, strWidth));
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
        return svg.toString() + "</svg>" ;
    }

}
