package com.kenzie.app.classes;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Early2000sPopSinger {
    String name;
    String dna;
    String channel;
    String hair;
    ArrayList<String> groups = new ArrayList<>(); //hannah montana -> miley cyrus




    @Override
    public boolean equals(Object incomingObject) {

        //Self reflection moment - am I me?
        if (this == incomingObject) return true;

        //Null or classes not matching
        if (incomingObject == null || getClass() != incomingObject.getClass()) return false;

        Early2000sPopSinger that = (Early2000sPopSinger) incomingObject;



        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.dna, that.dna) &&
                Objects.equals(this.channel, that.channel) &&
                Objects.equals(this.hair, that.hair) &&
                Objects.equals(this.groups, that.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dna, channel, hair, groups);
    }
}
