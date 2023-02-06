package address.addressbookrepointerfaces;

import address.models.AddressBook;
import address.models.BuddyInfo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddressBookRepoTest {

    AddressBook aBook;
    BuddyInfo buddy = new BuddyInfo("n1", "a1", "p1");
    BuddyInfo buddy2 = new BuddyInfo("n2", "a2", "p2");
    BuddyInfo buddy3 = new BuddyInfo("n3", "a3", "p3");
    @Autowired
    private  AddressBookRepository addressBookRepository;


    @AfterAll
    public void reset(){
        addressBookRepository.deleteAll();
    }

    @BeforeAll
    public void init(){
        aBook = new AddressBook();
        aBook.addBuddy(buddy);
        aBook.addBuddy(buddy2);
        aBook.addBuddy(buddy3);
        addressBookRepository.save(aBook);
    }


    @Test
    void testSavingThreeBuddies() {
        List<AddressBook> list = (List<AddressBook>) addressBookRepository.findAll();
        List<BuddyInfo> buddies = list.get(0).getBuddyList();
        assertEquals(3, buddies.size());
    }
}
