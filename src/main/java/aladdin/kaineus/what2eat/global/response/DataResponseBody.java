package aladdin.kaineus.what2eat.global.response;

import aladdin.kaineus.what2eat.global.constant.SuccessCode;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

import static aladdin.kaineus.what2eat.global.constant.SuccessCode.SUCCESS;


public class DataResponseBody<T> extends ResponseBody {

    private final T data;

    public DataResponseBody(HttpStatus status, String message, T data) {
        super(status, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public static <T> DataResponseBody<T> of(@NotNull T data) {
        return new DataResponseBody<>(SUCCESS.getHttpStatus(), SUCCESS.getMessage(), data);
    }

    public static <T> DataResponseBody<T> of(HttpStatus status, String message, T data) {
        return new DataResponseBody<>(status, message, data);
    }

    public static <C extends SuccessCode, T> DataResponseBody<T> of(@NotNull C code, T data) {
        return new DataResponseBody<>(code.getHttpStatus(), code.getMessage(), data);
    }

}
