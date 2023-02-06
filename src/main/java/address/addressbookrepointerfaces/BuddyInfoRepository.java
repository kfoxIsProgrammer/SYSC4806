package address.addressbookrepointerfaces;



import address.models.BuddyInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {
    BuddyInfo findByName(String name);

    BuddyInfo findBuddyInfoByAddress(String address);

    BuddyInfo findBuddyInfoByPhoneNumber(String phoneNumber);
}