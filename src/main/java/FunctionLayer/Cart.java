package FunctionLayer;

import FunctionLayer.Objects.Carport;

import java.util.ArrayList;

public class Cart {
    public ArrayList<Carport> cart = new ArrayList<>();

    public Cart(ArrayList<Carport> cart) {
        this.cart = cart;
    }

    public void emptyCart(ArrayList<Carport> cart) {
        cart.clear();
    }

    public ArrayList<Carport> addToCart(ArrayList<Carport> cart, Carport carport) {
        cart.add(carport);
        return cart;
    }

    public ArrayList<Carport> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Carport> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        String carpLength = "";
        for (Carport carport : cart) {
            carpLength = String.valueOf(carport.getCarportLength());
        }
        return "Carport Længde: " + carpLength;
    }
}
