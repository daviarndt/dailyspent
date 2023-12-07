package com.example.dailyspent.utils;

public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Operation carried out successfully", data);
    }

    public static <T> ApiResponse<T> exception(String message) {
        return new ApiResponse<>(false, message, null);
    }

    public static <T> ApiResponse<T> noContent() {
        return new ApiResponse<>(true, "Request was OK, but no content was found", null);
    }

    public ApiResponse(){}

    public ApiResponse(Boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
