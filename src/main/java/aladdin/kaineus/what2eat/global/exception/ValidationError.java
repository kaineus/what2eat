package aladdin.kaineus.what2eat.global.exception;

import lombok.Builder;

@Builder
public record ValidationError(String fieldName, String errorMessage) {
}
