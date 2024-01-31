package de.aittr.g_31_2_shop.exception_handling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 2 способ - размещение аннотации на самом классе эксепшена
// Минус - не можем отправить клиенту какое-то информативное сообщение
// Плюс - это глобальный обработчик ошибок, который реагирует на ошибки,
// выброшенные в любом месте приложения.
@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class SecondTestException extends RuntimeException {
    public SecondTestException(String message) {
        super(message);
    }
}
