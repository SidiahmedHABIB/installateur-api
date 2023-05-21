package com.app.Installateur_API.security;

import com.app.Installateur_API.entity.Admin;
import com.app.Installateur_API.entity.AppUser;
import com.app.Installateur_API.entity.User;
import com.app.Installateur_API.service.interfaces.IAdminService;
import com.app.Installateur_API.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger LOGGER= LoggerFactory.getLogger(AuthController.class);
    private final TokenService tokenService;
    private final AccountService accountService;
    private final JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private IUserService iUserService;
    private IAdminService iAdminService;

    public AuthController(TokenService tokenService,
                          AccountService accountService,
                          JwtDecoder jwtDecoder,
                          AuthenticationManager authenticationManager,
                          IUserService iUserService,
                          IAdminService iAdminService) {
        this.tokenService = tokenService;
        this.accountService = accountService;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.iUserService = iUserService;
        this.iAdminService=iAdminService;
    }

    @GetMapping("/app")
    public List<AppUser> getAll(){
        return  accountService.getAll();
    }
    @PostMapping("/admin")
    public ResponseEntity<Map<String,Object>> requestForTokenAdmin(LoginRequest loginRequest){
        Map<String,String > response;
        if(loginRequest.grantType().equals("password")){
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),loginRequest.password()
                    )
            );
            response=tokenService.generateJwtToken(authentication.getName(),authentication.getAuthorities(),loginRequest.withRefreshToken());
            Admin admin = iAdminService.loginAdmin(loginRequest.email());
            return ResponseEntity.ok(Map.of("tokens",response,"data",admin));
        } else if(loginRequest.grantType().equals("refreshToken")){
            String refreshToken=loginRequest.refreshToken();
            if(refreshToken==null) {
                return new ResponseEntity<>(Map.of("error","RefreshToken Not Present"),HttpStatus.UNAUTHORIZED);
            }
            Jwt decodedJwt = jwtDecoder.decode(refreshToken);
            String email=decodedJwt.getSubject();
            System.out.println(email);
            AppUser appUser=accountService.findByEmail(email);
            Collection<GrantedAuthority> authorities=appUser.getAppRoles()
                        .stream()
                        .map(role->new SimpleGrantedAuthority(role.getRoleName()))
                        .collect(Collectors.toList());
            response=tokenService.generateJwtToken(appUser.getEmail(),authorities,loginRequest.withRefreshToken());
            return ResponseEntity.ok(Map.of("tokens",response));
        }
        return new ResponseEntity(Map.of("error",String.format("grantType <<%s>> not supported ",loginRequest.grantType())),HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/technician")
    public ResponseEntity<Map<String, Object>> requestForTokenTechnician(LoginRequest loginRequest){
        Map<String,String > response;
        if(loginRequest.grantType().equals("password")){
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),loginRequest.password()
                    )
            );
            response=tokenService.generateJwtToken(authentication.getName(),authentication.getAuthorities(),loginRequest.withRefreshToken());
            User user = iUserService.loginUser(loginRequest.email());
            return ResponseEntity.ok(Map.of("tokens",response,"data",user));
        } else if(loginRequest.grantType().equals("refreshToken")){
            String refreshToken=loginRequest.refreshToken();
            if(refreshToken==null) {
                return new ResponseEntity<>(Map.of("error","RefreshToken Not Present"),HttpStatus.UNAUTHORIZED);
            }
            Jwt decodedJwt = jwtDecoder.decode(refreshToken);
            String email=decodedJwt.getSubject();
            System.out.println(email);
            AppUser appUser=accountService.findByEmail(email);
            Collection<GrantedAuthority> authorities=appUser.getAppRoles()
                    .stream()
                    .map(role->new SimpleGrantedAuthority(role.getRoleName()))
                    .collect(Collectors.toList());
            response=tokenService.generateJwtToken(appUser.getEmail(),authorities,loginRequest.withRefreshToken());
            return ResponseEntity.ok(Map.of("tokens",response));
        }
        return new ResponseEntity(Map.of("error",String.format("grantType <<%s>> not supported ",loginRequest.grantType())),HttpStatus.UNAUTHORIZED);
    }

}
