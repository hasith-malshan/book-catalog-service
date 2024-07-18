package com.rizerr.book_catalog_service.dao;

import lombok.Data;

import java.lang.reflect.Type;

@Data
public class ApiResponse {
private String message;
private Object content;
}
