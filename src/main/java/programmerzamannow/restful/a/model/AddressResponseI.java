package programmerzamannow.restful.a.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseI {

    private String id;

    private String street;

    private String city;

    private String province;

    private String country;

    private String postalCode;
}
