package sample;

import ProductModel.Product;

import javax.imageio.IIOException;
import java.io.*;

public class AddProductDemo {
    public static void main(String[] args) {
        ObjectOutputStream product;
        try {
            product = new ObjectOutputStream(new FileOutputStream("D:\\CaseStudy_Module_2\\src\\ProductFile\\Product.txt"));
            Product products = new Product("123", "Bánh A", "22/12/2019", "10","50");
            product.writeObject(products);
            System.out.println("Run");
        } catch (IIOException | FileNotFoundException e){
            System.out.println("Có Vấn đề");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
