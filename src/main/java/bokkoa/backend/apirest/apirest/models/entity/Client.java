package bokkoa.backend.apirest.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
// import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


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

    @NotNull(message = "Region can't be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;
    
    @JsonIgnoreProperties(value = {"client", "hibernateLazyInitializer", "handler"}, allowSetters = true )
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private List<Bill> bills;


    public Client(){
        this.bills = new ArrayList<>();
    }

    // @PrePersist
    // public void prePersist(){
    //     createdAt = new Date();
    // }

    public List<Bill> getBills() {
        return bills;
    }
    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
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
    public Region getRegion() {
        return region;
    }
    public void setRegion(Region region) {
        this.region = region;
    }

    // From serializable interface
    private static final long serialVersionUID = 1L;
    

}
