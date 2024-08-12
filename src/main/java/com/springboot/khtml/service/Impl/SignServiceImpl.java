package com.springboot.khtml.service.Impl;

import com.springboot.khtml.S3.S3Uploader;
import com.springboot.khtml.dao.AuthDao;
import com.springboot.khtml.dto.CommonResponse;
import com.springboot.khtml.dto.signDto.ResultDto;
import com.springboot.khtml.dto.signDto.SignInResultDto;
import com.springboot.khtml.dto.signDto.SignUpEmailDto;
import com.springboot.khtml.dto.signDto.SignUpFirstDto;
import com.springboot.khtml.entity.User;
import com.springboot.khtml.jwt.JwtProvider;
import com.springboot.khtml.repository.UserRepository;
import com.springboot.khtml.service.SignService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final JavaMailSender emailSender;
    private final AuthDao authDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;
    private Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);

    @Override
    public Map<String,String> sendSimpleMessage(String email, HttpServletRequest request) throws Exception {
        String ePw = createKey(); // 인증번호 생성

        // 세션에 이메일 저장
        request.getSession().setAttribute("email", email);

        MimeMessage message = createMessage(email, ePw);
        try {
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        Map<String,String> response = new HashMap<>();
        response.put("confirmation : ",ePw);

        return response;
    }

    @Override
    public boolean verifyEmail(String confirmationCode, HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("email"); // 세션에서 이메일 가져오기

        User user = new User();
        if (email != null) {
            user.setVerified(true);
            user.setEmail(email);

            // 세션에 인증 성공 여부 저장
            request.getSession().setAttribute("partialUser", user);

            return true;
        }
        return false;
    }

    @Override
    public ResultDto SignUpFirst(SignUpFirstDto signUpFirstDto, HttpServletRequest request) {
        User partialUser = (User) request.getSession().getAttribute("partialUser");

        ResultDto resultDto = new ResultDto();

        if (userRepository.existsByEmail(partialUser.getEmail())) {
            resultDto.setDetailMessage("아이디가 중복되었습니다.");
            setFail(resultDto);
            return resultDto;
        }

        if (!signUpFirstDto.getPassword().equals(signUpFirstDto.getPasswordCheck())) {
            resultDto.setDetailMessage("비밀번호가 일치하지 않습니다.");
            setFail(resultDto);
            return resultDto;
        }
        if(partialUser != null){
            partialUser.setPassword(passwordEncoder.encode(signUpFirstDto.getPassword()));
            partialUser.setPasswordCheck(passwordEncoder.encode(signUpFirstDto.getPasswordCheck()));

            request.getSession().setAttribute("partialUser",partialUser);
            resultDto.setDetailMessage("다음 단계로 넘어가세요.");
            setSuccess(resultDto);
        }else{

            setFail(resultDto);
        }
        return resultDto;
    }



    @Override
    public ResultDto SignUpSecond(String userName, String gender, String address, String nickName,MultipartFile profile_image, HttpServletRequest request)
            throws IOException{
        User partialUser = (User) request.getSession().getAttribute("partialUser");
        String imageUrl = s3Uploader.uploadImage(profile_image, "profile/");
        ResultDto resultDto = new ResultDto();

        if(partialUser != null){
            partialUser.setUserName(userName);
            partialUser.setGender(gender);
            partialUser.setAddress(address);
            partialUser.setNickName(nickName);
            partialUser.setProfileUrl(imageUrl);

            resultDto.setDetailMessage("다음 단계로 넘어가세요.");
            setSuccess(resultDto);
            authDao.save(partialUser);
        }else{

            setFail(resultDto);
        }
        return resultDto;
    }
    @Override
    public ResultDto SignIn(String email, String password) {
        // 로그인 로직
        User user = userRepository.getByEmail(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        logger.info("[getSignInResult] 패스워드 일치");

        logger.info("[getSignInResult] SignInResultDto 객체 생성");
        SignInResultDto signInResultDto = new SignInResultDto().builder()
                .token(jwtProvider.createToken(String.valueOf(user.getEmail()), user.getRoles()))
                .build();
        logger.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccess(signInResultDto);
        signInResultDto.setDetailMessage("로그인 성공");
        return signInResultDto;
    }

    private MimeMessage createMessage(String to, String ePw) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject("이메일 인증");
        String msgg = "";
        msgg += "<div style='margin:20px;'>";
        msgg += "<h1> OVER-DOSE </h1>";
        msgg += "<br>";
        msgg += "<p>인증번호 입니다.</p>";
        msgg += "<br>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += ePw + "</strong><div><br/> ";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("nankys0510@naver.com", "OverDose"));//보내는 사람

        return message;
    }

    public static String createKey() {
        int number = (int) (Math.random() * 90000) + 100000;
        return String.valueOf(number);
    }
    private void setSuccess(ResultDto resultDto) {
        resultDto.setSuccess(true);
        resultDto.setCode(CommonResponse.SUCCESS.getCode());
        resultDto.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFail(ResultDto resultDto) {
        resultDto.setSuccess(false);
        resultDto.setCode(CommonResponse.Fail.getCode());
        resultDto.setMsg(CommonResponse.Fail.getMsg());
    }
}
