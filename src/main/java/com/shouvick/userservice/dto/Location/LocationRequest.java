package com.shouvick.userservice.dto.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
    @NotBlank(message = "Latitude can not be empty")
    private Double latitude;
    @NotBlank(message = "Longitude can not be empty")
    private Double longitude;
}
