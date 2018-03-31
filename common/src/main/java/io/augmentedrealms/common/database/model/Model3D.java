package io.augmentedrealms.common.database.model;

import javax.persistence.*;

@Entity
@Table(name = "model3d")
public class Model3D implements DBModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String extension;

    private String modelName;

    private String previewExtension;

    @ManyToOne
    private User publisher;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getPreviewExtension() {
        return previewExtension;
    }

    public void setPreviewExtension(String previewExtension) {
        this.previewExtension = previewExtension;
    }
}
