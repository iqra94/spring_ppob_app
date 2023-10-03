package programmerzamannow.restful.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.restful.a.model.*;
import programmerzamannow.restful.a.service.ContactServiceF;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.*;
import programmerzamannow.restful.service.ContactService;

import java.util.List;

@RestController
public class ContactControllerF {

    @Autowired
    private ContactServiceF contactService;

    @PostMapping(
            path = "/api/contacts-f",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<ContactResponseF> create(User user, @RequestBody CreateContactRequestF request) {
        ContactResponseF contactResponse = contactService.create(user, request);
        return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
    }

    @GetMapping(
            path = "/api/contacts-g/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<ContactResponseF> get(User user, @PathVariable("contactId") String contactId) {
        ContactResponseF contactResponse = contactService.get(user, contactId);
        return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
    }

    @PutMapping(
            path = "/api/contacts-g/{contactId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<ContactResponseF> update(User user,
                                               @RequestBody UpdateContactRequestG request,
                                               @PathVariable("contactId") String contactId) {

        request.setId(contactId);

        ContactResponseF contactResponse = contactService.update(user, request);
        return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
    }

    @DeleteMapping(
            path = "/api/contacts-g/{contactId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<String> delete(User user, @PathVariable("contactId") String contactId) {
        contactService.delete(user, contactId);
        return WebResponseA.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/contacts-g",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<List<ContactResponseF>> search(User user,
                                                     @RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "email", required = false) String email,
                                                     @RequestParam(value = "phone", required = false) String phone,
                                                     @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                     @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        SearchContactRequestH request = SearchContactRequestH.builder()
                .page(page)
                .size(size)
                .name(name)
                .email(email)
                .phone(phone)
                .build();

        Page<ContactResponseF> contactResponses = contactService.search(user, request);
        return WebResponseA.<List<ContactResponseF>>builder()
                .data(contactResponses.getContent())
                .paging(PagingResponseH.builder()
                        .currentPage(contactResponses.getNumber())
                        .totalPage(contactResponses.getTotalPages())
                        .size(contactResponses.getSize())
                        .build())
                .build();
    }
}

/** sebelum 02:39:00 - Get Contact API
     @Autowired
     private ContactServiceF contactService;

     @PostMapping(
     path = "/api/contacts-f",
     consumes = MediaType.APPLICATION_JSON_VALUE,
     produces = MediaType.APPLICATION_JSON_VALUE
     )
     public WebResponseA<ContactResponseF> create(User user, @RequestBody CreateContactRequestF request) {
     ContactResponseF contactResponse = contactService.create(user, request);
     return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
     }
 */

/** sebelum 02:46:15 - Update Contact API
 @Autowired
 private ContactServiceF contactService;

 @PostMapping(
 path = "/api/contacts-f",
 consumes = MediaType.APPLICATION_JSON_VALUE,
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<ContactResponseF> create(User user, @RequestBody CreateContactRequestF request) {
 ContactResponseF contactResponse = contactService.create(user, request);
 return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
 }

 @GetMapping(
 path = "/api/contacts-g/{contactId}",
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<ContactResponseF> get(User user, @PathVariable("contactId") String contactId) {
 ContactResponseF contactResponse = contactService.get(user, contactId);
 return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
 }
 */

/** sebelum 02:53:38 - Remove Contact API
 @Autowired
 private ContactServiceF contactService;

 @PostMapping(
 path = "/api/contacts-f",
 consumes = MediaType.APPLICATION_JSON_VALUE,
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<ContactResponseF> create(User user, @RequestBody CreateContactRequestF request) {
 ContactResponseF contactResponse = contactService.create(user, request);
 return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
 }

 @GetMapping(
 path = "/api/contacts-g/{contactId}",
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<ContactResponseF> get(User user, @PathVariable("contactId") String contactId) {
 ContactResponseF contactResponse = contactService.get(user, contactId);
 return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
 }

 @PutMapping(
 path = "/api/contacts-g/{contactId}",
 consumes = MediaType.APPLICATION_JSON_VALUE,
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<ContactResponseF> update(User user,
 @RequestBody UpdateContactRequestG request,
 @PathVariable("contactId") String contactId) {

 request.setId(contactId);

 ContactResponseF contactResponse = contactService.update(user, request);
 return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
 }
 */

/** sebelum 02:57:09 - Search Contact API
 @Autowired
 private ContactServiceF contactService;

 @PostMapping(
 path = "/api/contacts-f",
 consumes = MediaType.APPLICATION_JSON_VALUE,
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<ContactResponseF> create(User user, @RequestBody CreateContactRequestF request) {
 ContactResponseF contactResponse = contactService.create(user, request);
 return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
 }

 @GetMapping(
 path = "/api/contacts-g/{contactId}",
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<ContactResponseF> get(User user, @PathVariable("contactId") String contactId) {
 ContactResponseF contactResponse = contactService.get(user, contactId);
 return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
 }

 @PutMapping(
 path = "/api/contacts-g/{contactId}",
 consumes = MediaType.APPLICATION_JSON_VALUE,
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<ContactResponseF> update(User user,
 @RequestBody UpdateContactRequestG request,
 @PathVariable("contactId") String contactId) {

 request.setId(contactId);

 ContactResponseF contactResponse = contactService.update(user, request);
 return WebResponseA.<ContactResponseF>builder().data(contactResponse).build();
 }

 @DeleteMapping(
 path = "/api/contacts-g/{contactId}",
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<String> delete(User user, @PathVariable("contactId") String contactId) {
 contactService.delete(user, contactId);
 return WebResponseA.<String>builder().data("OK").build();
 }
 */