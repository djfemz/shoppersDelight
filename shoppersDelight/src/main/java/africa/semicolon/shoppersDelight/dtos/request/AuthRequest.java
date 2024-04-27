package africa.semicolon.shoppersDelight.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthRequest {
    private String username;
    private String password;

}
