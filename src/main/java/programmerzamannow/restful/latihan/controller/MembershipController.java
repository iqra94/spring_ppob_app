package programmerzamannow.restful.latihan.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import programmerzamannow.restful.latihan.config.JwtProvider;
import programmerzamannow.restful.latihan.entity.Membership;
import programmerzamannow.restful.latihan.repository.MembershipRepository;
import programmerzamannow.restful.latihan.request.LoginReq;
import programmerzamannow.restful.latihan.request.ProfileReq;
import programmerzamannow.restful.latihan.request.RegistrationReq;
import programmerzamannow.restful.latihan.response.ApiRes;
import programmerzamannow.restful.latihan.service.CustomUserServiceImpl;
import programmerzamannow.restful.latihan.service.ValidationService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class MembershipController {

  @Autowired
  private MembershipRepository membershipRepository;

  @Autowired
  private CustomUserServiceImpl customUserServiceImpl;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtProvider jwtProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private ValidationService validationService;

  @PostMapping("/registration")
  public ResponseEntity<ApiRes> createUserHandler(@Valid @RequestBody RegistrationReq user, BindingResult bindingResult) {
    System.out.println(user.getFirstName());
    System.out.println(user.getLastName());

    ApiRes authResponse = new ApiRes();

    if (bindingResult.hasErrors()) {
      List<FieldError> errors = bindingResult.getFieldErrors();
      StringBuilder errorMessages = new StringBuilder();
      for (FieldError error : errors) {
        errorMessages.append(error.getDefaultMessage());
        authResponse.setStatus(102);
        authResponse.setMessage(errorMessages.toString());
        authResponse.setData(null);
      }
      return new ResponseEntity<ApiRes>(authResponse, HttpStatus.BAD_REQUEST);
    }

    Membership createdUser = new Membership();
    createdUser.setFirstName(user.getFirstName());
    createdUser.setLastName(user.getLastName());
    createdUser.setEmail(user.getEmail());
    createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

    membershipRepository.save(createdUser);

    return new ResponseEntity<ApiRes>(new ApiRes(0, "Registrasi berhasil silahkan login", null), HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<ApiRes> loginUserHandler(@Valid @RequestBody LoginReq loginRequest, BindingResult bindingResult) {
    ApiRes authResponse = new ApiRes();

    if (bindingResult.hasErrors()) {
      // Handle validation errors here
      List<FieldError> errors = bindingResult.getFieldErrors();
      StringBuilder errorMessages = new StringBuilder();
      for (FieldError error : errors) {
        errorMessages.append(error.getDefaultMessage()).append("\n");
        authResponse.setStatus(102);
        authResponse.setMessage(errorMessages.toString());
        authResponse.setData(null);
      }
      return new ResponseEntity<ApiRes>(authResponse, HttpStatus.BAD_REQUEST);
    }

    Membership userMember = membershipRepository.findByEmail(loginRequest.getEmail());

    if (userMember != null) {
      String username = userMember.getEmail();

      if (!loginRequest.getEmail().equals(username)){
        return new ResponseEntity<>(new ApiRes(103, "Username atau password salah", null), HttpStatus.UNAUTHORIZED);
      }
    } else {
      return new ResponseEntity<>(new ApiRes(103, "Username atau password salah", null), HttpStatus.UNAUTHORIZED);
    }

    if (!passwordEncoder.matches(loginRequest.getPassword(), userMember.getPassword())){
      return new ResponseEntity<>(new ApiRes(103, "Username atau password salah", null), HttpStatus.UNAUTHORIZED);
    }

    Authentication authentication = authenticate(loginRequest);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtProvider.generateToken(authentication);

    Map<String, String> tokenDua = Map.of("token", jwtProvider.generateToken(authentication));

    return new ResponseEntity<ApiRes>(new ApiRes(0, "Login Sukses", token), HttpStatus.CREATED);
  }

  private Authentication authenticate(LoginReq loginReq) {
    UserDetails userDetails = customUserServiceImpl.loadUserByUsername(loginReq.getEmail());

    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }

  @PutMapping("/profile/update")
  public ResponseEntity<?> profileUpdate(@RequestBody ProfileReq profileReq, @RequestHeader("Authorization") String jwt) {

    String emailJwt = jwtProvider.getEmailFromToken(jwt);
    Membership user = membershipRepository.findByEmail(emailJwt);

    if (user != null) {
      user.setFirstName(profileReq.getFirstName());
      user.setLastName(profileReq.getLastName());
      return ResponseEntity.status(HttpStatus.OK).body(
        new ApiRes(0, "Sukses", user)
      );
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
      new ApiRes(108, "Token tidak valid atau kadaluwarsa", null)
    );
  }

  @PutMapping("/profile/image")
  public ResponseEntity<ApiRes> profileImage(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String jwt) {

    String emailJwt = jwtProvider.getEmailFromToken(jwt);
    Membership user = membershipRepository.findByEmail(emailJwt);

    System.out.println(file.getOriginalFilename());
    System.out.println(file.getContentType());

    if (user != null) {
      try {
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!fileExtension.equals("jpg") && !fileExtension.equals("png")) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ApiRes(102, "Format Image tidak sesuai", null)
          );
        }
        String filePath = "/home/agera94/Pictures" + File.separator + fileName;
        file.transferTo(new File(filePath));

        user.setProfileImage(filePath);
        membershipRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(
          new ApiRes(0, "Update Profile Image berhasil", user)
        );
      } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
          new ApiRes(108, "Token tidak valid atau kadaluwarsa", null)
        );
      }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            new ApiRes(108, "Token tidak valid atau kadaluwarsa", null)
    );

  }

  @GetMapping("/profile")
  public ResponseEntity<?> profileGet(@RequestHeader("Authorization") String jwt) {

    String emailJwt = jwtProvider.getEmailFromToken(jwt);
    Membership user = membershipRepository.findByEmail(emailJwt);

    if (user != null) {
      return ResponseEntity.status(HttpStatus.OK).body(
        new ApiRes(0, "Sukses", user)
      );
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
      new ApiRes(108, "Token tidak valid atau kadaluwarsa", null)
    );
  }


}
