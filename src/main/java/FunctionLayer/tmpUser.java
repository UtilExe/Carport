package FunctionLayer;

public class tmpUser {
    private String username;
    private String email;
    private String password;
    private int mobilNr;
    private String dateCreated;
    private int saldo;

    public tmpUser(String username, String email, String password, int mobilNr) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobilNr = mobilNr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setMobilNr(int mobilNr) {
        this.mobilNr = mobilNr;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
