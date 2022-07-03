package ar.com.plug.examen.app.rest.controllers;

import ar.com.plug.examen.app.api.User;
import ar.com.plug.examen.app.rest.model.Seller;
import ar.com.plug.examen.app.rest.repositories.SellerRepository;
import ar.com.plug.examen.app.rest.services.SellerService;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {
    private final SellerRepository sellerRepository;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @PostMapping("user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) throws BadHttpRequest {
        logger.info(username + " - " + pwd);
        Seller seller = this.sellerRepository.findByName(username).orElseThrow(()->new NoSuchElementException("Bad Request"));
        if(pwd.equals(String.valueOf(seller.getName().hashCode()))){
            String token = getJWTToken(username);
            User user = new User();
            user.setUser(username);
            user.setToken(token);
            return user;
        }else{
            throw new BadHttpRequest();
        }

    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}