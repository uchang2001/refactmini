package com.one.miniproject.service;

import com.one.miniproject.dto.ResponseDto;
import com.one.miniproject.dto.SignUpRequestDto;
import com.one.miniproject.model.User;
import com.one.miniproject.repository.UserRepository;
import com.one.miniproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public ResponseDto registerUser(SignUpRequestDto requestDto) {
        Boolean result = true;
        String err_msg = "사용가능한 ID 입니다.";
        String username = requestDto.getUsername();
        String nickname = requestDto.getNickname();

        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            err_msg = "중복된 ID가 존재합니다.";
            result = false;
            return new ResponseDto(result, err_msg, nickname);
//            throw new IllegalArgumentException("중복된 ID 존재");
        }
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password, nickname);
        userRepository.save(user);

        ResponseDto responseDto = new ResponseDto(result, err_msg, nickname);
        return responseDto;

    }

    public ResponseDto login(SignUpRequestDto requestDto) {
        Boolean result = false;
        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());
        User user = userRepository.findByUsername(requestDto.getUsername()).orElse(null);
        if (found.isPresent() && passwordEncoder.matches(requestDto.getPassword(), found.get().getPassword())) {
            result = true;
            return new ResponseDto(user.getNickname(), result);
        } else {
            return new ResponseDto(result);
        }
    }


    public ResponseDto nicknameCheck(SignUpRequestDto requestDto) {
        Optional<User> user = userRepository.findByNickname(requestDto.getNickname());
        if (user.isPresent()) {
            Boolean result = false;
            return new ResponseDto(result);
        } else {
            Boolean result = true;
            String nickname = requestDto.getNickname();
            Optional<User> kakaoUser = userRepository.findByUsername(requestDto.getUsername());
            kakaoUser.ifPresent(selectUser->{
                selectUser.setUsername(kakaoUser.get().getUsername());
                selectUser.setNickname(nickname);
                userRepository.save(selectUser);
            });
            return new ResponseDto(result);
        }
    }
}