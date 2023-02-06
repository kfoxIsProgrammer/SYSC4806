package address.addressbookrepointerfaces;


import address.models.BuddyInfo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BuddyInfoRepoTest {
    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    BuddyInfo buddy = new BuddyInfo("n1", "a1", "p1");
    BuddyInfo buddy2 = new BuddyInfo("n2", "a2", "p2");
    BuddyInfo buddy3 = new BuddyInfo("n3", "a3", "p3");

    @BeforeEach
    public void init(){
        buddyInfoRepository.save(buddy);
        buddyInfoRepository.save(buddy2);
        buddyInfoRepository.save(buddy3);
    }
    @AfterAll
    public void deleteAll(){
        buddyInfoRepository.deleteAll();
    }

    @Test
    void testSavingThreeBuddies() {
        List<BuddyInfo> buddies = (List<BuddyInfo>) buddyInfoRepository.findAll();
        assertEquals(3, buddies.size());
    }

    @Test
    void testFindByName() {
        assertEquals( buddy2.getName(),buddyInfoRepository.findByName("n2").getName());
    }

    @Test
    void testFindByPhoneNumber() {
        assertEquals( buddy3.getPhoneNumber(),buddyInfoRepository.findBuddyInfoByPhoneNumber("p3").getPhoneNumber());
    }

    @Test
    void testFindByAddress() {
        assertEquals(buddy.getAddress(),buddyInfoRepository.findBuddyInfoByAddress("a1").getAddress());
    }

}
