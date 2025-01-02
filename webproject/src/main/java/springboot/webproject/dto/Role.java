package springboot.webproject.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id") // 명시적으로 role_id로 설정
    private Long id;

    @Column(nullable = false, unique = true) // 이름은 반드시 있어야 하며 중복 불가
    private String roleName;

    // 기본 생성자
    public Role() {}

    // 생성자
    public Role(String roleName) {
        this.roleName = roleName;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}