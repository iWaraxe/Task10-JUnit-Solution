package shop;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static final double TAX = 0.2;
    private final String cartName;
    private List<RealItem> realItems;
    private List<VirtualItem> virtualItems;
    private double total;

    public Cart(String cartName) {
        this.cartName = cartName;
        realItems = new ArrayList<>();
        virtualItems = new ArrayList<>();
    }

    public String getCartName() {
        return cartName;
    }

    public void addRealItem(RealItem item) {
        realItems.add(item);
        total += item.getPrice() + item.getPrice()*TAX; // item.getPrice() * (1 + TAX)
    }

    public void deleteRealItem(RealItem item) {
        realItems.remove(item);
    }

    public void addVirtualItem(VirtualItem item) {
        virtualItems.add(item);
        total += item.getPrice() + item.getPrice()*TAX;
    }

    public void deleteVirtualItem(VirtualItem item) {
        virtualItems.remove(item);
    }

    public void showItems() {
        for (RealItem item : realItems) {
            System.out.println(item);
        }
        for (VirtualItem item : virtualItems) {
            System.out.println(item);
        }
    }

    public double getTotalPrice() {
        return total;
    }
}
