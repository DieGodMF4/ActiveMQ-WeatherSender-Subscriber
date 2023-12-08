package marrero_ferrera_gcid_ulpgc;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public record TopicSubscriber(String topicName) implements Subscriber {
    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    @Override
    public String receiveMessage() throws MyReceiverException {
        try {
            Connection connection = buildConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);
            MessageConsumer consumer = session.createConsumer(topic);

            Message message = consumer.receive();
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("Received message '" + textMessage.getText() + "'");
                return textMessage.getText();
            } else {
                System.out.println("NO MESSAGE");
            } connection.close();
        } catch (JMSException e) {
            throw new MyReceiverException("Error receiving message from ActiveMQ", e);
        }
        return null;
    }

    private static Connection buildConnection () throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        return connection;
    }
}
