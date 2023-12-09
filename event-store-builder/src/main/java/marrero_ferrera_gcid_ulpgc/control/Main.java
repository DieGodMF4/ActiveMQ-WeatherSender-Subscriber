package marrero_ferrera_gcid_ulpgc.control;

public class Main {
    public static void main(String[] args) throws MyReceiverException {
        TopicSubscriber subscriber = new TopicSubscriber("prediction.Weather");
        subscriber.receiveMessage();

    }
}