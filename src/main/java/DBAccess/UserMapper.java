package DBAccess;

import FunctionLayer.LoginSampleException;
import FunctionLayer.Entities.User;
import FunctionLayer.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserMapper {

    public static void createUser(User user) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO carport.users (name, email, password, mobilNr) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            ps.setString(1, user.getName());
            ps.setString( 2, user.getEmail());
            ps.setString( 3, user.getPassword());
            ps.setInt(4, user.getMobilNr());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            user.setDateCreated("create_time");
        } catch ( SQLException | ClassNotFoundException ex ) {

            // Lav evt. validering ift. Duplicate Entry som Nikolaj gjorde.

            if (ex.getMessage().contains("Communications link failure")) {
                Log.severe("login " + ex.getMessage());
                throw new LoginSampleException("Databasen er i øjeblikket nede. Kontakt IT");
            }

            Log.severe("Register " + ex.getMessage());
            throw new LoginSampleException( ex.getMessage() );
        }
    }

    public static User login(String mail, String pw ) throws LoginSampleException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT email, password FROM Users "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setString( 1, mail );
            ps.setString( 2, pw );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                User user = new User(email, password);
                return user;
            } else {
                Log.info("login " + "Could not validate user");
                throw new LoginSampleException( "Could not validate user" );
            }
        } catch ( ClassNotFoundException | SQLException ex ) {

            if (ex.getMessage().contains("Communications link failure")) {
                Log.severe("login " + ex.getMessage());
                throw new LoginSampleException("Databasen er i øjeblikket nede. Kontakt IT");
            }
            Log.severe("Login " + ex.getMessage());
            throw new LoginSampleException(ex.getMessage());
        }
    }

}
