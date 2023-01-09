package com.wearperfect.dataservice.api.security.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class WearperfectUserPrincipal {

    Long userId;
    String username;
}
