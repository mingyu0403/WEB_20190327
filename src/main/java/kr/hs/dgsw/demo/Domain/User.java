package kr.hs.dgsw.demo.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String profileImagePath;
    private String profileImageName;

    private String username;
    private String email;

    @CreationTimestamp
    private LocalDateTime joined;

    @UpdateTimestamp
    private LocalDateTime modified;

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public User(String profileImagePath, String profileImageName, String username, String email) {
        this.profileImagePath = profileImagePath;
        this.profileImageName = profileImageName;
        this.username = username;
        this.email = email;
    }
}
