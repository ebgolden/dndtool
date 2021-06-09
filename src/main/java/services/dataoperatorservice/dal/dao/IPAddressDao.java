package services.dataoperatorservice.dal.dao;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Builder
@Value
public class IPAddressDao {
    @Getter(AccessLevel.NONE)
    String ipAddress;

    public String getIPAddress() {
        return ipAddress;
    }
}