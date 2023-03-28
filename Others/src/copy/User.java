package copy;

public class User implements Cloneable {
    private int age;
    private String name;
    private Address address;

    public User() {
    }

    public User(int age, String name, Address address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public User clone() {
        try {
            User clone = (User) super.clone();
            clone.setAddress(address.clone());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
