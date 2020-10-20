package java_testers.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Words {

    String[] parsedString;
    int[] counters;
    private String id;

    public Words(String stringToParse) {
        parsedString = stringToParse.split(" ");
        for (String s : parsedString) {
            s = s.replaceAll("[^A-z]+", "");
        }
        counters = new int[parsedString.length];
    }

    public Words() {

    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
    public String[] getParsedString() {
        return parsedString;
    }

    public void setCounters(int index, int value) {
        this.counters[index] = value;
    }

    public void setParsedString(String[] parsedString) {
        this.parsedString = parsedString;
    }
}
