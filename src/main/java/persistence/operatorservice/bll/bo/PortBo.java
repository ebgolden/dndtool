package persistence.operatorservice.bll.bo;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PortBo {
    int port;
}