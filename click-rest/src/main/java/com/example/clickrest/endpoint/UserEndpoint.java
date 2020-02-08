package com.example.clickrest.endpoint;

import am.itspace.clickcommon.model.Address;
import am.itspace.clickcommon.model.User;
import am.itspace.clickcommon.service.AddressService;
import am.itspace.clickcommon.service.ImageService;
import am.itspace.clickcommon.service.ProductService;
import am.itspace.clickcommon.service.UserService;
import com.example.clickrest.dto.AuthenticationRequest;
import com.example.clickrest.dto.AuthenticationResponse;
import com.example.clickrest.dto.UserDto;
import com.example.clickrest.security.CurrentUser;
import com.example.clickrest.security.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserEndpoint {
    private final UserService userService;
    private final PasswordEncoder  passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final ImageService imageService;
    private final AddressService addressService;
    private final ProductService productService;

    public UserEndpoint(UserService userService, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, ImageService imageService, AddressService addressService, ProductService productService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.imageService = imageService;
        this.addressService = addressService;
        this.productService = productService;
    }

    @PostMapping("auth")
    public ResponseEntity auth(@RequestBody AuthenticationRequest authenticationRequest) {
        User user = userService.findByEmail(authenticationRequest.getEmail());
        if (user != null && passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())
        ) {
            String token = jwtTokenUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(token)
                    .userDto(UserDto.builder()
                          //  .id(user.getId())
                            .name(user.getName())
                            .surname(user.getSurname())
                            .email(user.getEmail())
                           // .userType(user.getUserType())
                            .build())
            );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }}

//    @PostMapping("address")
//    public void saveAddress(@RequestBody Address address AuthenticationPrincipal CurrentUser user{
//        User byId=userService.findById(user)
//
//    }

//    @PostMapping("/user/add")
//    public ResponseEntity add(@RequestBody User user) {
//        if (userService.findByEmail(user.getEmail()) != null) {
//            return ResponseEntity
//                    .status(HttpStatus.CONFLICT)
//                    .build();
//        }
//
//        userService.save(user);
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity getAll() {
//        return ResponseEntity.ok(userService.findAll());
//
//    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity getById(@PathVariable("id") int id) {
//        Optional<User> byId = Optional.ofNullable(userService.findById(id));
//        if (byId.isPresent()) {
//            return ResponseEntity.ok(byId.get());
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity deleteById(@PathVariable("id") int id) {
//
//        Optional<User> byId = userService.findById(id);
//        if (byId.isPresent()) {
//            userService.deleteById(id);
//            return ResponseEntity.ok(byId.get());
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @PutMapping("/user/update")
//    public ResponseEntity update(@RequestBody User user) {
//        if (userService.findById(user.getId()).isPresent()) {
//            userService.save(user);
//            return ResponseEntity
//                    .ok(user);
//        }
//        return ResponseEntity.notFound().build();
//    }
//}
