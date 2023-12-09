package marrero_ferrera_gcid_ulpgc.control;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;

public record TopicSubscriber(String topicName) implements Subscriber {
    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    @Override
    public ArrayList<String> receiveMessage() throws MyReceiverException {
        ArrayList<String> answers = new ArrayList<>();
        try {
            Connection connection = buildConnection();
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);
            MessageConsumer consumer = session.createConsumer(topic);

            Message message = consumer.receive(6000);
            while (message != null) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    message.acknowledge();
                    answers.add(textMessage.getText());
                }
                message = consumer.receive(2000);
            }
            connection.close();
            return answers;
        } catch (
                JMSException e) {
            throw new MyReceiverException("Error receiving message from ActiveMQ", e);
        }
    }

    private static Connection buildConnection() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        return connection;
    }
}

