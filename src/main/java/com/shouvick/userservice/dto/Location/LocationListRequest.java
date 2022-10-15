package com.shouvick.userservice.dto.Location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LocationListRequest {
    private Date createdOn;
    private LocationRequest location;
}
