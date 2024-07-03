package parser;

import com.google.gson.Gson;
import shop.Cart;

import java.io.*;

public class JsonParser implements Parser {

    private final Gson gson;

    public JsonParser() {
        gson = new Gson();
    }

    public void writeToFile(Cart cart) {
        try (FileWriter writer = new FileWriter("src/main/resources/" + cart.getCartName() + ".json")) {
            writer.write(gson.toJson(cart));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cart readFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return gson.fromJson(reader.readLine(), Cart.class);
        } catch (FileNotFoundException ex) {
            throw new NoSuchFileException(String.format("File %s.json not found!", file), ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
