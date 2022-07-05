package ar.com.plug.examen.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ar.com.plug.examen.app.common.ValidationMessages;
import ar.com.plug.examen.app.common.RegexPatterns;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "sellers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "document_number", unique = true, nullable = false)
    @Range(min = 1, max = 99999999)
    private Integer documentNumber;

    @Column(name = "first_name", nullable = false)
    @Size(min = 1, max = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 1, max = 30)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    @Size(min = 1, max = 8)
    @Pattern(regexp = RegexPatterns.REGEX_PHONE_NUMBER, message = "phone_number " + ValidationMessages.PHONE_NUMBER_VALID_VALUES)
    private String phoneNumber;

}
