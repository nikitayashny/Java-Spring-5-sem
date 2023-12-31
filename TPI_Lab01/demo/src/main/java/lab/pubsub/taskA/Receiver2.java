package lab.pubsub.taskA;

import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;

import javax.jms.*;

public class Receiver2 implements MessageListener {
    ConnectionFactory cf = new ConnectionFactory();
    JMSConsumer consumer;
    Receiver2() {
        try (JMSContext jmsContext = cf.createContext("admin", "admin",JMSContext.CLIENT_ACKNOWLEDGE)) {
            // При использовании этого режима JMS-провайдер автоматически подтверждает получение сообщения после его успешной доставки
            // в методе receive() или при завершении обработки сообщения в методе слушателя. Это означает, что клиент не должен явно
            // вызывать метод acknowledge() для подтверждения получения сообщения. Подтверждение происходит автоматически.
            cf.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            Destination messageTopic = jmsContext.createTopic("Ex_3");
            consumer = jmsContext.createConsumer(messageTopic);
            consumer.setMessageListener(this);
            System.out.println("Listening to Ex_3");
            while (true) {
                Thread.sleep(1000);
            }
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
        new Receiver2();
    }
}
