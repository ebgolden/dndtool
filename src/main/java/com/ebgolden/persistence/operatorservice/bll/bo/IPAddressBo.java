package persistence.operatorservice.bll.bo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Builder
@Value
public class IPAddressBo {
    @Getter(AccessLevel.NONE)
    String ipAddress;

    public String getIPAddress() {
        return ipAddress;
    }
}