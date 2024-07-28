package org.example.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.dto.BookDto;

import java.util.List;

public interface BookDaoV2 {
    @Select("select bookid bookId, bookname bookName, publisher, price from book")
    List<BookDto> listBook();

    @Select("select bookid bookId, bookname bookName, publisher, price from book where bookid = #{bookId}")
    BookDto detailBook(Long bookId);

    @Insert("insert into book (bookid, bookname, publisher, price) values ( #{bookId}, #{bookName}, #{publisher}, #{price} )")
    int insertBook(BookDto bookDto);

    @Update("update book "
            + "set bookname = #{bookName},publisher = #{publisher},price = #{price} "
            + "where bookid = #{bookId}")
    Long updateBook(BookDto bookDto);

    @Delete("delete from book "
            + "where bookid = #{bookId}")
    Long deleteBook(Long bookId);
}
