package ProductModel;

import java.io.Serializable;

public class Product implements Serializable {
    private String id ;
    private String name;
    private String exp;
    private String price;
    private String amount;
    private String creatAmount;

    public Product(){
    };

    public Product(String id, String name, String exp, String price, String amount){
        this.id = id;
        this.name = name;
        this.exp = exp;
        this. price = price;
        this.amount = amount;
    }

//    public Product(String creatAmount) {
//        this.creatAmount = creatAmount;
//    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExp() {
        return exp;
    }

    public String getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreatAmount() {
        return creatAmount;
    }

    public void setCreatAmount(String creatAmount) {
        this.creatAmount = creatAmount;
    }

    //public void creatPrice(){
     //   return this.price * this.amount;
    //}


    @Override
    public String toString() {
        return "Product: " +
                "Mã sản phẩm =" + id +
                ", Tên sẩn phẩm = '" + name + '\'' +
                ", Ngày sản xuất = '" + exp + '\'' +
                ", Giá = " + price +
                ", Số lượng (trong 1 thùng) = " + amount +
                ", Số thùng = " + creatAmount +
                '.';
    }
}
