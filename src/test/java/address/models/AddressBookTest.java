package address.models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookTest {

    public static AddressBook aBook;
    public static BuddyInfo buddy1;
    public static BuddyInfo buddy2;

    @BeforeEach
    public  void init(){
        aBook = new AddressBook();
        aBook.setId(1);
        buddy1 = new BuddyInfo();
        buddy2 = new BuddyInfo();

        buddy1.setName("joe");
        buddy1.setPhoneNumber("613");
        buddy1.setAddress("address1");
        buddy1.setId(1);


        buddy2.setName("blow");
        buddy2.setPhoneNumber("614");
        buddy2.setAddress("address2");
        buddy2.setId(2);
    }

    @Test
    public void testSetBuddyList() {
        ArrayList<BuddyInfo>  list = new ArrayList<>();
        list.add(new BuddyInfo("anme", "stuff", "otherStuff"));

        aBook.setBuddyList(list);
        assertEquals(aBook.getBuddyList().get(0).getName(), list.get(0).getName());
    }
    @Test
    public void testGetBuddyList() {
        aBook.addBuddy(buddy1);
        assertEquals(aBook.getBuddyList().get(0).getName(), buddy1.getName());
    }
    @Test
    public void testGetSize(){
        aBook.addBuddy(buddy1);
        assertEquals(aBook.getSize(),1);
    }
    @Test
    public void testAddBuddy() {
        assertEquals(aBook.getSize(), 0);
        aBook.addBuddy(buddy1);
        assertEquals(aBook.getSize(), 1);
        aBook.addBuddy(buddy2);
        assertEquals(aBook.getSize(),2);
    }

    @Test
    public void testDeleteBuddy() {
        assertEquals(aBook.getSize(), 0);
        aBook.addBuddy(buddy1);
        assertEquals(aBook.getSize(), 1);
        aBook.deleteBuddy(buddy1);
        assertEquals(aBook.getSize(),0);
    }

    @Test
    public void testToStringTest(){
        assertEquals(aBook.toString(), "This addressbook has: " +"0"+ " buddies\n");
        aBook.addBuddy(buddy1);
        assertEquals(aBook.toString(), "This addressbook has: " +"1"+ " buddies\njoe address1 613\n");
    }
}