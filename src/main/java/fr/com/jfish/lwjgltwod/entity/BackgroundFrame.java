package fr.com.jfish.lwjgltwod.entity;

import fr.com.jfish.lwjgltwod.abstraction.LWJGLTwoDBackground;
import fr.com.jfish.lwjgltwod.maths.Matrix4f;
import fr.com.jfish.lwjgltwod.utils.Shader;

public class BackgroundFrame extends LWJGLTwoDBackground {

    public BackgroundFrame(final String defaultResouce, final float x, final float y, 
        final float z, final float zIndex, final float size, final float viewWidth, final float viewHeight) {        
        super(defaultResouce, x, y, z, zIndex, size, viewWidth, viewHeight);
    }

    @Override
    public void update() { }

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
