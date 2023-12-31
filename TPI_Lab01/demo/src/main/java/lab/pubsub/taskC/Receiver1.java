package lab.pubsub.taskC;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;
// durable при отключении сохраняет сообщения
public class Receiver1 implements MessageListener {
    ConnectionFactory cf = new ConnectionFactory();
    JMSConsumer consumer;
    Receiver1() {
        try (JMSContext jmsContext = cf.createContext("admin", "admin")) {
            cf.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            jmsContext.setClientID("client123");
            Destination messageTopic = jmsContext.createTopic("Ex_3c");
            consumer = jmsContext.createDurableConsumer((Topic)messageTopic,"mistake");
            consumer.setMessageListener(this);
            System.out.println("Listening to Ex_3c");
            Thread.sleep(100000);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Message:" + message.getBody(String.class));
        } catch (JMSException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Receiver1();
    }
}
