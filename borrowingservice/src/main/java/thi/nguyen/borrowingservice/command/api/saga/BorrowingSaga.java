package thi.nguyen.borrowingservice.command.api.saga;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import thi.nguyen.borrowingservice.command.api.command.DeleteBorrowingCommand;
import thi.nguyen.borrowingservice.command.api.events.BorrowingCreateEvent;
import thi.nguyen.commonservice.command.UpdateStatusBookCommand;
import thi.nguyen.commonservice.model.BookResponseCommonModel;
import thi.nguyen.commonservice.query.GetDetailsQuery;

@Saga
public class BorrowingSaga {
    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    private void handle(BorrowingCreateEvent event){
        System.out.println("BorrowingCreatedEvent in Saga for BookId : " + event.getBookId() + " : EmployeeId :");

        try{
            SagaLifecycle.associateWith("bookId", event.getBookId());
            GetDetailsQuery getDetailsQuery = new GetDetailsQuery(event.getBookId());

            BookResponseCommonModel bookResponseCommonModel =
                    queryGateway.query(getDetailsQuery,
                            ResponseTypes.instanceOf(BookResponseCommonModel.class)).join();
            if(bookResponseCommonModel.getReady() == true){
                UpdateStatusBookCommand command = new UpdateStatusBookCommand(event.getBookId(), false,event.getEmployeeId(),event.getId());
                commandGateway.sendAndWait(command);
            }else{
                rollBackBorrowRecord(event.getId());
                throw new Exception("Sách đã có người mượn");
            }

        }catch (Exception ex){

        }

    }

    private void rollBackBorrowRecord(String id){
        commandGateway.sendAndWait(new DeleteBorrowingCommand(id));
    }
}
