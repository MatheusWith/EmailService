package com.Appnotifica_o.email.models;

import com.Appnotifica_o.email.dtos.DtoEmail;
import com.Appnotifica_o.email.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "email")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID remetenteId;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String message;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

    public Email(DtoEmail data){
        this.setEmailTo(data.emailTo());
        this.setRemetenteId(data.remetenteId());
        this.setSubject(data.subject());
        this.setMessage(data.message());
    }
}
