package medplatform.controllers;

import medplatform.TestConfig;
import medplatform.services.DoctorService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class DoctorControllerUnitTest extends TestConfig {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService service;

    @Test
    public void insertDoctorTest() throws Exception {
    }

    @Test
    public void insertDoctorTestFailsDueToAge() throws Exception {
    }

    @Test
    public void insertDoctorTestFailsDueToNull() throws Exception {
    }
}
