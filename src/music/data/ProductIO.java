package music.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import music.models.Product;

public class ProductIO {

    public static  List<Product> products;

    static {
        products = new ArrayList<>();
    }
    public static void loadProductsFromDB() {
    	 Connection conn = null;
         PreparedStatement ps = null;
         ResultSet rs = null;

         try {
             conn = ConnectionPool.getInstance().getConnection();

             String query = "SELECT * FROM products;";
             ps = conn.prepareStatement(query);

             rs = ps.executeQuery();


             while (rs.next()) {
            	 Product p = new Product(rs.getString(1), rs.getString(2), Double.parseDouble(rs.getString(3)));

                 products.add(p);
     
             }
            }
         catch (Exception e) {
             e.printStackTrace();

         }
         finally {
             DBUtil.closeResultSet(rs);
             DBUtil.closePreparedStatement(ps);
             DBUtil.freeConnection(conn);
         }
         
    }

    public static List<Product> getProducts() {
    	products =  new ArrayList<>();
    	loadProductsFromDB();
        return products;
    }

    public static Product getProduct(String productCode) {
    	getProducts();
        for (Product p : products) {
            if (productCode.equals(p.getCode())) {
                return p;
            }
        }

        return null;
    }

    public static void insertProduct(Product product) {
    	System.out.println("insert");
    	 Connection conn = null;
         PreparedStatement ps = null;
         ResultSet rs = null;

         try {
             conn = ConnectionPool.getInstance().getConnection();

             String query = "INSERT INTO products VALUES(?,?,?)";
             ps = conn.prepareStatement(query);
             ps.setString(1, product.getCode());
             ps.setString(2, product.getDescription());
             ps.setString(3, new Double(product.getPrice()).toString());

             ps.executeUpdate();

         }
         catch (Exception e) {
             e.printStackTrace();

         }
         finally {
             DBUtil.closeResultSet(rs);
             DBUtil.closePreparedStatement(ps);
             DBUtil.freeConnection(conn);
         }


    }

    public static void updateProduct(Product product) {
    	System.out.println("updates");
    	 Connection conn = null;
         PreparedStatement ps = null;
         ResultSet rs = null;

         try {
             conn = ConnectionPool.getInstance().getConnection();

             String query = "UPDATE PRODUCTS SET price = ?, description = ? WHERE CODE = ?";
             ps = conn.prepareStatement(query);
             ps.setString(1, new Double(product.getPrice()).toString());
             ps.setString(2, product.getDescription());
             ps.setString(3, product.getCode());
             

           ps.executeUpdate();

         }
         catch (Exception e) {
             e.printStackTrace();

         }
         finally {
             DBUtil.closeResultSet(rs);
             DBUtil.closePreparedStatement(ps);
             DBUtil.freeConnection(conn);
         }


    }

    public static void deleteProduct(String code) {
    	 Connection conn = null;
         PreparedStatement ps = null;
         ResultSet rs = null;

         try {
             conn = ConnectionPool.getInstance().getConnection();

             String query = "DELETE FROM products WHERE code = ?";
             ps = conn.prepareStatement(query);
             ps.setString(1, code);

             ps.executeUpdate();

         }
         catch (Exception e) {
             e.printStackTrace();

         }
         finally {
             DBUtil.closeResultSet(rs);
             DBUtil.closePreparedStatement(ps);
             DBUtil.freeConnection(conn);
         }

         loadProductsFromDB();
    }
}
