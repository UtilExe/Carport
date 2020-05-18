package FunctionLayer.Entities;

/**
 * @author Daniel, Emil, Jannich, Jimmy
 * User klassen består af en række variabler, som en bruger kræves at have.
 */

public class User {
    private String name;
    private String email;
    private String password;
    private int mobilNr;

    public User(String name, String email, String password, int mobilNr) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobilNr = mobilNr;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMobilNr() {
        return mobilNr;
    }
}
