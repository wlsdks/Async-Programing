package dev.be.async.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final EmailService emailService;

    // 비동기 동작 - 스프링 컨테이너에 등록한 Bean활용
    public void asyncCall_1() {
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    // 동기식 동작
    public void asyncCall_2() {
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService1 = new EmailService();
        emailService1.sendMail();
        emailService1.sendMailWithCustomThreadPool();
    }

    // 동기식 동작 - 아래 메서드를보면 이 클래스 안에 @Async 어노테이션으로 만든 메소드를 부르기에 비동기일줄 알겠지만 이미 이 클래스는 @Service로 빈 등록이 되어있어서 스프링이 얘를 빈으로 가져오면
    // 이 클래스가 이미 빈으로 등록된채로 사용되기에 이미 빈으로 불러온 이 클래스 내부에 @Async를 단 메소드를 만들어서 빈으로 등록해서 사용하면 빈으로써 Async가 적용되지않아 동기식으로 동작한다.
    public void asyncCall_3() {
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
    }

    @Async
    public void sendMail() {
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());
    }
}
