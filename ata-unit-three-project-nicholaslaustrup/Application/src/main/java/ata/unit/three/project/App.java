package ata.unit.three.project;

import ata.unit.three.project.expense.dynamodb.ExpenseItem;
import ata.unit.three.project.expense.dynamodb.ExpenseServiceRepository;
import ata.unit.three.project.expense.service.ExpenseService;
import ata.unit.three.project.expense.service.model.ExpenseItemConverter;
import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;
@Module
public class App {
//    @Provides
//    public static ExpenseService expenseService() {
//        return new ExpenseService(provideExpenseServiceRepository(), provideExpenseItemConverter());
//    }
    @Provides
    public static ExpenseItemConverter provideExpenseItemConverter(){
        return new ExpenseItemConverter();
    }
    @Provides
    public static ExpenseServiceRepository provideExpenseServiceRepository(){
        return new ExpenseServiceRepository();
    }
}
