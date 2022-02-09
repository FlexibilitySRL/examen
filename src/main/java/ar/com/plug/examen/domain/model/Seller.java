package ar.com.plug.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_sellers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The document number cannot be empty")
    @Size(min = 8, max = 8, message = "The size of the document number must be 8")
    @Unique
    @Column(name = "number_id", length = 8, nullable = false)
    private String numberId;

    @NotEmpty(message = "The firstname cannot be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "The lastname cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "photo_url")
    private String photoUrl;
}
