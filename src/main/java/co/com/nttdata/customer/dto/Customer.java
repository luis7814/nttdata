package co.com.nttdata.customer.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Customer implements Serializable {

    private String firstName;
    private String secondName;
    private String surname;
    private String secondSurname;
    private String phone;
    private String address;
    private String cityResidence;

}
