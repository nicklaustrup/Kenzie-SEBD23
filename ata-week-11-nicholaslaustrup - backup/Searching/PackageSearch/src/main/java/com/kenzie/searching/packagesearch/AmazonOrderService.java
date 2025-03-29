package com.kenzie.searching.packagesearch;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Manages a list of AmazonPackages.
 * Individual packages can be found by ASIN.
 */
public class AmazonOrderService {

    private List<AmazonPackage> packages;

    /**
     * Constructs an AmazonOrderService object.
     *
     * @param packages - The List of packages in the order
     */
    public AmazonOrderService(List<AmazonPackage> packages) {
        this.packages = packages;
    }

    /**
     * Does a linear search for a package in the known list of packages.
     *
     * @param asin - The ASIN being searched for.
     * @return the Amazon Package with the target ASIN
     */
    public AmazonPackage findPackageLinear(String asin) throws PackageNotFoundException {
        AmazonPackage packageFound;
        for (AmazonPackage amazonPackage : packages) {
            if (amazonPackage.getAsin().equals(asin)) {
                packageFound = amazonPackage;
                return packageFound;
            }
        }
        throw new PackageNotFoundException("Can't find it.");
    }

    /**
     * Does a binary search for a package in the known list of packages.
     * Note: You should assume that the package list is already sorted when this method is called.
     *
     * @param asin - The ASIN being searched for.
     * @return the Amazon Package with the target ASIN
     */
    public AmazonPackage findPackageBinary(String asin) throws PackageNotFoundException {
//        Collections.sort(packages);
        int start = 0;
        int end = packages.size() - 1;
        int middle;

        while (start <= end) {
            middle = start + ((end - start) / 2);
            if (packages.get(middle).getAsin().equals(asin)) {
                System.out.println("yeah buddy");
                return packages.get(middle);
            } else if (packages.get(start).getAsin().equals(asin)){
                return packages.get(start);
            } else if (packages.get(end).getAsin().equals(asin)){
                return packages.get(end);
            } else if (packages.get(middle).getAsin().compareTo(asin) < 0) {
                start = middle + 1;
            } else if (packages.get(middle).getAsin().compareTo(asin) > 0) {
                end = middle - 1;
            } else if (start == end){
                break;
            }
        }
        throw new PackageNotFoundException("Could not locate package");
    }
}
