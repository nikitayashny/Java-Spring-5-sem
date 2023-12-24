package bstu.yashny.nikitayashny_proj.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
@Data
@Getter
public class CarRequestNoRent {
    private long id;
    private String name;
    private String description;
    private int cost;
    private Date expirationDate;
}
