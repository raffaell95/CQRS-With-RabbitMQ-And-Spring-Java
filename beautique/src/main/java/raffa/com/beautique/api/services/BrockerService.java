package raffa.com.beautique.api.services;

public interface BrockerService {

    public void send(String type, Object message);
}
