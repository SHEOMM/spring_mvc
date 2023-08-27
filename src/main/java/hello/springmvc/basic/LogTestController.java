package hello.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // 롬복 어노테이션
@RestController
public class LogTestController {
    // 롬복 어노테이션으로 대체
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        System.out.println("name = " + name);
        // log.trace("trace my log + " + name); // 이런식으로 사용하면 안됨
        // 왜 안되는가? 컴파일 타임에 미리 합치는 작업이 일어나기 때문에
        // log.trace("trace my log = {}", name); 이런식으로 사용해야함
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);

        return "ok";
    }
}
