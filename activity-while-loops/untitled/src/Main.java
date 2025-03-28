public class Main {
    public static void main(String[] args) {

    // Write your code here
    int [] numbers = {20, 10, 5, 40, 15};

    //Initialize smallestNumber to first one to start things off
    int smallestNumber = numbers[0];
    int currentNumber;
        for(int index = 0; index < numbers.length ; index++){
        //currentNumber is updated based on the current index
        currentNumber = numbers[index];
        //if the current number is smaller than the smallestNumber
        //then the smallest is reset to the current
        if(currentNumber < smallestNumber) {
         smallestNumber = currentNumber;
          }
        }
        System.out.println(smallestNumber);
    }
}