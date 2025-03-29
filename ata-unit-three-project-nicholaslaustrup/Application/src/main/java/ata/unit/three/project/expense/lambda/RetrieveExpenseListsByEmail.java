package ata.unit.three.project.expense.lambda;

import ata.unit.three.project.App;
import ata.unit.three.project.expense.dynamodb.ExpenseItem;
import ata.unit.three.project.expense.lambda.models.ExpenseItemList;
import ata.unit.three.project.expense.service.DaggerExpenseServiceComponent;
import ata.unit.three.project.expense.service.ExpenseService;
import ata.unit.three.project.expense.service.exceptions.InvalidDataException;
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
import java.util.*;

@ExcludeFromJacocoGeneratedReport
public class RetrieveExpenseListsByEmail
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {


    static final Logger log = LogManager.getLogger();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        log.info(gson.toJson(input));

        ExpenseServiceComponent dagger = DaggerExpenseServiceComponent.create();
        ExpenseService expenseService = dagger.expenseService();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        String email = input.getQueryStringParameters().get("email");


        try {
            if (email==null){
            throw new InvalidExpenseException("Email address cannot be null");
        }
            String output = gson.toJson(expenseService.getExpenseListByEmail(email));
            return response
                    .withStatusCode(200)
                    .withBody(output);
        } catch (InvalidDataException e) {
            return response
                    .withStatusCode(400)
                    .withBody(gson.toJson(e.errorPayload()));
        }
    }
}
