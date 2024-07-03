package parser;

import shop.Cart;

import java.io.File;

public interface Parser {

    void writeToFile(Cart cart);
    Cart readFromFile(File file);
}
