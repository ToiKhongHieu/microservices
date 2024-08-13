package thi.nguyen.query.projection;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thi.nguyen.command.data.BookRepository;
import thi.nguyen.command.data.Books;
import thi.nguyen.commonservice.query.GetDetailsQuery;
import thi.nguyen.query.model.BookResponseModel;
import thi.nguyen.query.queries.GetAllBooksQuery;
import thi.nguyen.query.queries.GetBooksQuery;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookProjection {

    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public BookResponseModel handle(GetBooksQuery getBooksQuery) {
        BookResponseModel model = new BookResponseModel();
        Books book = bookRepository.getById(getBooksQuery.getBookId());
        BeanUtils.copyProperties(book, model);

        return model;
    }

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBooksQuery getAllBooksQuery) {
        List<Books> listEntity = bookRepository.findAll();
        List<BookResponseModel> listBook = new ArrayList<>();
        listEntity.forEach(s -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(s,model);
            listBook.add(model);
        });
        return listBook;
    }

    @QueryHandler
    public BookResponseModel handle(GetDetailsQuery getDetailsQuery){
        BookResponseModel model = new BookResponseModel();
        Books book = bookRepository.getById(getDetailsQuery.getBookId());
        BeanUtils.copyProperties(book,model);
        return model;
    }
}
