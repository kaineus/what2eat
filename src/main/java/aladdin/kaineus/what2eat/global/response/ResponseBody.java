package aladdin.kaineus.what2eat.global.response;

import aladdin.kaineus.what2eat.global.constant.SuccessCode;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static aladdin.kaineus.what2eat.global.constant.SuccessCode.SUCCESS;


@Getter
public class ResponseBody {

    private final String timestamp;
    private final HttpStatus status;
    private final String message;

    public ResponseBody(HttpStatus status, String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.timestamp = now.format(formatter);
        this.status = status;
        this.message = message;
    }

    public static ResponseBody of() {
        return new ResponseBody(SUCCESS.getHttpStatus(), SUCCESS.getMessage());
    }

    public static <C extends SuccessCode> ResponseBody of(@NotNull C code) {
        return new ResponseBody(code.getHttpStatus(), code.getMessage());
    }

    public static ResponseBody of(HttpStatus status, String message) {
        return new ResponseBody(status, message);
    }
}
