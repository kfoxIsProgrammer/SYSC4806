package address.controllers;

import address.addressbookrepointerfaces.AddressBookRepository;
import address.addressbookrepointerfaces.BuddyInfoRepository;
import address.models.AddressBook;
import address.models.BuddyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddressBookController {
    public final AddressBookRepository repository;
    @Autowired
    public final BuddyInfoRepository buddyRepo;

    public AddressBookController(AddressBookRepository repository, BuddyInfoRepository buddyRepo) {
        this.repository = repository;
        this.buddyRepo = buddyRepo;
    }

    @RequestMapping(value = "/")
    public String getIndex(){
        return "index";
    }

    @RequestMapping(value = "/addbuddies")
    public String getBuddyForm(@RequestParam("num") int num, Model model){
        AddressBook book = new AddressBook();
        for(int i =0; i<num;i++){
            book.addBuddy(new BuddyInfo());
        }
        model.addAttribute("addressBook", book);
        return "buddyform";
    }


    @RequestMapping(value = "/addresslist")
    public String getAddresses(Model model){
        List<AddressBook> books = (List<AddressBook>) repository.findAll();
        if(books.size()==0){
            model.addAttribute("buddies", new AddressBook().getBuddyList());
        }
        else {
            model.addAttribute("buddies", books.get(0).getBuddyList());
        }
       return "addresslist";
    }


    @PostMapping("/addbuddies")
    public String addBuddy(@ModelAttribute("addressbook") AddressBook addressBook, Model model){
        AddressBook currentBook;
        if(repository.count() == 0){
            currentBook = addressBook;
            repository.save(currentBook);
        }else{
            currentBook = ((List<AddressBook>) repository.findAll()).get(0);
            for(BuddyInfo buddy: addressBook.getBuddyList()){
                currentBook.addBuddy(buddy);
            }
            repository.save(currentBook);
        }
        model.addAttribute("buddies", currentBook.getBuddyList());
        return "addresslist";
    }

    @GetMapping("/deleteBuddies")
    public String getDeleteForm(Model model){
        AddressBook aBook = ((List<AddressBook>) repository.findAll()).get(0);
        model.addAttribute("addressBook", aBook);
        return "deleteBuddiesForm";
    }

    @PostMapping(value="/deleteBuddies/{id}")
    public String getDeleteForm(@PathVariable long id, Model model){
        AddressBook aBook = ((List<AddressBook>) repository.findAll()).get(0);
        if (buddyRepo.existsById(id)) {
            aBook.getBuddyList().remove(buddyRepo.findById(id).get());
            System.out.println(buddyRepo.findById(id).get());
            repository.save(aBook);
        }
        model.addAttribute("buddies", aBook.getBuddyList());
        return "addresslist";
    }
}
