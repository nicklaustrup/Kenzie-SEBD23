package ata.unit.three.project.expense.lambda;

import ata.unit.three.project.App;
import ata.unit.three.project.expense.service.DaggerExpenseServiceComponent;
import ata.unit.three.project.expense.service.ExpenseService;
import ata.unit.three.project.expense.service.ExpenseServiceComponent;
import ata.unit.three.project.expense.service.exceptions.InvalidDataException;
import ata.unit.three.project.expense.service.exceptions.InvalidExpenseException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenzie.ata.ExcludeFromJacocoGeneratedReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

@ExcludeFromJacocoGeneratedReport
public class DeleteExpense implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    static final Logger log = LogManager.getLogger();
//    private ExpenseService expenseService;
//    @Inject
//    public DeleteExpense(ExpenseService expenseService){
//        this.expenseService = expenseService;
//    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        // Logging the request json to make debugging easier.
        log.info(gson.toJson(input));

        String expenseId = input.getPathParameters().get("expenseId");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        // Your Code Here
        ExpenseServiceComponent dagger = DaggerExpenseServiceComponent.create();
        ExpenseService expenseService = dagger.expenseService();


        try {
            expenseService.deleteExpense(expenseId);
            return response
                    .withStatusCode(204);
        } catch (InvalidExpenseException e) {
            return response
                    .withStatusCode(400)
                    .withBody(gson.toJson(e.errorPayload()));
        }
    }
}
