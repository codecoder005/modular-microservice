package com.popcorn.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String zipcode;
    private String state;
    private String country;
}
