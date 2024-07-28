package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.dao.BookDaoV1;
import org.example.dto.BookDto;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MainV1 {
    public static void main(String[] args) throws IOException {

        Reader reader = Resources.getResourceAsReader("config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();

        BookDaoV1 bookDao = session.getMapper(BookDaoV1.class);

        // 목록 조회
        List<BookDto> bookList = bookDao.listBook();
        for (BookDto bookDto : bookList) {
            System.out.println(bookDto);
        }

        // 상세조회
        BookDto bookDetailDto = bookDao.detailBook(1L);
        System.out.println(bookDetailDto);

        // 등록
        BookDto bookInsertDto = new BookDto(11L, "11번째 책", "축신두", 5000);
        System.out.println(bookDao.insertBook(bookInsertDto));
        session.commit();

        // 수정
        BookDto bookUpdateDto = new BookDto(5L, "ㅋㅋㅋㅋㅋㅋㅋㅋㅋ", "호날두", 50000);
        int ret = bookDao.updateBook(bookUpdateDto);
        System.out.println(ret);
        session.commit();

        System.out.println(bookDao.deleteBook(11L));
        session.commit();

        session.close();
    }
}