package address.controller;


import address.addressbookrepointerfaces.AddressBookRepository;
import address.addressbookrepointerfaces.BuddyInfoRepository;
import address.models.AddressBook;
import address.models.BuddyInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;



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

    @MockBean
    private AddressBookRepository addressBookRepository;

    @MockBean
    private BuddyInfoRepository buddyInfoRepository;

    private String localhost = "http://localhost:8080";

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void checkAllAllowedMethods() throws Exception{
        Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow(localhost);
        HttpMethod[] supportedMethods
                = {HttpMethod.GET, HttpMethod.POST ,HttpMethod.DELETE};
        Assertions.assertTrue(optionsForAllow.containsAll(Arrays.asList(supportedMethods)));


    }
    @Test
    public void indexPageShouldShowTwoLinks() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Show Buddies")))
                .andExpect(content().string(containsString("Add Buddies")));
    }

    @Test
    public void addressListShowsTwoBuddies() throws Exception {
        AddressBook aBook = new AddressBook();
        aBook.addBuddy(new BuddyInfo("test1","test1","test1"));
        aBook.addBuddy(new BuddyInfo("test2","test2","test2"));

        List<AddressBook> mockStuff = Stream.of(aBook).collect(Collectors.toList());

        when(addressBookRepository.findAll()).thenReturn(mockStuff);

        this.mockMvc.perform(get("/addresslist")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("test1")))
                .andExpect(content().string(containsString("test2")));
    }


    @Test
    public void addBuddies() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        AddressBook aBook = new AddressBook();
        aBook.addBuddy(new BuddyInfo("test","test","test"));

        HttpEntity<AddressBook> request = new HttpEntity<>(aBook, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                localhost+"/addbuddies", request , String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteBuddy() throws Exception {
        AddressBook aBook = new AddressBook();
        aBook.addBuddy(new BuddyInfo("test1","test1","test1"));
        aBook.addBuddy(new BuddyInfo("test2","test2","test2"));

        List<AddressBook> mockStuff = Stream.of(aBook).collect(Collectors.toList());

        when(addressBookRepository.findAll()).thenReturn(mockStuff);
        ResponseEntity<String> response = restTemplate.postForEntity(
                localhost+"/deleteBuddies/1", "" , String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getDeletePageWith2Buddies() throws Exception {
        AddressBook aBook = new AddressBook();
        aBook.addBuddy(new BuddyInfo("test1","test1","test1"));
        aBook.addBuddy(new BuddyInfo("test2","test2","test2"));

        List<AddressBook> mockStuff = Stream.of(aBook).collect(Collectors.toList());

        when(addressBookRepository.findAll()).thenReturn(mockStuff);

        this.mockMvc.perform(get("/deleteBuddies")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("test1")))
                .andExpect(content().string(containsString("test2")));
    }

}
