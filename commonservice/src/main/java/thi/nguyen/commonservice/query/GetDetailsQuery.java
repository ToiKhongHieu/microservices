package thi.nguyen.commonservice.query;

import java.util.Date;

public class GetDetailsQuery {
    private String bookId;

    public GetDetailsQuery(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
