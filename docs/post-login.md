INSERT INTO services (service_code, service_name, service_icon, service_tariff)
VALUES
('PAJAK', 'Pajak PBB', 'https://nutech-integrasi.app/dummy.jpg', 40000),
('PLN', 'Listrik', 'https://nutech-integrasi.app/dummy.jpg', 10000),
('PDAM', 'PDAM Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 40000),
('PULSA', 'Pulsa', 'https://nutech-integrasi.app/dummy.jpg', 40000),
('PGN', 'PGN Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 50000),
('MUSIK', 'Musik Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 50000),
('TV', 'TV Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 50000),
('PAKET_DATA', 'Paket data', 'https://nutech-integrasi.app/dummy.jpg', 50000),
('VOUCHER_GAME', 'Voucher Game', 'https://nutech-integrasi.app/dummy.jpg', 100000),
('VOUCHER_MAKANAN', 'Voucher Makanan', 'https://nutech-integrasi.app/dummy.jpg', 100000),
('QURBAN', 'Qurban', 'https://nutech-integrasi.app/dummy.jpg', 200000),
('ZAKAT', 'Zakat', 'https://nutech-integrasi.app/dummy.jpg', 300000);


Berikut adalah contoh kode Spring Boot untuk membuat endpoint yang menerima permintaan JSON dengan body yang berisi email dan password, kemudian memeriksa kredensial tersebut dalam database MySQL dan mengirimkan respons menggunakan `ResponseEntity`. Kode ini juga termasuk pembuatan token JWT jika login berhasil.

Pertama, Anda perlu menambahkan dependensi MySQL dan dependensi JWT ke dalam file `pom.xml`:

```xml
<dependencies>
    <!-- MySQL Dependency -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Spring Security and JWT Dependency -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.9.1</version>
    </dependency>
</dependencies>
```

Kemudian, Anda dapat membuat entitas `User` untuk merepresentasikan data pengguna dalam database MySQL:

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    // getters and setters
}
```

Berikut adalah kode untuk kontroler Spring Boot yang menangani permintaan login:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new LoginResponse(0, "Login Sukses", new TokenResponse(token)));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new ErrorResponse(1, "Login Gagal", null));
        }
    }
}
```

Ini adalah kelas `UserLoginRequest`:

```java
public class UserLoginRequest {
    private String email;
    private String password;

    // getters and setters
}
```

Ini adalah kelas `LoginResponse`:

```java
public class LoginResponse {
    private int status;
    private String message;
    private TokenResponse data;

    // constructors, getters, and setters
}
```

Ini adalah kelas `TokenResponse`:

```java
public class TokenResponse {
    private String token;

    // constructor, getters, and setters
}
```

Ini adalah kelas `ErrorResponse`:

```java
public class ErrorResponse {
    private int status;
    private String message;
    private Object data;

    // constructor, getters, and setters
}
```

Kemudian, Anda dapat membuat repositori `UserRepository` untuk berinteraksi dengan database MySQL:

```java
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
```

Terakhir, Anda perlu menambahkan konfigurasi Spring Security dan JWT ke aplikasi Anda. Ini dapat dilakukan dengan menambahkan kelas berikut ke proyek Anda:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

Anda juga perlu menambahkan kelas `JwtUtil` untuk menghasilkan token JWT:

```java
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
```

Pastikan Anda memiliki konfigurasi dalam file `application.properties` atau `application.yml` untuk menyimpan konfigurasi JWT, seperti `jwt.secret` dan `jwt.expiration`.

Ini adalah contoh kode untuk membuat endpoint login dengan Spring Boot, MySQL, dan JWT. Pastikan Anda memiliki dependensi yang sesuai di `pom.xml` dan konfigurasi yang diperlukan dalam proyek Anda.