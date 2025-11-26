package code.bookstore.models;

public class shippingtype {
    private String shippingtype;
    private double shippingprice;

    public shippingtype(){
        this.shippingtype = null;
        this.shippingprice = 0;
    }

    public shippingtype(String shippingtype, double shippingprice){
        this.shippingtype = shippingtype;
        this.shippingprice = shippingprice;
    }

    public void settype(String shippingtype){
        this.shippingtype = shippingtype;
    }

    public void setprice(double shippingprice){
        this.shippingprice = shippingprice;
    }

    public String getInfo(){
        return "ShippingType{" +
                "type:" + shippingtype + '\'' +
                ", price:" + shippingprice +
                "}";
    }
}
