package marrero_ferrera_gcid_ulpgc.control;

public interface Subscriber {
    String receiveMessage() throws MyReceiverException;
}
