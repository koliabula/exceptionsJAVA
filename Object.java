import java.util.Date;

public class Object {


    
    public String name;
    public String surname;
    public String patronymic;
    public Date dateOfbirth;
    public int phoneNumber;    
    public String gender;
    
   
    public Object(String name, String surname, String patronymic, Date dateOfbirth, int phoneNumber, String gender){

        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfbirth = dateOfbirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getDateOfbirth() {
        return dateOfbirth;
    }

    public int getPhoneNumber(int i) {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    

}
