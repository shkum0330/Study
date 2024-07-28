package org.example.dao;

import org.example.dto.BookDto;

import java.util.List;

public interface BookDaoV1 {
    List<BookDto> listBook();
    BookDto detailBook(Long bookId);
    int insertBook(BookDto bookDto);
    int updateBook(BookDto bookDto);
    Long deleteBook(Long id);
}
