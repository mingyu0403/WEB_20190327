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
    private String password;

    @CreationTimestamp
    private LocalDateTime joined;

    @UpdateTimestamp
    private LocalDateTime modified;

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, String filePath, String fileName){
        this.username = username;
        this.email = email;
        this.password = password;
        this.profileImagePath = filePath;
        this.profileImageName = fileName;
    }

    public User(String profileImagePath, String profileImageName, String username, String email) {
        this.profileImagePath = profileImagePath;
        this.profileImageName = profileImageName;
        this.username = username;
        this.email = email;
    }
}
