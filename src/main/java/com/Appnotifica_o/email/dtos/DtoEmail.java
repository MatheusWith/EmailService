package com.Appnotifica_o.email.dtos;

import java.util.UUID;

public record DtoEmail(
        UUID id,
        String emailTo,
        String subject,
        String message
) {
}
