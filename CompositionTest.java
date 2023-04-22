class Person {
    protected String name;
    protected Address address;

    public Person(String name, String street, String zipcode, String city) {
        this.name = name;
        this.address = new Address(street, zipcode, city);
    }

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String toString() {
        return name + " [" + address + "]";
    }

    public String formatForMailing() {
        return name + "\n" + address;
    }
}

class Address {

    private String street;
    private String zipcode;
    private String city;

    public Address(String street, String zipcode, String city){
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    //return a string containing the address
    public String toString() {
        return street + ", " + zipcode + " " + city ;
    }

    //return a string containing the address in mailing list format
    public String formatForMailing(){
        return street + "\n" + zipcode + " " + city;
    }

    //getters
    public String getStreet() {
        return street;
    }
    public String getZipcode() {
        return zipcode;
    }
    public String getCity() {
        return city;
    }

    //setters
    public void setStreet(String street) {
        this.street = street;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public void setCity(String city) {
        this.city = city;
    }

}

public class CompositionTest {

    public static void main(String[] args) {

        Person jan = new Person("Jan Kowalski", "Prosta 54","30111","Krakow");
        Person maria = new Person("Maria Kowalska", "Prosta 54","30111","Krakow");

        System.out.println(jan);
        System.out.println(maria.formatForMailing());

        Address podzamcze22 = new Address("Podzamcze 22","00033","Warszawa");
        Person adam = new Person("Adam Nowak", podzamcze22);
        Person rozalia = new Person("Rozalia Nowak", podzamcze22);
        System.out.println(adam);
        System.out.println(rozalia.formatForMailing());

    }
}