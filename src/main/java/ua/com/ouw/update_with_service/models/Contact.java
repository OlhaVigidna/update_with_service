package ua.com.ouw.update_with_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = {"phoneList"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Size(min = 3, max = 9, message = "hello {foo.message}")
    String name;
    String email;
    String avatar;
    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "contact"
    )
    List<Phone> phoneList = new ArrayList<>();

    public Contact(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
