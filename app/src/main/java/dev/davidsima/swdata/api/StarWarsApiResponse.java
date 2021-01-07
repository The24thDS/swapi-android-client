package dev.davidsima.swdata.api;

import java.util.List;

public class StarWarsApiResponse<T> {
    private int count;
    private List<T> results;

    public int getCount() {
        return count;
    }

    public List<T> getResults() {
        return results;
    }
}
