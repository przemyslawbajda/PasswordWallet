package com.wallet.demo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharePasswordRequest {

    private Long userId;
    private Long passwordId;
}
