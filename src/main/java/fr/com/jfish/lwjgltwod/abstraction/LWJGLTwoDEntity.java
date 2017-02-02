package fr.com.jfish.lwjgltwod.abstraction;

import fr.com.jfish.lwjgltwod.utils.Texture;
import fr.com.jfish.lwjgltwod.utils.VertexArray;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author thw
 */
public class LWJGLTwoDEntity extends AbstractTwoD {
    
    protected static int instance = 0;
    protected int instanceNum;
    protected float rot = 0f;
    protected float delta = 0.0f;

    public LWJGLTwoDEntity(final String defaultResouce, 
        final float x, final float y, final float z, final float zIndex, final float size) {
        
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
                
        this.vertices = new float[] {
            -size / 2.0f, -size / 2.0f, this.zIndex,
            -size / 2.0f, size / 2.0f, this.zIndex,
            size / 2.0f, size / 2.0f, this.zIndex,
            size / 2.0f, -size / 2.0f, this.zIndex
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
        
        this.instanceNum = ++LWJGLTwoDEntity.instance;
    }
    
    @Override
    public void update() { }

    @Override
    public void render() { }
    
}
