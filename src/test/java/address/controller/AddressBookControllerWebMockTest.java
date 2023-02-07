package address.controller;


import address.addressbookrepointerfaces.AddressBookRepository;
import address.addressbookrepointerfaces.BuddyInfoRepository;
import address.models.AddressBook;
import address.models.BuddyInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class AddressBookControllerWebMockTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    private String localhost = "http://localhost:8080";


    @Test
    public void indexPageShouldShowTwoLinks() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Show Buddies")))
                .andExpect(content().string(containsString("Add Buddies")));
    }

    @Test
    public void addressListShowsTwoBuddies() throws Exception {
        AddressBook aBook = new AddressBook();
        aBook.addBuddy(new BuddyInfo("test1Name","test1Address","test1PhoneNumber"));
        aBook.addBuddy(new BuddyInfo("test2","test2","test2"));

        addressBookRepository.save(aBook);

        this.mockMvc.perform(get("/addresslist")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("test1")))
                .andExpect(content().string(containsString("test2")));
    }



    @Test
    public void addBuddies() throws Exception {
        AddressBook aBook = new AddressBook();
        aBook.addBuddy(new BuddyInfo("test1Name","test1Address","test1PhoneNumber"));
        aBook.addBuddy(new BuddyInfo("test2","test2","test2"));


        mockMvc.perform( MockMvcRequestBuilders
                        .post("/addbuddies")
                        .content(asJsonString(aBook))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBuddy() throws Exception {
        AddressBook aBook = new AddressBook();
        aBook.addBuddy(new BuddyInfo("test1","test1","test1"));
        aBook.addBuddy(new BuddyInfo("test2","test2","test2"));
        aBook.addBuddy(new BuddyInfo("test3","test3","test3"));

        addressBookRepository.save(aBook);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/deleteBuddies/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getDeletePageWith2Buddies() throws Exception {
        AddressBook aBook = new AddressBook();
        aBook.addBuddy(new BuddyInfo("test1","test1","test1"));
        aBook.addBuddy(new BuddyInfo("test2","test2","test2"));

        addressBookRepository.save(aBook);

        mockMvc.perform(get("/deleteBuddies")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("test1")))
                .andExpect(content().string(containsString("test2")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
