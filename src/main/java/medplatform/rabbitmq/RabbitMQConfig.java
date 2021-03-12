package medplatform.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import medplatform.repositories.MonitoredDataRepository;
import medplatform.services.PatientService;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue}")
    String queueName;

    @Value("${spring.rabbitmq.username}")
    String username;

    private final PatientService patientService;

    private final MonitoredDataRepository monitoredDataRepository;

    private final SimpMessageSendingOperations socketTemplate;

    @Autowired
    public RabbitMQConfig(PatientService patientService, SimpMessageSendingOperations socketTemplate, MonitoredDataRepository monitoredDataRepository) {
        this.patientService = patientService;
        this.socketTemplate = socketTemplate;
        this.monitoredDataRepository = monitoredDataRepository;
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory ) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(queue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQListener(patientService, monitoredDataRepository, socketTemplate));
        return simpleMessageListenerContainer;

    }
}
