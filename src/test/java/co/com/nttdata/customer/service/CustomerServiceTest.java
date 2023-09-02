package co.com.nttdata.customer.service;

import co.com.nttdata.customer.dto.Customer;
import co.com.nttdata.customer.exceptions.BadRequestException;
import co.com.nttdata.customer.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerServiceTest {
    @Mock
    private Logger log;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindCustomerByNumberAndDocumentType_ValidData_Success() {
        Customer result = customerService.findCustomerByNumberAndDocumentType("C", "23445322");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Alberto", result.getSecondName());
    }

    @Test
    public void testInvalidDocumentTypeException() {
        assertThrows(BadRequestException.class,
                () -> customerService.findCustomerByNumberAndDocumentType("D", "23445322"));
    }

    @Test
    public void testInvalidDocumentNumberException() {
        assertThrows(NotFoundException.class,
                () -> customerService.findCustomerByNumberAndDocumentType("C", "99999999"));
    }

    @Test
    public void testMissingDataException() {
        assertThrows(BadRequestException.class,
                () -> customerService.findCustomerByNumberAndDocumentType(null, null));
    }
}
