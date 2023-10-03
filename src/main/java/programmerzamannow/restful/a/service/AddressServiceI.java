package programmerzamannow.restful.a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import programmerzamannow.restful.a.model.AddressResponseI;
import programmerzamannow.restful.a.model.CreateAddressRequestI;
import programmerzamannow.restful.a.model.UpdateAddressRequestJ;
import programmerzamannow.restful.a.repository.AddressRepositoryI;
import programmerzamannow.restful.a.repository.ContactRepositoryF;
import programmerzamannow.restful.entity.Address;
import programmerzamannow.restful.entity.Contact;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.AddressResponse;
import programmerzamannow.restful.model.CreateAddressRequest;
import programmerzamannow.restful.model.UpdateAddressRequest;
import programmerzamannow.restful.repository.AddressRepository;
import programmerzamannow.restful.repository.ContactRepository;
import programmerzamannow.restful.service.ValidationService;

import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceI {

    @Autowired
    private ContactRepositoryF contactRepository;

    @Autowired
    private AddressRepositoryI addressRepository;

    @Autowired
    private ValidationServiceB validationService;

    @Transactional
    public AddressResponseI create(User user, CreateAddressRequestI request) {
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setContact(contact);
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());

        addressRepository.save(address);

        return toAddressResponse(address);
    }

    private AddressResponseI toAddressResponse(Address address) {
        return AddressResponseI.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .province(address.getProvince())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .build();
    }

    @Transactional(readOnly = true)
    public AddressResponseI get(User user, String contactId, String addressId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

        Address address = addressRepository.findFirstByContactAndId(contact, addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

        return toAddressResponse(address);
    }

    @Transactional
    public AddressResponseI update(User user, UpdateAddressRequestJ request){
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

        Address address = addressRepository.findFirstByContactAndId(contact, request.getAddressId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());
        addressRepository.save(address);

        return toAddressResponse(address);
    }

    @Transactional
    public void remove(User user, String contactId, String addressId){
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

        Address address = addressRepository.findFirstByContactAndId(contact, addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

        addressRepository.delete(address);
    }

    @Transactional(readOnly = true)
    public List<AddressResponseI> list(User user, String contactId){
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

        List<Address> addresses = addressRepository.findAllByContact(contact);
        return addresses.stream().map(this::toAddressResponse).toList();
    }
}

/** sebelum 03:32:10 - Get Address API
 @Autowired
 private ContactRepositoryF contactRepository;

 @Autowired
 private AddressRepositoryI addressRepository;

 @Autowired
 private ValidationServiceB validationService;

 @Transactional
 public AddressResponseI create(User user, CreateAddressRequestI request) {
 validationService.validate(request);

 Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

 Address address = new Address();
 address.setId(UUID.randomUUID().toString());
 address.setContact(contact);
 address.setStreet(request.getStreet());
 address.setCity(request.getCity());
 address.setProvince(request.getProvince());
 address.setCountry(request.getCountry());
 address.setPostalCode(request.getPostalCode());

 addressRepository.save(address);

 return toAddressResponse(address);
 }

 private AddressResponseI toAddressResponse(Address address) {
 return AddressResponseI.builder()
 .id(address.getId())
 .street(address.getStreet())
 .city(address.getCity())
 .province(address.getProvince())
 .country(address.getCountry())
 .postalCode(address.getPostalCode())
 .build();
 }
 */

/** sebelum 03:39:00 - Update Address API
 @Autowired
 private ContactRepositoryF contactRepository;

 @Autowired
 private AddressRepositoryI addressRepository;

 @Autowired
 private ValidationServiceB validationService;

 @Transactional
 public AddressResponseI create(User user, CreateAddressRequestI request) {
 validationService.validate(request);

 Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

 Address address = new Address();
 address.setId(UUID.randomUUID().toString());
 address.setContact(contact);
 address.setStreet(request.getStreet());
 address.setCity(request.getCity());
 address.setProvince(request.getProvince());
 address.setCountry(request.getCountry());
 address.setPostalCode(request.getPostalCode());

 addressRepository.save(address);

 return toAddressResponse(address);
 }

 private AddressResponseI toAddressResponse(Address address) {
 return AddressResponseI.builder()
 .id(address.getId())
 .street(address.getStreet())
 .city(address.getCity())
 .province(address.getProvince())
 .country(address.getCountry())
 .postalCode(address.getPostalCode())
 .build();
 }

 @Transactional(readOnly = true)
 public AddressResponseI get(User user, String contactId, String addressId) {
 Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

 Address address = addressRepository.findFirstByContactAndId(contact, addressId)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

 return toAddressResponse(address);
 }
 */

/** sebelum 03:44:32 - Remove Address API
 @Autowired
 private ContactRepositoryF contactRepository;

 @Autowired
 private AddressRepositoryI addressRepository;

 @Autowired
 private ValidationServiceB validationService;

 @Transactional
 public AddressResponseI create(User user, CreateAddressRequestI request) {
 validationService.validate(request);

 Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

 Address address = new Address();
 address.setId(UUID.randomUUID().toString());
 address.setContact(contact);
 address.setStreet(request.getStreet());
 address.setCity(request.getCity());
 address.setProvince(request.getProvince());
 address.setCountry(request.getCountry());
 address.setPostalCode(request.getPostalCode());

 addressRepository.save(address);

 return toAddressResponse(address);
 }

 private AddressResponseI toAddressResponse(Address address) {
 return AddressResponseI.builder()
 .id(address.getId())
 .street(address.getStreet())
 .city(address.getCity())
 .province(address.getProvince())
 .country(address.getCountry())
 .postalCode(address.getPostalCode())
 .build();
 }

 @Transactional(readOnly = true)
 public AddressResponseI get(User user, String contactId, String addressId) {
 Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

 Address address = addressRepository.findFirstByContactAndId(contact, addressId)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

 return toAddressResponse(address);
 }

 @Transactional
 public AddressResponseI update(User user, UpdateAddressRequestJ request){
 validationService.validate(request);

 Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

 Address address = addressRepository.findFirstByContactAndId(contact, request.getAddressId())
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

 address.setStreet(request.getStreet());
 address.setCity(request.getCity());
 address.setProvince(request.getProvince());
 address.setCountry(request.getCountry());
 address.setPostalCode(request.getPostalCode());
 addressRepository.save(address);

 return toAddressResponse(address);
 }
 */

/** sebelum 03:48:19 - List Address API
 @Autowired
 private ContactRepositoryF contactRepository;

 @Autowired
 private AddressRepositoryI addressRepository;

 @Autowired
 private ValidationServiceB validationService;

 @Transactional
 public AddressResponseI create(User user, CreateAddressRequestI request) {
 validationService.validate(request);

 Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

 Address address = new Address();
 address.setId(UUID.randomUUID().toString());
 address.setContact(contact);
 address.setStreet(request.getStreet());
 address.setCity(request.getCity());
 address.setProvince(request.getProvince());
 address.setCountry(request.getCountry());
 address.setPostalCode(request.getPostalCode());

 addressRepository.save(address);

 return toAddressResponse(address);
 }

 private AddressResponseI toAddressResponse(Address address) {
 return AddressResponseI.builder()
 .id(address.getId())
 .street(address.getStreet())
 .city(address.getCity())
 .province(address.getProvince())
 .country(address.getCountry())
 .postalCode(address.getPostalCode())
 .build();
 }

 @Transactional(readOnly = true)
 public AddressResponseI get(User user, String contactId, String addressId) {
 Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

 Address address = addressRepository.findFirstByContactAndId(contact, addressId)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

 return toAddressResponse(address);
 }

 @Transactional
 public AddressResponseI update(User user, UpdateAddressRequestJ request){
 validationService.validate(request);

 Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

 Address address = addressRepository.findFirstByContactAndId(contact, request.getAddressId())
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

 address.setStreet(request.getStreet());
 address.setCity(request.getCity());
 address.setProvince(request.getProvince());
 address.setCountry(request.getCountry());
 address.setPostalCode(request.getPostalCode());
 addressRepository.save(address);

 return toAddressResponse(address);
 }

 @Transactional
 public void remove(User user, String contactId, String addressId){
 Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

 Address address = addressRepository.findFirstByContactAndId(contact, addressId)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

 addressRepository.delete(address);
 }
 */