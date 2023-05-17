package com.example.lr20190024.clients.requests;

import com.example.lr20190024.clients.entities.Client;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientStoreRequest {
    @Nullable
    @Length(min = 2)
    private String firstName;

    @Nullable
    @Length(min = 2)
    private String lastName;

    @Nullable
    @Email
    private String email;

    @Nullable
    private String phoneNumber;


    public Client toEntity() {
        return new Client(
                this.firstName,
                this.lastName,
                this.email,
                this.phoneNumber
        );
    }
}
