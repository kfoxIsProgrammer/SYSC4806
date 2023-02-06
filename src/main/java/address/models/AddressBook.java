package address.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
/**
 * AddressBook class that's used to hold a list of buddies contact information
 */
@Entity
public class AddressBook {

    /**
     * Primary key for database
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;



    /**
     * List to hold the buddies
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BuddyInfo> buddyList = new ArrayList<>();


    public AddressBook(){

    }
    public AddressBook(List<BuddyInfo> buddies){
        this.buddyList = buddies;
    }
    /**
     * setter for Id
     * @param id
     */
    public void setId(long id){
        this.id = id;
    }

    /**
     * getter for Id
     * @return integer Id
     */
    public long getId(){
        return this.id;
    }
    /**
     * add a new buddy contact into the buddyList
      * @param newBuddy BuddyInfo buddy to add
     */
    public void addBuddy(BuddyInfo newBuddy) {
        buddyList.add(newBuddy);
    }

    /**
     * delete a buddy contact from buddyList
     * @param delBuddy The BuddyInfo to delete
     */
    public void deleteBuddy(BuddyInfo delBuddy) {
        buddyList.remove(delBuddy);
    }

    /**
     * public access to get the size of the buddyList
     * @return buddyList size
     */
    public int getSize() {
        return buddyList.size();
    }


    /**
     * Get the buddy from buddyList based
     * @return list of BuddyInfo buddyList
     */
    public List<BuddyInfo> getBuddyList() {
        return buddyList;
    }

    /**
     * Setter for BuddyList
     * @param list
     */
    public void setBuddyList(List<BuddyInfo> list){
        this.buddyList = list;
    }

    /**
     * To String to print addressbook
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("This addressbook has: " + buddyList.size() + " buddies\n");
        for (int i = 0; i < buddyList.size(); i++) {
            sb.append(buddyList.get(i) + "\n");
        }
        return sb.toString();
    }


    /**
     * Main function to test outputs
      * @param args
     */
    public static void main(String[] args) {
        AddressBook eBook = new AddressBook();

        System.out.println(eBook);//Empty

        eBook.addBuddy(new BuddyInfo("Darth Vader", "Tatooine", "613-555-5555"));
        eBook.addBuddy(new BuddyInfo("Luke Skywalker", "Tatooine", "613-555-5555"));
        eBook.addBuddy(new BuddyInfo("JarJar", "Naboo", "613-555-5555"));
        eBook.addBuddy(new BuddyInfo("joe","address1","613"));

        System.out.println(eBook);//4 entries
    }


}
