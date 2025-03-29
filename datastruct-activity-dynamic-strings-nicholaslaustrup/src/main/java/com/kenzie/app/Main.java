package com.kenzie.app;

public class Main {
    /*********************************/
    /* Using Dynamic Strings    */
    /*********************************/
    
    // Complete the Following Exercises

    // Exercise One -------------------------------------------------------------------
    public static StringBuilder createStringBuilderWithName(String name) {
        // Create a StringBuilder and initialize it with the given name.
        // Return the StringBuilder object
        StringBuilder newName = new StringBuilder(name);

        return newName;
    }

    // Exercise Two -------------------------------------------------------------------
    public static String createCommaSeparatedList(String item1, String item2, String item3, String item4) {
        // Create a new StringBuilder instance
        // Using the stringBuilder and the four parameters, create comma separated list of all the items.
        // The result should look like: "Cat, Dog, Mouse, Rabbit"
        // There should be a comma and space in-between each item ", "
        //
        // Add each word separately using the .append method (don't use +)
        // Convert your StringBuilder instance into a string and return it.
        StringBuilder csv = new StringBuilder();
            csv.append(item1);
            csv.append(", ");
            csv.append(item2);
            csv.append(", ");
            csv.append(item3);
            csv.append(", ");
            csv.append(item4);


        return csv.toString();
    }

    // Exercise Three -------------------------------------------------------------------
    public static String replaceTextInString(
            String content,
            String substring,
            String replacementValue
    ){
        // Replace the substring with the value of replacementValue in the content
        //
        // For example, if the arguments are:
        //   content: "A fox jumped over a wall"
        //   subString: "fox"
        //   replacementValue: "cat"
        // Then you want to replace the word "fox" with the word "cat" inside the content.
        //
        // First, convert the content String into a StringBuilder
        // Then, using the .replace() method, swap the subString for the replacement value.
        // Google the documentation for the replace method.
        // Hint: You will first have to determine the start and end index of the substring within the content
        //
        // When you are done, return the completed String
        StringBuilder newContent = new StringBuilder(content);
        int substringLength = substring.length();
        int substring1 = newContent.indexOf(substring);
        int substring2 = substring1 + substringLength;
        newContent.replace(substring1, substring2, replacementValue);
        return newContent.toString();
    }

    // Exercise Four -------------------------------------------------------------------
    public static String createFavoriteCharactersSentence(String[] characters){
        // Convert the Array into a sentence listing the characters.
        // - The sentence should start with: "My favorite characters are "
        // - There should be a comma and space in between each item, and the work "and" before the last item.
        // - The sentence should end in a period.
        // For example, if the input array is ["Superman", "Wonder Woman", "Batman", "Godzilla"]
        // Then your String would be: "My favorite characters are Superman, Wonder Woman, Batman, and Godzilla."
        //
        // Create a new StringBuild and use this to form your String.
        // You will need to iterate over the characters array, adding each item and the necessary other characters.
        StringBuilder faveChars = new StringBuilder("My favorite characters are ");
            String joinedCharacters = String.join(", ", characters);
            faveChars.append(joinedCharacters);
        int lastComma = faveChars.lastIndexOf(",") + 1;
            faveChars.insert(lastComma, " and");
            faveChars.append(".");


        return faveChars.toString();
    }
    
    public static void main(String[] args) {
        // ----------------------------------------------------------------------------------------------
        System.out.println("Exercise One");
        String favoriteCharacter = "Westley of Florin";
        StringBuilder favoriteCharacterBuilder = new StringBuilder(favoriteCharacter);
        System.out.println(favoriteCharacterBuilder.toString());
        // This should print: Westley of Florin

        // ----------------------------------------------------------------------------------------------
        System.out.println("Exercise Two");
        String favoriteCharacters = createCommaSeparatedList("Westley", "Buttercup", "Vizzini", "Fezzik");
        System.out.println(favoriteCharacters);
        // This should Print: Westley, Buttercup, Vizzini, Fezzik

        // ----------------------------------------------------------------------------------------------
        System.out.println("Exercise Three");
        String result = replaceTextInString("He ate an entire stick of butter and felt sick...", "stick of butter", "Reese's Peanut Butter");
        System.out.println(result);
        // This should print: He ate an entire Reese's Peanut Butter and felt sick...

        // ----------------------------------------------------------------------------------------------
        System.out.println("Exercise Four");
        String sentence = createFavoriteCharactersSentence(new String[] { "Westley", "Buttercup", "Vizzini", "Fezzik" });
        System.out.println(sentence);
        // This should print: My favorite characters are Westley, Buttercup, Vizzini, and Fezzik.

        // ----------------------------------------------------------------------------------------------
        // When you are done with all the exercises, check your console output to make sure it
        // matches the expected output of each exercise and remove any extraneous System.out.println()
        // statements from your code.
    }

}
