package marrero_ferrera_gcid_ulpgc.control;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public record TopicSubscriber(String topicName, String consumerName, String clientId) implements Subscriber {
    private static final String url = "tcp://localhost:61616";


    @Override
    public void receiveMessage(Listener listener) throws MyReceiverException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Session session;
        Topic topic;

        try {
            Connection connection = connectionFactory.createConnection();
            connection.setClientID(clientId);
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            topic = session.createTopic(topicName);
        } catch (JMSException e) {
            throw new MyReceiverException("An error occurred while connecting to ActiveMQ.", e);
        }
        MessageConsumer consumer = null;
        try {
            consumer = session.createDurableConsumer(topic, this.consumerName);
        } catch (JMSException e) {
            throw new MyReceiverException("An error occurred while creating the session.", e);
        }

        try {
            consumer.setMessageListener(message -> {
                try {
                    String txt = ((TextMessage) message).getText();
                    listener.consume(txt);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });
        } catch (JMSException e) {
            throw new MyReceiverException(e);
        }
}

    /*
    private static Connection buildConnection () {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        return connection;
    }

     */
}
