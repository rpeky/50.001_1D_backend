package com.travelapp.api.AAsecurity;

import com.travelapp.api.AAsecurity.DTO.LoginRequestDTO;
import com.travelapp.api.AAsecurity.DTO.LoginResponseDTO;
import com.travelapp.api.AAsecurity.DTO.UsersLoginConfirmationDTO;
import com.travelapp.api.globalnonsense.responserequestwrappers.ApiResponse;
import com.travelapp.api.globalnonsense.responserequestwrappers.ApiRequest;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody ApiRequest<LoginRequestDTO> request) {
        LoginRequestDTO loginRequest = request.getData();
        String userUid = loginRequest.getUserUid();
        String email = loginRequest.getEmail();

        Optional<Users> userOpt = usersRepository.findByUserUid(userUid);
        if (userOpt.isEmpty()) {
            throw new BadCredentialsException("Invalid credentials");
        }

        Users user = userOpt.get();

        if (!user.getUserUid().equalsIgnoreCase(userUid) || !user.getEmail().equalsIgnoreCase(email)) {
            throw new BadCredentialsException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(userUid, email);

        UsersLoginConfirmationDTO userDto = new UsersLoginConfirmationDTO();
        userDto.setUserUid(user.getUserUid());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setBio(user.getBio());

        LoginResponseDTO responseDTO = new LoginResponseDTO(token, userDto);

        return ResponseEntity.ok(new ApiResponse<>(responseDTO));
    }


}

