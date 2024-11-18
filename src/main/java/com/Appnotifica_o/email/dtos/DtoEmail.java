package com.Appnotifica_o.email.dtos;

import java.util.UUID;

public record DtoEmail(
        UUID id,
        UUID remetenteId,
        String emailTo,
        String subject,
        String message
) {
}
