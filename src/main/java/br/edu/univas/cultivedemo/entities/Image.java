package br.edu.univas.cultivedemo.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "image")
public class Image implements Serializable{
   
    private static final long serialVersionUID = -6679251683416812211L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private byte[] image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_publication_id")
    @JsonIgnore(value = true)
	private Publication publication;

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
     * @return byte[] return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }


    /**
     * @return Publication return the publicacation
     */
    public Publication getPublication() {
        return publication;
    }

    /**
     * @param publication the publication to set
     */
    public void setPublication(Publication publication) {
        this.publication = publication;
    }

}