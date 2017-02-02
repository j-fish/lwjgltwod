package fr.com.jfish.lwjgltwod.utils;

import fr.com.jfish.lwjgltwod.maths.Matrix4f;
import fr.com.jfish.lwjgltwod.maths.Vector3f;
import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

public class Shader {

    final int SIZE = 4 * 4;

    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;
    public static Shader DEFAULT;
    private boolean enabled = false;
    private final int ID;
    private final Map<String, Integer> locationCache = new HashMap<>();

    public Shader(String vertex, String fragment) {
        ID = ShaderUtils.load(vertex, fragment);
    }

    public static void loadAll() {
        DEFAULT = new Shader("resources/shaders/default_entity.vert", "resources/shaders/default_entity.frag");
    }

    public int getUniform(String name) {
        
        if (locationCache.containsKey(name)) {
            return locationCache.get(name);
        }

        int result = glGetUniformLocation(ID, name);
        
        if (result == -1) {
            System.err.println("ERROR >> Could not find uniform variable '" + name + "'!");
        } else {
            locationCache.put(name, result);
        }
        
        return result;
    }

    public void setUniform1i(String name, int value) {
        if (!enabled) {
            enable();
        }
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value) {
        if (!enabled) {
            enable();
        }
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y) {
        if (!enabled) {
            enable();
        }
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vector3f vector) {
        if (!enabled) {
            enable();
        }
        glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix) {
        if (!enabled) {
            enable();
        }
        glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable() {
        glUseProgram(ID);
        enabled = true;
    }

    public void disable() {
        glUseProgram(0);
        enabled = false;
    }

}
