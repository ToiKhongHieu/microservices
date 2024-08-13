package thi.nguyen.borrowingservice.command.api.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends CrudRepository<Borrowing,String> {
}
