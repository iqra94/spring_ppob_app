package programmerzamannow.restful.latihan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import programmerzamannow.restful.config.JwtProvider;
import programmerzamannow.restful.latihan.entity.Membership;
import programmerzamannow.restful.latihan.repository.BannerRepository;
import programmerzamannow.restful.latihan.repository.MembershipRepository;
import programmerzamannow.restful.latihan.repository.ServiceRepository;
import programmerzamannow.restful.latihan.response.ApiRes;

@RestController
@RequestMapping("/test")
public class InformationController {

  @Autowired
  private MembershipRepository membershipRepository;
  @Autowired
  private BannerRepository bannerRepository;
  @Autowired
  private ServiceRepository serviceRepository;

  @Autowired
  private JwtProvider jwtProvider;

  @GetMapping("/banner")
  public ResponseEntity<?> bannerFindAll(@RequestHeader("Authorization") String jwt) {

    String emailJwt = jwtProvider.getEmailFromToken(jwt);
    Membership user = membershipRepository.findByEmail(emailJwt);

    if (user != null) {
      return ResponseEntity.status(HttpStatus.OK).body(
        new ApiRes(0, "Sukses", bannerRepository.findAll())
      );
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
      new ApiRes(108, "Token tidak valid atau kadaluwarsa", null)
    );
  }

  @GetMapping("/service")
  public ResponseEntity<?> serviceFindAll(@RequestHeader("Authorization") String jwt) {

    String emailJwt = jwtProvider.getEmailFromToken(jwt);
    Membership user = membershipRepository.findByEmail(emailJwt);

    if (user != null) {
      return ResponseEntity.status(HttpStatus.OK).body(
        new ApiRes(0, "Sukses", serviceRepository.findAll())
      );
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
      new ApiRes(108, "Token tidak valid atau kadaluwarsa", null)
    );
  }


}
