package com.ebgolden.common;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Die {
    int numberOfSides;
    int roll;
}