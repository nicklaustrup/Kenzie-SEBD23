package ata.unit.three.project.expense.lambda;

import ata.unit.three.project.App;
import ata.unit.three.project.expense.lambda.models.Expense;
import ata.unit.three.project.expense.service.DaggerExpenseServiceComponent;
import ata.unit.three.project.expense.service.ExpenseService;
import ata.unit.three.project.expense.service.ExpenseServiceComponent;
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
public class UpdateExpense implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    static final Logger log = LogManager.getLogger();
//    private ExpenseService expenseService;
//    @Inject
//    public UpdateExpense(ExpenseService expenseService){
//        this.expenseService = expenseService;
//    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        // Logging the request json to make debugging easier.
        log.info(gson.toJson(input));

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        String expenseId = input.getPathParameters().get("expenseId");

        // Your Code Here

        ExpenseServiceComponent dagger = DaggerExpenseServiceComponent.create();
        ExpenseService expenseService = dagger.expenseService();
        try {
            //TODO Deal with this
//            ExpenseService expenseService = App.expenseService();
            Expense expense = gson.fromJson(input.getBody(), Expense.class);
            if (expense == null){
                throw new InvalidExpenseException("Expense cannot be empty.");
            }
            if (expense.getAmount() < 0){
                throw new InvalidExpenseException("Expense amount cannot be less than $0.");
            }
            expenseService.updateExpense(expenseId, expense);

            return response
                    .withStatusCode(204);
        } catch (InvalidExpenseException a) {
            return response
                    .withStatusCode(400)
                    .withBody(gson.toJson(a.errorPayload()));
        }
    }
}
