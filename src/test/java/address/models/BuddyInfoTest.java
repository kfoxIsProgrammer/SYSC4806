package address.models;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BuddyInfoTest {

    public static BuddyInfo buddy1, buddy2, buddy3;
    @BeforeAll
    public static void init(){
        buddy1 = new BuddyInfo();
        buddy2 = new BuddyInfo("blow", "address2", "613");
        buddy3 = new BuddyInfo("Kevin", "Address3", "613");
        buddy3.setId(1);
        buddy2.setId(2);

    }
    @Test
    public void testGetName() {
        assertEquals(buddy2.getName(), "blow");
    }

    @Test
    public void testSetName() {
        assertEquals(buddy1.getName(), null);
        buddy1.setName("joe");
        assertEquals( buddy1.getName(), "joe");
    }

    @Test
    public void testGetAddress() {
        assertEquals(buddy2.getAddress(), "address2");
    }

    @Test
    public void testSetAddress() {
        assertEquals(buddy1.getAddress(), null);
        buddy1.setAddress("address1");
        assertEquals( buddy1.getAddress(), "address1");
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals(buddy2.getPhoneNumber(),"613");

    }

    @Test
    public void testSetPhoneNumber() {
        assertEquals(buddy1.getPhoneNumber(), null);
        buddy1.setPhoneNumber("614");
        assertEquals( buddy1.getPhoneNumber(), "614");
    }

    @Test
    public void testToStringTest(){
        assertEquals(buddy2.toString(), "blow" +" "+"address2"+" "+"613");
    }

}