package br.com.jardel.controller;

import br.com.jardel.repository.UserRepository;
import br.com.jardel.security.AccountCredentialsVO;
import br.com.jardel.security.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static br.com.jardel.config.WebConfig.APPLICATION_YAML_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping("auth")
public class AuthController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(value = "/signin",
            produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE},
            consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public ResponseEntity signin(@RequestBody AccountCredentialsVO accountCredentialsVO) {

        var userName = accountCredentialsVO.getUserName();
        var password = accountCredentialsVO.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

        var user = userRepository.findByUserName(userName);

        var token = "";

        if (user != null) {
            token = jwtTokenProvider.createToken(userName, user.getRoles());
        } else {
            throw new UsernameNotFoundException("User:" + userName + " not found!");
        }

        Map<Object, Object> model = new HashMap<>();
        model.put("username", userName);
        model.put("token", token);

        return ResponseEntity.ok(model);
    }

    @GetMapping(value = "/password/{password}",
            produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE, APPLICATION_YAML_VALUE})
    public String gerarPassword(@PathVariable String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
