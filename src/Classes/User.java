package Classes;

public class User {
    protected String nume;
    protected String prenume;
    protected String email;
    protected String telefon;
    protected String gender;
    private DateOfBirth birthday;
    protected int birthDay;
    protected int birthMonth;
    protected int birthYear;

    protected String username;
    protected int userType;

    public User(String nume, String prenume, String email, String telefon, String gender, DateOfBirth birthday, String username, int userType) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
        this.gender = gender;
        this.birthday = birthday;
        this.username = username;
        this.userType = userType;
    }

    public User() {
        this.nume ="";
        this.prenume = "";
        this.email = "";
        this.telefon = "";
        this.gender = "";
        this.birthday = null;
        this.username = "";
        this.userType = -1;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(DateOfBirth birthday) {
        this.birthday = birthday;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getGender() {
        return gender;
    }

    public DateOfBirth getBirthday() {
        return birthday;
    }

    public String getUsername() {
        return username;
    }

    public int getUserType() {
        return userType;
    }
}
