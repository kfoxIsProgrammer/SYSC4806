package address.addressbookrepointerfaces;



import address.models.AddressBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {

    AddressBook findAddressBookById(long id);



}