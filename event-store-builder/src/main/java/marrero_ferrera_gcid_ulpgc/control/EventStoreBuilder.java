package marrero_ferrera_gcid_ulpgc.control;

public interface EventStoreBuilder {
    void storeMessage(String message) throws MyReceiverException;
}