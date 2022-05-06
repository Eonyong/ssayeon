package a204.ssayeon.db.entity.user;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@DynamicInsert
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String classId;

    @Column(nullable = false)
    private String password;

    private String company;

    private String picture;
    private Boolean isAlarm;

    public void setIsAlarm(){
        if(isAlarm)
            isAlarm=false;
        else
            isAlarm=true;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setNickname(String nickname){
        this.nickname=nickname;
    }

    public void setPicture(String picture){
        this.picture=picture;
    }

    public void setCompany(String company){
        this.company=company;
    }


}
