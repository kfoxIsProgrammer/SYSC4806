package address.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BuddyInfo  {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    /**
     * Name of buddy
     */
    private String name;
    /**
     * Address of buddy
     */
    private String address;
    /**
     * Phone number of buddy
     */
    private String phoneNumber;


    /**
     * 3 arg constructor
     * @param name name of buddy
     * @param address address of buddy
     * @param phoneNumber phone number of buddy
     */
    public BuddyInfo(String name, String address, String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * No arg constructor
     */
    public BuddyInfo(){

    }


    /**
     * Public accessor for name of the buddy
     * @return the name of the buddy
     */
    public String getName() {
        return name;
    }

    /**
     * Public setter for name field
     * @param name string: new name of the buddy to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Set the id of the buddyInfo
     * @param id the id of the info
     */
    public void setId(long id) {this.id = id;}

    /**
     * Getter of the id
     * @return the id of the buddyInfo
     */
    public long getId() {
        return this.id;
    }
    /**
     * Public accessor for address of the buddy
     * @return the address of the buddy
     */public String getAddress() {
        return address;
    }


    /**
     * Public setter for address field
     * @param address string: new address of the buddy to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Public accessor for phone number of the buddy
     * @return the phone number of the buddy
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Public setter for phone number field
     * @param phoneNumber string: new phone number of the buddy to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * To string to print out the name information of the buddy
     */
    @Override
    public String toString() {
        return (this.name +" "+this.address+" "+this.phoneNumber);
    }
}
