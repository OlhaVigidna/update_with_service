package ua.com.ouw.update_with_service.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = {"contact"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String number;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Contact contact;

    public Phone(String number) {
        this.number = number;
    }
}
