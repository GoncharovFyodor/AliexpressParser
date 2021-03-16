package ru.goncharov.aliexpressparser.mapper;

import ru.goncharov.aliexpressparser.products.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.goncharov.aliexpressparser.reader.IReader;

import java.util.ArrayList;
import java.util.List;

public class JSONAliexpressMapper implements Mapper{
    private IReader reader;
    List<Product> products = new ArrayList<Product>();

    public JSONAliexpressMapper(IReader reader) {
        this.reader = reader;
    }

    public List<Product> makeListOfProductsFromString() {
        System.out.println(reader.getStringFromConnection());
        JSONObject obj = new JSONObject(reader.getStringFromConnection());
        JSONArray arr = obj.getJSONArray("gpsProductDetails");
        for (int i = 0; i < arr.length(); i++) {
            Product product = new Product();
            JSONObject Jsonobject=arr.getJSONObject(i);

            product.setProductId(Jsonobject.getLong("productId"));
            product.setSellerId(Jsonobject.getLong("sellerId"));

            if (Jsonobject.has("productImage")) {
                String productImage = Jsonobject.getString("productImage");
            /*aliexpress adds dimension at the end of the link so that we will have someUrl.jpg_350x350.jpg.
            In order to simplify, we remove all garbage and  add 3 to make url work again*/
                int end = productImage.lastIndexOf(".jpg") + 3;
                /*aliexpress likes to add // to url in the beginning so we start with the 3-th element to make url work*/
                productImage = productImage.substring(2, end);
                product.setProductImage(productImage);
            }
            if (Jsonobject.has("productDetailUrl")) {
                String productDetailUrl = Jsonobject.getString("productDetailUrl");
                int end = productDetailUrl.lastIndexOf("html") + 4;
                product.setProductDetailUrl(productDetailUrl.substring(2, end));
            }
            if (Jsonobject.has("productTitle")) {
                product.setProductTitle(Jsonobject.getString("productTitle"));
            }
            if (Jsonobject.has("minPrice")) {
                product.setMinPrice(Jsonobject.getString("minPrice"));
            }
            if (Jsonobject.has("maxPrice")) {
                product.setMaxPrice(Jsonobject.getString("maxPrice"));
            }
            if (Jsonobject.has("oriMinPrice")) {
                product.setOriMinPrice(Jsonobject.getString("oriMinPrice"));
            }
            if (Jsonobject.has("oriMaxPrice")) {
                product.setOriMaxPrice(Jsonobject.getString("oriMaxPrice"));
            }
            if (Jsonobject.has("discount")) {
                product.setDiscount(Jsonobject.getInt("discount"));
            }
            if (Jsonobject.has("promotionId")) {
                product.setPromotionId(Jsonobject.getLong("promotionId"));
            }
            product.setStartTime(Jsonobject.getLong("startTime"));

            if (Jsonobject.has("endTime")) {
                product.setEndTime(Jsonobject.getLong("endTime"));
            }
            if (Jsonobject.has("phase")) {
                product.setPhase(Jsonobject.getInt("phase"));
            }
            if (arr.getJSONObject(i).has("orders")) {
                product.setOrders(Jsonobject.getLong("orders"));
            }
            if (Jsonobject.has("shopUrl")) {
                String shopUrl = Jsonobject.getString("shopUrl");
                /*after ?t ali add garbage to url*/
                int end = shopUrl.lastIndexOf("?t");
                product.setShopUrl(shopUrl.substring(2, end));
            }
            if (Jsonobject.has("trace")) {
                product.setTrace(Jsonobject.getString("trace"));
            }
            if (Jsonobject.has("shopName")) {
                product.setShopName(Jsonobject.getString("shopName"));
            }

            if (Jsonobject.has("totalStock")) {
                product.setTotalStock(Jsonobject.getLong("totalStock"));
            }
            if (Jsonobject.has("stock")) {
                product.setStock(Jsonobject.getLong("stock"));
            }
            if (Jsonobject.has("soldout")) {
                product.setSoldout(Jsonobject.getBoolean("soldout"));
            }
            products.add(product);


        }
        return products;
    }
}
