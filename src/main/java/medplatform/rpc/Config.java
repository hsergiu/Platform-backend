package medplatform.rpc;

import medplatform.services.MedicationPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class Config {

    @Autowired
    private MedicationPlanService medicationPlanService;

    @Bean
    public MedicationPlanServiceInterface planService() {
        return medicationPlanService;
    }

    @Bean(name = "/planservice")
    public RemoteExporter exporter() {
        HttpInvokerServiceExporter hse = new HttpInvokerServiceExporter();
        hse.setService(planService());
        hse.setServiceInterface(MedicationPlanServiceInterface.class);
        return hse;
    }
}
