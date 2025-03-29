/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.kenzie.unit.two.iam.lambda;

import com.kenzie.unit.two.App;
import com.kenzie.unit.two.iam.models.Department;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenzie.ata.ExcludeFromJacocoGeneratedReport;

import java.util.List;
import java.util.Map;

@ExcludeFromJacocoGeneratedReport
public class GetDepartments implements RequestHandler<Map<String, String>, List<Department>> {

    // Handler value: example.Handler
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    @Override
    public List<Department> handleRequest(Map<String, String> input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("CONTEXT: " + gson.toJson(context));
        // process event
        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));

        return App.departmentService().getDepartments();
    }
}
