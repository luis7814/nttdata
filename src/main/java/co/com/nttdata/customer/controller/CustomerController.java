package co.com.nttdata.customer.controller;

import co.com.nttdata.customer.dto.Customer;
import co.com.nttdata.customer.exceptions.InternalServerErrorException;
import co.com.nttdata.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public Customer findCustomerByNumberAndDocumentType(@RequestParam("documentType") String documentType,
                                                        @RequestParam("documentNumber") String documentNumber) {
        log.info("Ingresa al servicio");
        return customerService.findCustomerByNumberAndDocumentType(documentType, documentNumber);
    }

    @GetMapping("/customer/error")
    public String error() {
        log.error("Internal Server Error");
        throw new InternalServerErrorException("Internal Server Error");
     }
}
