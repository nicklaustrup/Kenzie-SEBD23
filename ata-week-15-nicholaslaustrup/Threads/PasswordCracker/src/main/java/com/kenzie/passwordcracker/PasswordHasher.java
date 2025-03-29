package com.kenzie.passwordcracker;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A class to pre-compute hashes for all common passwords to speed up cracking the hacked database.
 *
 * Passwords are downloaded from https://github.com/danielmiessler/SecLists/tree/master/Passwords/Common-Credentials
 */
public class PasswordHasher {
    // should create the file in your workspace directory
    private static final String PASSWORDS_AND_HASHES_FILE = "./passwordsAndHashesOutput.csv";
    private static final String DISCOVERED_SALT = "salt";

    /**
     * Generates hashes for all of the given passwords.
     *
     * @param passwords List of passwords to hash
     * @return map of password to hash
     * @throws InterruptedException when a thread is interrupted
     */
    public static Map<String, String> generateAllHashes(List<String> passwords) throws InterruptedException {
        Map<String, String> passwordToHashes = Maps.newConcurrentMap();

        List<List<String>> partitionedLists = Lists.partition(passwords, passwords.size() / 4);
        for (List<String> sublist : partitionedLists){
            //new batch password hasher with smaller list of passwords
            BatchPasswordHasher batchHasher = new BatchPasswordHasher(sublist, DISCOVERED_SALT);

            //create four threads of batchhasher
            Thread thread1 = new Thread(batchHasher);
            Thread thread2 = new Thread(batchHasher);
            Thread thread3 = new Thread(batchHasher);
            Thread thread4 = new Thread(batchHasher);

            //add threads to list
            List<Thread> threadList = new ArrayList<>();
            threadList.add(thread1);
            threadList.add(thread2);
            threadList.add(thread3);
            threadList.add(thread4);

            //start / join thread list
            waitForThreadsToComplete(threadList);

            //hash passwords and add them to map
            passwordToHashes.putAll(batchHasher.getPasswordToHashes());
        }

        //return map
        return passwordToHashes;
    }

    /**
     * Makes the thread calling this method wait until passed in threads are done executing before proceeding.
     *
     * @param threads to wait on
     * @throws InterruptedException when a thread is interrupted
     */
    public static void waitForThreadsToComplete(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.start();
            thread.join();
        }
    }

    /**
     * Writes pairs of password and its hash to a file.
     * @param passwordToHashes password to hash lookup
     */
    static void writePasswordsAndHashes(Map<String, String> passwordToHashes) {
        File file = new File(PASSWORDS_AND_HASHES_FILE);
        try (
                BufferedWriter writer = Files.newBufferedWriter(file.toPath());
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)
        ) {
            for (Map.Entry<String, String> passwordToHash : passwordToHashes.entrySet()) {
                final String password = passwordToHash.getKey();
                final String hash = passwordToHash.getValue();

                csvPrinter.printRecord(password, hash);
            }
            System.out.println("Wrote output of batch hashing to " + file.getAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
