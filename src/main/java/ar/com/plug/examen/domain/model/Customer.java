package ar.com.plug.examen.domain.model;


import lombok.Getter;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GenerationType;

@Entity
@Table(name = "customer")
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private String sex;

    private String age;

    private String document;

    private String address;

    private String email;

    private Boolean deleteCustomer = false;

    private Customer(){}

    public static class Builder {

        private Customer customer;

        public Builder() {
            customer = new Customer();
        }

        public Builder withId(final Long id) {
            customer.id = id;
            return this;
        }

        public Builder withName(final String name){
            customer.name = name;
            return this;
        }

        public Builder withLastName(final String lastName) {
            customer.lastName = lastName;
            return this;
        }

        public Builder withAddress(final String address) {
            customer.address = address;
            return this;
        }

        public Builder withDocument(final String document){
            customer.document = document;
            return this;
        }

        public Builder withEmail(final String email) {
            customer.email = email;
            return this;
        }

        public Builder withAge(final String age) {
            customer.age = age;
            return this;
        }

        public Builder withSex(final String sex) {
            customer.sex = sex;
            return this;
        }

        public Customer build(){
            return customer;
        }
    }


}
