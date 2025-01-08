package springboot.webproject.dto;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id") // 명시적으로 role_id로 설정
    private int id;

    @Column(unique = true) // 이름은 반드시 있어야 하며 중복 불가
    private String roleName;
    // 아래 코드는 gpt 한테 물어봤는데 이해가 안가는 코드. 구지 넣어야 하는지 모르겠는 코드
    @ManyToMany(mappedBy = "roles")
    private List<UsersDTO> users;
    // 기본 생성자
    public Role() {}

    // 생성자
    public Role(String roleName) {
        this.roleName = roleName;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}