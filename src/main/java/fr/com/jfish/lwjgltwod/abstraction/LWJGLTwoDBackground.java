package fr.com.jfish.lwjgltwod.abstraction;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import fr.com.jfish.lwjgltwod.utils.Texture;
import fr.com.jfish.lwjgltwod.utils.VertexArray;

public class LWJGLTwoDBackground extends AbstractTwoD {

	protected static int instance = 0;
    protected int instanceNum;
    protected float rot = 0f;
    protected float delta = 0.0f;

    public LWJGLTwoDBackground(final String defaultResouce, 
        final float x, final float y, final float z, final float zIndex, final float size,
        final float viewWidth, final float viewHeight) {
        
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
        this.zIndex = zIndex;
        
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(defaultResouce));
        } catch (final IOException iOEx) {
            Logger.getLogger(Texture.class.getName()).log(Level.SEVERE, null, iOEx);
        }
                
        /*this.vertices = new float[] {
            0f, 0f, this.zIndex,
            viewWidth, 0f, this.zIndex,
            viewWidth, viewHeight, this.zIndex,
            0f, viewHeight, this.zIndex
        };*/
        
        this.vertices = new float[] {
			-8f, -8f * 9.0f / 16.0f, 0.0f,
			-8f,  8f * 9.0f / 16.0f, 0.0f,
			  0.0f,  8f * 9.0f / 16.0f, 0.0f,
			  0.0f, -8f * 9.0f / 16.0f, 0.0f
		};

        this.indices = new byte[] {
            0, 1, 2,
            2, 3, 0
        };

        this.textureCoordinates = new float[] {
            0, 1,
            0, 0,
            1, 0,
            1, 1
        };
        
        this.mesh = new VertexArray(vertices, indices, textureCoordinates);
        this.texture = new Texture(image);
        
        this.instanceNum = ++LWJGLTwoDBackground.instance;
    }
	
    @Override
    public void update() { }

    @Override
    public void render() { }
	
}
