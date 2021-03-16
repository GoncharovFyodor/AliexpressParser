package ru.goncharov.aliexpressparser.mapper;
import com.opencsv.bean.*;
import ru.goncharov.aliexpressparser.products.Product;

import java.io.FileWriter;

public class CSVMapper{
    private Mapper mapper;
    private static final String CSV_SEPARATOR = ",";
    public void writeToCSV(){
        // name of generated csv
        final String CSV_LOCATION = "products.csv";
        try {

            // Creating writer class to generate .csv file
            FileWriter writer = new
                    FileWriter(CSV_LOCATION);
            // Create Mapping Strategy to arrange the column name in order
            ColumnPositionMappingStrategy mappingStrategy=
                    new ColumnPositionMappingStrategy();
            mappingStrategy.setType(Product.class);

            // Arrange column name as provided in below array.
            String[] columns = new String[]
                    { "productId", "sellerId", "sellerId", "productDetailUrl",
                            "productTitle", "minPrice", "maxPrice", "oriMinPrice", "oriMaxPrice", "discount",
                            "promotionId", "startTime", "endTime", "phase", "orders", "shopUrl", "trace","shopName", "totalStock",
                            "stock", "soldout"};
            mappingStrategy.setColumnMapping(columns);

            // Creating StatefulBeanToCsv object
            StatefulBeanToCsvBuilder<Product> builder=
                    new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter =
                    builder.withMappingStrategy(mappingStrategy).build();

            // Write list to StatefulBeanToCsv object
            beanWriter.write(mapper.makeListOfProductsFromString());

            // closing the writer object
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public CSVMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
