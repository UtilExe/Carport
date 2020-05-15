package DBAccess;

import FunctionLayer.CarportHelper;
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
            stmt.execute("CREATE TABLE `material_list` LIKE `carport`.`material_list`");
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
        // Vi henter alle ordrer der ikke er godkendt.
        ArrayList<Order> orderList = OrderMapper.getOrdersThatAreNotApproved();

        assertThat(orderList, hasSize(3));
    }

    @Test (expected = AssertionError.class)
    public void testGetOrdersNegative() throws UniversalSampleException {
        // Vi henter alle ordrer der ikke er godkendt.
        ArrayList<Order> orderList = OrderMapper.getOrdersThatAreNotApproved();

        assertThat(orderList, hasSize(4));
    }

    @Test
    public void testApprove() throws UniversalSampleException {
        // Vi godkender den sidste odrer.
        OrderMapper.approve(4);
        // Så henter vi ordrerne igen og tjekker om den er blevet godkendt.
        ArrayList<Order> orderList = OrderMapper.getOrdersThatAreNotApproved();

        assertThat(orderList, hasSize(2));
    }

    @Test (expected = AssertionError.class)
    public void testApproveNegative() throws UniversalSampleException {
        // Vi godkender den sidste odrer.
        OrderMapper.approve(4);
        // Så henter vi ordrerne igen og tjekker om den er blevet godkendt.
        ArrayList<Order> orderList = OrderMapper.getOrdersThatAreNotApproved();

        assertThat(orderList, hasSize(3));
    }

    @Test
    public void testRemoveOrder() throws UniversalSampleException {
        // Vi fjerner den sidste odrer.
        OrderMapper.removeOrder(4);
        // Så henter vi ordrerne igen og tjekker om den er blevet fjernet.
        ArrayList<Order> orderList = OrderMapper.getOrdersThatAreNotApproved();

        assertThat(orderList, hasSize(2));
    }

    @Test (expected = AssertionError.class)
    public void testRemoveOrderNegative() throws UniversalSampleException {
        // Vi fjerner den sidste odrer.
        OrderMapper.removeOrder(4);
        // Så henter vi ordrerne igen og tjekker om den er blevet fjerner.
        ArrayList<Order> orderList = OrderMapper.getOrdersThatAreNotApproved();

        assertThat(orderList, hasSize(3));
    }

    @Test
    public void testaddCarportToCustOrder() throws UniversalSampleException {
        //Carport carport = new Carport(700, 300, 340, "Sjovt-tag");
        //Order order = new Order(0, carport, true, 200, 300, false, 30, 15000, false, 86756453);

        // Vi tilføjer en ordre:
        OrderMapper.addCarportToCustOrder(700, 300, 340, true, 200, 200, true, 30, "Sjovt-tag", 15000, 98722412);

        // Så henter vi ordrerne igen og tjekker om den er blevet tilføjet.
        ArrayList<Order> orderList = OrderMapper.getOrdersThatAreNotApproved();

        assertThat(orderList, hasSize(4));
    }

    @Test (expected = AssertionError.class)
    public void testaddCarportToCustOrderNegative() throws UniversalSampleException {
        // Vi tilføjer en ordre:
        OrderMapper.addCarportToCustOrder(700, 300, 340, true, 200, 200, true, 30, "Sjovt-tag", 15000, 98722412);

        // Så henter vi ordrerne igen og tjekker om den er blevet tilføjet.
        ArrayList<Order> orderList = OrderMapper.getOrdersThatAreNotApproved();

        assertThat(orderList, hasSize(3));
    }

    // TODO Lav nedenstående test, hvis overskud.
    /*
    @Test
    public void testGetHelper() throws UniversalSampleException {
        CarportHelper result = OrderMapper.getHelper(4);
        CarportHelper expected = new CarportHelper(720, 360, 340, 260, 325, 35);

        assertEquals(result, expected);
    }
     */

}
