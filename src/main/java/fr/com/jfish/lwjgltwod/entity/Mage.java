package fr.com.jfish.lwjgltwod.entity;

import fr.com.jfish.lwjgltwod.utils.Shader;
import fr.com.jfish.lwjgltwod.abstraction.LWJGLTwoDEntity;
import fr.com.jfish.lwjgltwod.maths.Matrix4f;

public class Mage extends LWJGLTwoDEntity {

    public Mage(final String defaultResouce, final float x, final float y, 
        final float z, final float zIndex, final float size) {
        
        super(defaultResouce, x, y, z, zIndex, size);
    }

    @Override
    public void update() {
        
        if (this.instanceNum == 1) {
            position.x  += .01f;
            if (position.x > 3f) position.x = -8f;
        }        
    }

    @Override
    public void render() {
        
        Shader.DEFAULT.enable();
        Shader.DEFAULT.setUniformMat4f("ml_matrix", 
            Matrix4f.translate(position).multiply(Matrix4f.rotate(rot)));
        texture.bind();
        mesh.render();
        Shader.DEFAULT.disable();
    }

}
