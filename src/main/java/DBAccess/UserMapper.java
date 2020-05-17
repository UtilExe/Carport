package DBAccess;

import FunctionLayer.Entities.User;
import FunctionLayer.Log;
import FunctionLayer.Operations;
import FunctionLayer.UniversalSampleException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserMapper {

    public static void createUser(User user) throws UniversalSampleException {
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
            String methodName = "createUser";

            if (ex.getMessage().contains("Duplicate entry")) {
                Log.finest(methodName + Operations.addQuotesAround(user.getEmail()) + ": Duplicate entry");
                throw new UniversalSampleException("En bruger med denne mail eksisterer allerede");
            }

            UniversalSampleException.exceptionIfsDB(ex.getMessage(), methodName);
            UniversalSampleException.exceptionIfLast(ex.getMessage(), methodName);
        }
    }

    public static User login(String mail, String pw ) throws UniversalSampleException {
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
                Log.info("login " + "Brugeren kunne ikke valideres");
                throw new UniversalSampleException( "Brugeren kunne ikke valideres" );
            }
        } catch ( ClassNotFoundException | SQLException ex ) {
            String methodName = "login";

            UniversalSampleException.exceptionIfsDB(ex.getMessage(), methodName);
            UniversalSampleException.exceptionIfLast(ex.getMessage(), methodName);
        }
        return null;
    }
}
