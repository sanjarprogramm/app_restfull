package uz.qbg.apprestFull.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {


     @NotNull(message = "ism bo'sh bo'lishi mumkin emas")
    private String fulName;

    @NotNull(message = "telefon raqam bosh bulishi mumkin emas")
    private String phoneNumber;

    @NotNull(message = "address bo'sh bulishi mumkin emas")
    private String address;
}
