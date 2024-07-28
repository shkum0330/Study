package org.example;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.config.MyBatisConfig;
import org.example.dao.BookDaoV2;
import org.example.dto.BookDto;

import java.io.IOException;
import java.util.List;

public class MainV3 {
    public static void main(String[] args) throws IOException {

        SqlSessionFactory sqlSessionFactory = new MyBatisConfig().getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();

        BookDaoV2 bookDao = session.getMapper(BookDaoV2.class);

        // 목록 조회
        List<BookDto> bookList = bookDao.listBook();
        for (BookDto bookDto : bookList) {
            System.out.println(bookDto);
        }

        // 상세조회
        System.out.println(bookDao.detailBook(1L));

        // 등록
        bookDao.insertBook(new BookDto(11L, "11번째 책", "축신두", 5000));
        session.commit();

        // 수정
        bookDao.updateBook(new BookDto(5L, "ㅋㅋㅋㅋㅋㅋㅋㅋㅋ", "호날두", 50000));
        session.commit();

        bookDao.deleteBook(11L);
        session.commit();
        session.close();
    }
}
