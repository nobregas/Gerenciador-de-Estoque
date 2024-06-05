package br.com.estoqueapi.estoqueapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private String email;

    private String role;

    @DBRef
    private List<StockMovement> stockMovements;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

}
