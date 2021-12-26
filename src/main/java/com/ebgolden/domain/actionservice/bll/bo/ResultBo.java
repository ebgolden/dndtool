package com.ebgolden.domain.actionservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import com.ebgolden.common.Result;

@Builder
@Value
public class ResultBo {
    Result result;
}