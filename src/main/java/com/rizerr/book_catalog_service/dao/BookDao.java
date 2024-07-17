package com.rizerr.book_catalog_service.dao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDao {

    private @NotNull Long isbn;
    @NotNull
    private String title;
    @NotNull
    private Double price;
    @NotNull
    private String[] authorName;

}
