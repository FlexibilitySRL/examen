package ar.com.plug.examen.app.rest.model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "client")
public class Client {
        @Id
        @Column(name = "id", updatable = false, nullable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "email")
        private String email;

        @Column(name = "active", nullable = false)
        private Boolean active;

        public Client() {
        }

        public Client(String name, String email, Boolean active) {
                this.name = name;
                this.email = email;
                this.active = active;
        }

        public Client(Long id, String name, String email, Boolean active) {
                this.id = id;
                this.name = name;
                this.email = email;
                this.active = active;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public Boolean getActive() {
                return active;
        }

        public void setActive(Boolean active) {
                this.active = active;
        }
}
