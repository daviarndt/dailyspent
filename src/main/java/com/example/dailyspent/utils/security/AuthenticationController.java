package com.example.dailyspent.utils.security;

import com.example.dailyspent.user.UserModel;
import com.example.dailyspent.utils.ApiResponse;
import com.example.dailyspent.utils.security.dto.RequestAuthenticationDTO;
import com.example.dailyspent.utils.security.dto.ResponseAuthenticationDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResponseAuthenticationDTO>> login(@RequestBody @Valid RequestAuthenticationDTO data) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String tokenJWT = tokenService.generateToken((UserModel) authentication.getPrincipal());
        return new ResponseEntity<>(ApiResponse.success(
                new ResponseAuthenticationDTO(
                        tokenJWT)
                ),
                HttpStatus.OK);
    }
}
