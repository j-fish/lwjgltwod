package fr.com.jfish.lwjgltwod.abstraction;

import fr.com.jfish.lwjgltwod.utils.Texture;
import fr.com.jfish.lwjgltwod.utils.VertexArray;
import fr.com.jfish.lwjgltwod.maths.Vector3f;

/**
 * @author thw
 */
abstract class AbstractTwoD {
    
    protected float size = 0.9f;
    protected VertexArray mesh = null;
    protected Texture texture = null;
    protected float zIndex = 1f;
    protected float[] vertices = null;    
    protected byte[] indices = null;
    protected float[] textureCoordinates = null;
    protected final Vector3f position = new Vector3f();
    
    public abstract void update();
    public abstract void render();

    public AbstractTwoD() { }

    public float acquireY() {
        return position.y;
    }
    
    public float acquireX() {
        return position.x;
    }

    public float acquireZ() {
        return position.z;
    }
    
    public void affectY(final float f) {
        position.y = f;
    }
    
    public void affectX(final float f) {
        position.x = f;
    }

    public void affectZ(final float f) {
        position.z = f;
    }
    
    public float getSize() {
        return size;
    }

    public void getSize(float f) {
        size = f;
    }
    
    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getZIndex() {
        return zIndex;
    }

    public void setZIndex(float zValue) {
        this.zIndex = zValue;
    }

    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    public byte[] getIndices() {
        return indices;
    }

    public void setIndices(byte[] indices) {
        this.indices = indices;
    }

    public float[] getTextureCoordinates() {
        return textureCoordinates;
    }

    public void setTextureCoordinates(float[] textureCoordinates) {
        this.textureCoordinates = textureCoordinates;
    }
    
}
