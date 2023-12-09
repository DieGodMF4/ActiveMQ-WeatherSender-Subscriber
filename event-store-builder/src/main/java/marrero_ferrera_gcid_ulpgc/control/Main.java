package marrero_ferrera_gcid_ulpgc.control;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws MyReceiverException {
        String topicName = (args.length > 0) ? args[0] : "prediction.Weather";
        TopicSubscriber subscriber = new TopicSubscriber(topicName);
        ArrayList<String> messages = subscriber.receiveMessage();

        FileEventStoreBuilder builder = new FileEventStoreBuilder();
        assert messages.isEmpty();
        builder.storeMessages(messages);
    }
}