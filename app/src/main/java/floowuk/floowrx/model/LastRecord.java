package floowuk.floowrx.model;

/*** Created by igorfrankiv on 22/01/2018. */

public final class LastRecord {

    private final int id;
    private final String listOfLocations;

    public LastRecord (int id, String listOfLocations) {
        this.id = id;
        this.listOfLocations = listOfLocations;
    }

    public int getId() {
        return id;
    }

    public String getListOfLocations() {
        return listOfLocations;
    }
}
