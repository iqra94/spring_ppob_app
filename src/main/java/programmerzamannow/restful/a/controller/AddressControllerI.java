package programmerzamannow.restful.a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.restful.a.model.AddressResponseI;
import programmerzamannow.restful.a.model.CreateAddressRequestI;
import programmerzamannow.restful.a.model.UpdateAddressRequestJ;
import programmerzamannow.restful.a.model.WebResponseA;
import programmerzamannow.restful.a.service.AddressServiceI;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.AddressResponse;
import programmerzamannow.restful.model.CreateAddressRequest;
import programmerzamannow.restful.model.UpdateAddressRequest;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.service.AddressService;

import java.util.List;

@RestController
public class AddressControllerI {

    @Autowired
    private AddressServiceI addressService;

    @PostMapping(
            path = "/api/contacts-a/{contactId}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<AddressResponseI> create(User user,
                                                 @RequestBody CreateAddressRequestI request,
                                                 @PathVariable("contactId") String contactId) {
        request.setContactId(contactId);
        AddressResponseI addressResponse = addressService.create(user, request);
        return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
    }

    @GetMapping(
            path = "/api/contacts-a/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<AddressResponseI> get(User user,
                                            @PathVariable("contactId") String contactId,
                                            @PathVariable("addressId") String addressId) {
        AddressResponseI addressResponse = addressService.get(user, contactId, addressId);
        return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
    }

    @PutMapping(
            path = "/api/contacts-a/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<AddressResponseI> update(User user,
                                               @RequestBody UpdateAddressRequestJ request,
                                               @PathVariable("contactId") String contactId,
                                               @PathVariable("addressId") String addressId) {
        request.setContactId(contactId);
        request.setAddressId(addressId);

        AddressResponseI addressResponse = addressService.update(user, request);
        return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
    }

    @DeleteMapping(
            path = "/api/contacts-a/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<String> remove(User user,
                                      @PathVariable("contactId") String contactId,
                                      @PathVariable("addressId") String addressId) {
        addressService.remove(user, contactId, addressId);
        return WebResponseA.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/contacts-a/{contactId}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponseA<List<AddressResponseI>> list(User user,
                                                   @PathVariable("contactId") String contactId) {
        List<AddressResponseI> addressResponses = addressService.list(user, contactId);
        return WebResponseA.<List<AddressResponseI>>builder().data(addressResponses).build();
    }
}

/** sebelum 03:32:10 - Get Address API
 @Autowired
 private AddressServiceI addressService;

 @PostMapping(
 path = "/api/contacts/{contactId}/addresses-i",
 produces = MediaType.APPLICATION_JSON_VALUE,
 consumes = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<AddressResponseI> create(User user,
 @RequestBody CreateAddressRequestI request,
 @PathVariable("contactId") String contactId) {
 request.setContactId(contactId);
 AddressResponseI addressResponse = addressService.create(user, request);
 return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
 }
 */

/** sebelum 03:39:00 - Update Address API
 @Autowired
 private AddressServiceI addressService;

 @PostMapping(
 path = "/api/contacts-a/{contactId}/addresses",
 produces = MediaType.APPLICATION_JSON_VALUE,
 consumes = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<AddressResponseI> create(User user,
 @RequestBody CreateAddressRequestI request,
 @PathVariable("contactId") String contactId) {
 request.setContactId(contactId);
 AddressResponseI addressResponse = addressService.create(user, request);
 return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
 }

 @GetMapping(
 path = "/api/contacts-a/{contactId}/addresses/{addressId}",
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<AddressResponseI> get(User user,
 @PathVariable("contactId") String contactId,
 @PathVariable("addressId") String addressId) {
 AddressResponseI addressResponse = addressService.get(user, contactId, addressId);
 return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
 }
 */

/** sebelum 03:44:32 - Remove Address API
 @Autowired
 private AddressServiceI addressService;

 @PostMapping(
 path = "/api/contacts-a/{contactId}/addresses",
 produces = MediaType.APPLICATION_JSON_VALUE,
 consumes = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<AddressResponseI> create(User user,
 @RequestBody CreateAddressRequestI request,
 @PathVariable("contactId") String contactId) {
 request.setContactId(contactId);
 AddressResponseI addressResponse = addressService.create(user, request);
 return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
 }

 @GetMapping(
 path = "/api/contacts-a/{contactId}/addresses/{addressId}",
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<AddressResponseI> get(User user,
 @PathVariable("contactId") String contactId,
 @PathVariable("addressId") String addressId) {
 AddressResponseI addressResponse = addressService.get(user, contactId, addressId);
 return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
 }

 @PutMapping(
 path = "/api/contacts-a/{contactId}/addresses/{addressId}",
 produces = MediaType.APPLICATION_JSON_VALUE,
 consumes = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<AddressResponseI> update(User user,
 @RequestBody UpdateAddressRequestJ request,
 @PathVariable("contactId") String contactId,
 @PathVariable("addressId") String addressId) {
 request.setContactId(contactId);
 request.setAddressId(addressId);

 AddressResponseI addressResponse = addressService.update(user, request);
 return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
 }

 @DeleteMapping(
 path = "/api/contacts-a/{contactId}/addresses/{addressId}",
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<String> remove(User user,
 @PathVariable("contactId") String contactId,
 @PathVariable("addressId") String addressId) {
 addressService.remove(user, contactId, addressId);
 return WebResponseA.<String>builder().data("OK").build();
 }
 */

/** sebelum 03:48:19 - List Address API
 @Autowired
 private AddressServiceI addressService;

 @PostMapping(
 path = "/api/contacts-a/{contactId}/addresses",
 produces = MediaType.APPLICATION_JSON_VALUE,
 consumes = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<AddressResponseI> create(User user,
 @RequestBody CreateAddressRequestI request,
 @PathVariable("contactId") String contactId) {
 request.setContactId(contactId);
 AddressResponseI addressResponse = addressService.create(user, request);
 return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
 }

 @GetMapping(
 path = "/api/contacts-a/{contactId}/addresses/{addressId}",
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<AddressResponseI> get(User user,
 @PathVariable("contactId") String contactId,
 @PathVariable("addressId") String addressId) {
 AddressResponseI addressResponse = addressService.get(user, contactId, addressId);
 return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
 }

 @PutMapping(
 path = "/api/contacts-a/{contactId}/addresses/{addressId}",
 produces = MediaType.APPLICATION_JSON_VALUE,
 consumes = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<AddressResponseI> update(User user,
 @RequestBody UpdateAddressRequestJ request,
 @PathVariable("contactId") String contactId,
 @PathVariable("addressId") String addressId) {
 request.setContactId(contactId);
 request.setAddressId(addressId);

 AddressResponseI addressResponse = addressService.update(user, request);
 return WebResponseA.<AddressResponseI>builder().data(addressResponse).build();
 }

 @DeleteMapping(
 path = "/api/contacts-a/{contactId}/addresses/{addressId}",
 produces = MediaType.APPLICATION_JSON_VALUE
 )
 public WebResponseA<String> remove(User user,
 @PathVariable("contactId") String contactId,
 @PathVariable("addressId") String addressId) {
 addressService.remove(user, contactId, addressId);
 return WebResponseA.<String>builder().data("OK").build();
 }
 */