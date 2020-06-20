package br.edu.univas.cultivedemo.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "publication")
@JsonInclude(value = Include.NON_EMPTY)
public class Publication implements Serializable {

    private static final long serialVersionUID = 1460488626476913069L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String title;

	private String body;

	private LocalDateTime createdData;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "fk_product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "fk_user_id")
    private User user;
    
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
	private List<Image> images;
    
    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return LocalDateTime return the createdData
     */
    public LocalDateTime getCreatedData() {
        return createdData;
    }

    /**
     * @param createdData the createdData to set
     */
    public void setCreatedData(LocalDateTime createdData) {
        this.createdData = createdData;
    }

    /**
     * @return Product return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return List<Image> return the images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

}