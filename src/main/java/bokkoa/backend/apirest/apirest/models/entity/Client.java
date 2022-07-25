package bokkoa.backend.apirest.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


// Creating class for CLIENT DB TABLE
@Entity
@Table(name="clients")
public class Client implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // we can use @Column decorator when we want to change
    // the column name or another properties
    @NotEmpty( message = " can't be empty :(")
    @Size(min = 4, max = 12, message="the size must be bewteen 4 to 12 letters")
    @Column(nullable = false)
    private String name;

    @NotEmpty( message = " can't be empty :(")
    private String lastName;
    
    @Column(nullable = false, unique = true)
    @NotEmpty( message = " can't be empty :(")
    @Email( message = "invalid email format")
    private String email;

    @NotNull(message = "cant be null")
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)  // Casting to DATE mysql format
    private Date createdAt;


    private String photo;
    
    // @PrePersist
    // public void prePersist(){
    //     createdAt = new Date();
    // }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
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
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // From serializable interface
    private static final long serialVersionUID = 1L;
    

}
