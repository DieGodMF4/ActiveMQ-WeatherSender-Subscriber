package marrero_ferrera_gcid_ulpgc;

public interface Subscriber {
    String receiveMessage() throws MyReceiverException;
}
