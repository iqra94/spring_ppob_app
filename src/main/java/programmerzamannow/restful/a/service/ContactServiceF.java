package programmerzamannow.restful.a.service;

import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import programmerzamannow.restful.a.model.ContactResponseF;
import programmerzamannow.restful.a.model.CreateContactRequestF;
import programmerzamannow.restful.a.model.SearchContactRequestH;
import programmerzamannow.restful.a.model.UpdateContactRequestG;
import programmerzamannow.restful.a.repository.ContactRepositoryF;
import programmerzamannow.restful.entity.Contact;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.ContactResponse;
import programmerzamannow.restful.model.CreateContactRequest;
import programmerzamannow.restful.model.SearchContactRequest;
import programmerzamannow.restful.model.UpdateContactRequest;
import programmerzamannow.restful.repository.ContactRepository;
import programmerzamannow.restful.service.ValidationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ContactServiceF {

    @Autowired
    private ContactRepositoryF contactRepository;

    @Autowired
    private ValidationServiceB validationService;

    @Transactional
    public ContactResponseF create(User user, CreateContactRequestF request) {
        validationService.validate(request);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);

        contactRepository.save(contact);

        return toContactResponse(contact);
    }

    private ContactResponseF toContactResponse(Contact contact) {
        return ContactResponseF.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }

    @Transactional(readOnly = true)
    public ContactResponseF get(User user, String id) {
        Contact contact = contactRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        return toContactResponse(contact);
    }

    @Transactional
    public ContactResponseF update(User user, UpdateContactRequestG request) {
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contactRepository.save(contact);

        return toContactResponse(contact);
    }

    @Transactional
    public void delete(User user, String contactId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        contactRepository.delete(contact);
    }

    @Transactional(readOnly = true)
    public Page<ContactResponseF> search(User user, SearchContactRequestH request) {
        Specification<Contact> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("user"), user));
            if (Objects.nonNull(request.getName())) {
                predicates.add(builder.or(
                        builder.like(root.get("firstName"), "%" + request.getName() + "%"),
                        builder.like(root.get("lastName"), "%" + request.getName() + "%")
                ));
            }
            if (Objects.nonNull(request.getEmail())) {
                predicates.add(builder.like(root.get("email"), "%" + request.getEmail() + "%"));
            }
            if (Objects.nonNull(request.getPhone())) {
                predicates.add(builder.like(root.get("phone"), "%" + request.getPhone() + "%"));
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Contact> contacts = contactRepository.findAll(specification, pageable);
        List<ContactResponseF> contactResponses = contacts.getContent().stream()
                .map(this::toContactResponse)
                .toList();

        return new PageImpl<>(contactResponses, pageable, contacts.getTotalElements());
    }
}

/** sebelum 02:39:00 - Get Contact API
     @Autowired
     private ContactRepositoryF contactRepository;

     @Autowired
     private ValidationServiceB validationService;

     @Transactional
     public ContactResponseF create(User user, CreateContactRequestF request) {
     validationService.validate(request);

     Contact contact = new Contact();
     contact.setId(UUID.randomUUID().toString());
     contact.setFirstName(request.getFirstName());
     contact.setLastName(request.getLastName());
     contact.setEmail(request.getEmail());
     contact.setPhone(request.getPhone());
     contact.setUser(user);

     contactRepository.save(contact);

     return toContactResponse(contact);
     }

     private ContactResponseF toContactResponse(Contact contact) {
     return ContactResponseF.builder()
     .id(contact.getId())
     .firstName(contact.getFirstName())
     .lastName(contact.getLastName())
     .email(contact.getEmail())
     .phone(contact.getPhone())
     .build();
     }
 */

/** sebelum 02:46:15 - Update Contact API
 @Autowired
 private ContactRepositoryF contactRepository;

 @Autowired
 private ValidationServiceB validationService;

 @Transactional
 public ContactResponseF create(User user, CreateContactRequestF request) {
 validationService.validate(request);

 Contact contact = new Contact();
 contact.setId(UUID.randomUUID().toString());
 contact.setFirstName(request.getFirstName());
 contact.setLastName(request.getLastName());
 contact.setEmail(request.getEmail());
 contact.setPhone(request.getPhone());
 contact.setUser(user);

 contactRepository.save(contact);

 return toContactResponse(contact);
 }

 private ContactResponseF toContactResponse(Contact contact) {
 return ContactResponseF.builder()
 .id(contact.getId())
 .firstName(contact.getFirstName())
 .lastName(contact.getLastName())
 .email(contact.getEmail())
 .phone(contact.getPhone())
 .build();
 }

 @Transactional(readOnly = true)
 public ContactResponseF get(User user, String id) {
 Contact contact = contactRepository.findFirstByUserAndId(user, id)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

 return toContactResponse(contact);
 }
 */

/** sebelum 02:53:38 - Remove Contact API
 @Autowired
 private ContactRepositoryF contactRepository;

 @Autowired
 private ValidationServiceB validationService;

 @Transactional
 public ContactResponseF create(User user, CreateContactRequestF request) {
 validationService.validate(request);

 Contact contact = new Contact();
 contact.setId(UUID.randomUUID().toString());
 contact.setFirstName(request.getFirstName());
 contact.setLastName(request.getLastName());
 contact.setEmail(request.getEmail());
 contact.setPhone(request.getPhone());
 contact.setUser(user);

 contactRepository.save(contact);

 return toContactResponse(contact);
 }

 private ContactResponseF toContactResponse(Contact contact) {
 return ContactResponseF.builder()
 .id(contact.getId())
 .firstName(contact.getFirstName())
 .lastName(contact.getLastName())
 .email(contact.getEmail())
 .phone(contact.getPhone())
 .build();
 }

 @Transactional(readOnly = true)
 public ContactResponseF get(User user, String id) {
 Contact contact = contactRepository.findFirstByUserAndId(user, id)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

 return toContactResponse(contact);
 }

 @Transactional
 public ContactResponseF update(User user, UpdateContactRequestG request) {
 validationService.validate(request);

 Contact contact = contactRepository.findFirstByUserAndId(user, request.getId())
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

 contact.setFirstName(request.getFirstName());
 contact.setLastName(request.getLastName());
 contact.setEmail(request.getEmail());
 contact.setPhone(request.getPhone());
 contactRepository.save(contact);

 return toContactResponse(contact);
 }
 */

/** sebelum 02:57:09 - Search Contact API
 @Autowired
 private ContactRepositoryF contactRepository;

 @Autowired
 private ValidationServiceB validationService;

 @Transactional
 public ContactResponseF create(User user, CreateContactRequestF request) {
 validationService.validate(request);

 Contact contact = new Contact();
 contact.setId(UUID.randomUUID().toString());
 contact.setFirstName(request.getFirstName());
 contact.setLastName(request.getLastName());
 contact.setEmail(request.getEmail());
 contact.setPhone(request.getPhone());
 contact.setUser(user);

 contactRepository.save(contact);

 return toContactResponse(contact);
 }

 private ContactResponseF toContactResponse(Contact contact) {
 return ContactResponseF.builder()
 .id(contact.getId())
 .firstName(contact.getFirstName())
 .lastName(contact.getLastName())
 .email(contact.getEmail())
 .phone(contact.getPhone())
 .build();
 }

 @Transactional(readOnly = true)
 public ContactResponseF get(User user, String id) {
 Contact contact = contactRepository.findFirstByUserAndId(user, id)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

 return toContactResponse(contact);
 }

 @Transactional
 public ContactResponseF update(User user, UpdateContactRequestG request) {
 validationService.validate(request);

 Contact contact = contactRepository.findFirstByUserAndId(user, request.getId())
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

 contact.setFirstName(request.getFirstName());
 contact.setLastName(request.getLastName());
 contact.setEmail(request.getEmail());
 contact.setPhone(request.getPhone());
 contactRepository.save(contact);

 return toContactResponse(contact);
 }

 @Transactional
 public void delete(User user, String contactId) {
 Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

 contactRepository.delete(contact);
 }
 */
