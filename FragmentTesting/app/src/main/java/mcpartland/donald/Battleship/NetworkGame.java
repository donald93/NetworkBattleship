package mcpartland.donald.Battleship;

/**
 * Created by Donald on 11/11/2014.
 */
public class NetworkGame {
    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    private String id;
    private String name;
    private String status;
    public NetworkGame(String id, String name, String status)
    {
        this.id = id;
        this.name = name;
        this.status = status;
    }
}
