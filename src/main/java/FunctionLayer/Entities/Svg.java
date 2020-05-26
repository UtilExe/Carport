package FunctionLayer.Entities;
import FunctionLayer.ValidationValues;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * Svg klassen håndterer tegning, som en skitse, af den bestilte Carport ud fra beregnede mål.
 * ToString metoden opretter et svg tag i HTML, hvor skitsen bliver genereret igennem CarportHelper klassen.
 */

public class Svg {
    private double width;
    private double height;
    private String viewbox;
    private int x;
    private int y;
    private StringBuilder svg = new StringBuilder();
    private final String HEADER_TEMPLATE = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"%s\" y=\"%s\" height=\"%s\" width=\"%s\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";
    private final String RECT_TEMPLATE = "<rect x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String BAND_TEMPLATE = "<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";
    private final String ARROW_LENGTH_TEMPLATE = " <defs>\n"
            + "    <marker id=\"beginArrow" + "\" \n"
            + "    	markerWidth=\"9\" markerHeight=\"9\" \n"
            + "    	refX=\"0\" refY=\"4\" \n"
            + "    	orient=\"auto\">\n"
            + "        <path d=\"M0,4 L8,0 L8,8 L0,4\" style=\"fill: #000000s;\" />\n"
            + "    </marker>\n"
            + "    <marker id=\"endArrow" + "\" \n"
            + "    	markerWidth=\"9\" markerHeight=\"9\" \n"
            + "    	refX=\"8\" refY=\"4\" \n"
            + "    	orient=\"auto\">\n"
            + "        <path d=\"M0,0 L8,4 L0,8 L0,0\" style=\"fill: #000000;\" />\n"
            + "    </marker>\n"
            + "</defs>\n"
            + "<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\""
            + "	style=\"stroke:#006600;\n"
            + "	marker-start: url(#beginArrow" + ");\n"
            + "   marker-end: url(#endArrow" + ");\"/>"
            + " ";
    private final String ARROW_WIDTH_TEMPLATE = " "
            + "<defs>\n"
            + "    <marker id=\"beginArrow" + "\" \n"
            + "    	markerWidth=\"9\" markerHeight=\"9\" \n"
            + "    	refX=\"0\" refY=\"4\" \n"
            + "    	orient=\"auto\">\n"
            + "        <path d=\"M0,4 L8,0 L8,8 L0,4\" style=\"fill: #000000s;\" />\n"
            + "    </marker>\n"
            + "    <marker id=\"endArrow" + "\" \n"
            + "    	markerWidth=\"9\" markerHeight=\"9\" \n"
            + "    	refX=\"8\" refY=\"4\" \n"
            + "    	orient=\"auto\">\n"
            + "        <path d=\"M0,0 L8,4 L0,8 L0,0\" style=\"fill: #000000;\" />\n"
            + "    </marker>\n"
            + "</defs>\n"
            + "<line x1=\"%s\" y1=\"%s\" x2=\"%s\" y2=\"%s\""
            + "	style=\"stroke:#006600;\n"
            + "	marker-start: url(#beginArrow" + ");\n"
            + "   marker-end: url(#endArrow" + ");\"/>"
            + " ";
    private final String TEXT_TEMPLATE = "<text style=\"text-anchor: middle\" transform=\"translate(%s,%s) rotate(-90)\">%s cm</text>\n" +
            "<text style=\"text-anchor: middle\" x=\"%s\" y=\"%s\">%s cm</text>";

    public Svg(double width, double height, String viewbox, int x, int y) {
        this.width = width;
        String strWidth = ValidationValues.fromDoubleToString(width);
        this.height = height;
        String strHeight = ValidationValues.fromDoubleToString(height);
        this.viewbox = viewbox;
        this.x = x;
        this.y = y;
        svg.append(String.format(HEADER_TEMPLATE, x, y, strHeight, strWidth, viewbox));
    }

    public void addRect(double x, double y, double width, double height) {
        String strX = ValidationValues.fromDoubleToString(x);
        String strY = ValidationValues.fromDoubleToString(y);
        String strWidth = ValidationValues.fromDoubleToString(width);
        String strHeight = ValidationValues.fromDoubleToString(height);
        svg.append(String.format(RECT_TEMPLATE, strX, strY, strWidth, strHeight));
    }

    public void addBand(double x1, double y1, double x2, double y2) {
        String strX1 = ValidationValues.fromDoubleToString(x1);
        String strY1 = ValidationValues.fromDoubleToString(y1);
        String strX2 = ValidationValues.fromDoubleToString(x2);
        String strY2 = ValidationValues.fromDoubleToString(y2);
        svg.append(String.format(BAND_TEMPLATE, strX1, strY1, strX2, strY2));
    }

    public void addArrowLength(double x1, double y1, double x2, double y2) {
        String strX1 = ValidationValues.fromDoubleToString(x1);
        String strY1 = ValidationValues.fromDoubleToString(y1);
        String strX2 = ValidationValues.fromDoubleToString(x2);
        String strY2 = ValidationValues.fromDoubleToString(y2);
        svg.append(String.format(ARROW_LENGTH_TEMPLATE, strX1, strY1, strX2, strY2));
    }

    public void addArrowWidth(double x1, double y1, double x2, double y2) {
        String strX1 = ValidationValues.fromDoubleToString(x1);
        String strY1 = ValidationValues.fromDoubleToString(y1);
        String strX2 = ValidationValues.fromDoubleToString(x2);
        String strY2 = ValidationValues.fromDoubleToString(y2);
        svg.append(String.format(ARROW_WIDTH_TEMPLATE, strX1, strY1, strX2, strY2));
    }

    public void addText(double x1, double y1, int width, double x2, double y2, int length) {
        String strX1 = ValidationValues.fromDoubleToString(x1);
        String strY1 = ValidationValues.fromDoubleToString(y1);
        String strX2 = ValidationValues.fromDoubleToString(x2);
        String strY2 = ValidationValues.fromDoubleToString(y2);
        svg.append(String.format(TEXT_TEMPLATE, strX1, strY1, width, strX2, strY2, length));
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

    @Override
    public String toString() {
        return svg.toString();
    }

}
