package kr.co.sboard.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCheckDTO {

    private String type;
    private String value;


}
