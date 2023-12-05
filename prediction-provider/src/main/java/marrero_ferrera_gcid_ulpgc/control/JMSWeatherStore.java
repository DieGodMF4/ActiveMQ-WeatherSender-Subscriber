package marrero_ferrera_gcid_ulpgc.control;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public record JMSWeatherStore(String topicName) implements WeatherStore {
    private static final String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    @Override
    public void insertWeather(String jsonDataContainer) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);

        System.out.println("CONN ESTABLISHED");
        try {
            Connection connection = (Connection) factory.createConnection();
            connection.start();
            System.out.println("CONN Started");

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);
            MessageProducer producer = session.createProducer(topic);

            TextMessage message = session.createTextMessage(jsonDataContainer);
            producer.send(message);
            System.out.println("JSON message sent to topic '" + topicName + "': '" + jsonDataContainer + "'");
            connection.close();
        } catch (JMSException | jakarta.jms.JMSException e) {
            e.getCause();
        }
    }
}
