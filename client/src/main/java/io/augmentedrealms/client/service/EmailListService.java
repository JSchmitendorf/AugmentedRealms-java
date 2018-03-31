package io.augmentedrealms.client.service;

import io.augmentedrealms.client.exception.ApiException;
import io.augmentedrealms.client.exception.ApiExceptionResponse;
import io.augmentedrealms.client.model.in.EmailRecord;
import io.augmentedrealms.common.database.repository.EmailListRepository;
import io.augmentedrealms.common.email.EmailTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailListService {

    private EmailListRepository emailListRepository;

    private JavaMailSender javaMailSender;

    private AsyncService asyncService;

    @Value("${spring.mail.username}")
    private String from;


    public EmailListService(EmailListRepository emailListRepository, JavaMailSender javaMailSender, AsyncService asyncService) {
        this.emailListRepository = emailListRepository;
        this.javaMailSender = javaMailSender;
        this.asyncService = asyncService;
    }

    public void addToEmailList(EmailRecord emailRecord) throws ApiException{
        if(!emailListRepository.existsByEmail(emailRecord.getEmail())) {
            emailListRepository.save(emailRecord.convertTo());
            asyncService.run(()->this.sendGreetingEmail(emailRecord.getEmail()));
        } else {
            throw new ApiException(ApiExceptionResponse.DUPLICATE_EMAIL);
        }
    }

    private void sendGreetingEmail(String to){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(EmailTemplate.GREETING.getSubject());
        mailMessage.setText(EmailTemplate.GREETING.getText());
        mailMessage.setFrom(from);
        javaMailSender.send(mailMessage);
    }



}
