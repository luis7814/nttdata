package co.com.nttdata.customer.service;

import co.com.nttdata.customer.dto.Customer;
import co.com.nttdata.customer.exceptions.BadRequestException;
import co.com.nttdata.customer.exceptions.NotFoundException;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    Logger log = LoggerFactory.getLogger(CustomerService.class);

    private static final String CITIZENSHIP_CARD = "C";
    private static final String PASSPORT = "P";
    private static final String DOCUMENT_NUMBER = "23445322";

    public Customer findCustomerByNumberAndDocumentType(String documentType, String documentNumber) {

        log.info("Validación de la data ingresada");
        validateData(documentType, documentNumber);

        log.info("Cargue de la información a retornar");
        Customer customer = loadData();

        return customer;
    }

    private void validateData(String documentType, String documentNumber) {

        if (StringUtils.isNotBlank(documentType) && StringUtils.isNotBlank(documentNumber)) {
            if (documentType.equals(CITIZENSHIP_CARD) || documentType.equals(PASSPORT)) {
                 if (!documentNumber.equals(DOCUMENT_NUMBER)) {
                   throw new NotFoundException("Datos no encontrados");
                }
            } else {
                throw new BadRequestException("El tipo de documento debe ser Cédula de ciudadania o Pasaporte.");
            }
        } else {
            throw new BadRequestException("El tipo y número de documento son obligatorios.");
        }
    }

    private Customer loadData() {

        return Customer.builder()
                .firstName("Luis")
                .secondName("Alberto")
                .surname("Barrera")
                .secondSurname("Segura")
                .phone("3150200059")
                .address("Calle Siempre Viva 123")
                .cityResidence("Springfield")
                .build();

    }
}
