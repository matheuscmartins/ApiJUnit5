package br.com.dicasdeumdev.ApiJUnit5.domain;

import jakarta.persistence.*;
import lombok.*;

@Data //cria substitui o Getter Setter HashCodeEquals e ToString, porem se tiver muitos atributos deixa a API lenta
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true) //não pode ter mesmo e-mail
    private String email;
    private String password;

}
