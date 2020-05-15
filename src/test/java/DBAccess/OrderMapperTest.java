package DBAccess;

import FunctionLayer.Entities.Carport;
import FunctionLayer.Entities.Order;
import FunctionLayer.UniversalSampleException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class OrderMapperTest {

    private static Connection testConnection;
    private static String USER = DBLogin.username;
    private static String USERPW = DBLogin.password;
    private static String DBNAME = "carportTest?serverTimezone=CET&useSSL=false";
    private static String HOST = "localhost";

    @BeforeClass
    public static void setUp() {
        try {
            // awoid making a new connection for each test
            if (testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.cj.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test
                Connector.setConnection(testConnection);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    @Before
    public void beforeEachTest() {
        try (Statement stmt = testConnection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS `cust_order`");
            stmt.execute("CREATE TABLE `cust_order` LIKE `carport`.`cust_order`");
            stmt.execute("INSERT INTO `cust_order` VALUES " +
                    "(1, 630, 330, 340, false, 0, 0, false, 0, 'Plasttrapezplader', 8049, false, 12345678), " +
                    "(2, 540, 360, 320, true, 290, 265, false, 0, 'Plasttrapezplader', 11217, false, 43243546), " +
                    "(3, 540, 420, 360, false, 0, 0, true, 30, 'Eternittag B7 - Grå', 11943, true, 74253647), " +
                    "(4, 720, 360, 340, true, 260, 325, true, 35, 'Eternittag B6 - Mokka (brun)', 16342, false, 06948573);");
        } catch (SQLException ex) {
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }


    @Test
    public void testGetOrders() throws UniversalSampleException {
        //Carport carport = new Carport(700, 300, 340, "Sjovt-tag");
        //Order order = new Order(0, carport, true, 200, 300, false, 30, 15000, false, 86756453);
        ArrayList<Order> orderList = OrderMapper.getOrders();

        assertThat(orderList, hasSize(4));

    }


}
