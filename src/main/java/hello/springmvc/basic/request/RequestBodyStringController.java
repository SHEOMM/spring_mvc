package hello.springmvc.basic.request;


import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
        //  OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
        //  - 문자로 읽을 때 Reader 사용
        //  - 바이트로 읽을 때 InputStream 사용
        //  - 문자로 쓸 때 Writer 사용
        //  - 바이트로 쓸 때 OutputStream 사용
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {
        // HttpEntity: HTTP header, body 정보를 편리하게 조회
        //  - 요청 파라미터를 조회하는 기능과 관계 없음
        //  - 응답에도 사용 가능
        //  - 메시지 바디 정보 직접 반환
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new ResponseEntity<String>("ok", HttpStatus.CREATED);
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        // 실무에서 많이 사용하는 방식

        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
