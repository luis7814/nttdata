package co.com.nttdata.customer.controller;

import co.com.nttdata.customer.dto.Customer;
import co.com.nttdata.customer.exceptions.InternalServerErrorException;
import co.com.nttdata.customer.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

class CustomerControllerTest {
    @Mock
    Logger log;
    @Mock
    CustomerService customerService;
    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCustomerByNumberAndDocumentType() {
        when(customerService.findCustomerByNumberAndDocumentType(anyString(), anyString())).thenReturn(dataCustomer());

        Customer result = customerController.findCustomerByNumberAndDocumentType("C", "23445322");
        Assertions.assertNotNull(dataCustomer());
        Assertions.assertEquals(dataCustomer().getFirstName(), result.getFirstName());
    }

    @Test
    public void testErrorEndpoint() {
        Assertions.assertThrows(InternalServerErrorException.class, () -> customerController.error());
    }

    private Customer dataCustomer() {

        Customer customer = new Customer();

        customer.setFirstName("Luis");
        customer.setPhone("3150200059");

        return customer;
    }
}