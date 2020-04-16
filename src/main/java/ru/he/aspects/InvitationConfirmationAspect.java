package ru.he.aspects;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.he.mail.MailComponent;
import ru.he.models.entities.Invitation;
import ru.he.models.entities.User;
import ru.he.repositoriesJpa.UsersRepository;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@Aspect
@Component
public class InvitationConfirmationAspect {

    @Autowired
    private MailComponent mailComponent;

    @Autowired
    @Qualifier("confirmationMailProperties")
    private Properties properties;

    @Autowired
    @Qualifier("mailForInvitationConfirmationTemplate")
    private Template mailTemplate;

    @AfterReturning(value = "execution(* ru.he.services.InvitationService.invite(*))", returning = "invitation")
    public void sendConfirmationInvitation(Invitation invitation) {
//        Invitation invitation = (Invitation) joinPoint.getArgs()[0];
        User user = invitation.getInvitedUser();
        String email = user.getEmail();

        Map<String, String> mailData = new HashMap<>();
        mailData.put("confirm_id", invitation.getConfirmId());

        StringWriter mailOut = new StringWriter();

        try {
            mailTemplate.process(mailData, mailOut);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }

        System.out.println(mailOut.toString());

        mailComponent.create()
                .withContent(mailOut.toString())
                .withSubject(properties.getProperty("subject"))
                .withContentType("text/html; charset=UTF-8")
                .withReceiver(email)
                .send();
    }
}

