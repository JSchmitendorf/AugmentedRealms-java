package io.augmentedrealms.client.model.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.ResourceSupport;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Model3D extends ResourceSupport{

    private String name;

    private Player publisher;

    private Model3D(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPublisher() {
        return publisher;
    }

    public void setPublisher(Player owner) {
        this.publisher = owner;
    }

    public static Model3D getInstance(io.augmentedrealms.common.database.model.Model3D model3DDBModel) {
        if (model3DDBModel == null) {
            return null;
        }
        Model3D model3D = new Model3D();
        model3D.setName(model3DDBModel.getModelName());
        model3D.setPublisher(Player.getInstance(model3DDBModel.getPublisher().getPlayer()));
        return model3D;
    }
}
