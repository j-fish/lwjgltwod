package fr.com.jfish.lwjgltwod.main;

import fr.com.jfish.lwjgltwod.entity.Mage;
import fr.com.jfish.lwjgltwod.utils.Shader;
import fr.com.jfish.lwjgltwod.maths.Matrix4f;
import java.io.IOException;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.system.Callback;

/**
 * @author thw
 */
public class GL2DMain {

    private GLCapabilities glCapabilities;
    private boolean running = false;
    private long window;
    private Callback debugProc;
    private Mage ent1;
    private Mage ent2;
    
    //private KeyBoardManager keyCallback;
    //private MousePositionManager mousePosCallBack;    
    //private GLFWMouseButtonCallback buttonCallback;
    //private Callback debugProc;
    
    private float dt;

    void init() throws IOException {

        if (!glfwInit()) {
            System.err.println("ERROR >> Could not initialize GLFW...");
            return;
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
        window = glfwCreateWindow(800, 600, "2Dtest LWJGL3", NULL, NULL);
        if (window == NULL) {
                System.err.println("ERRO >> Could not create GLFW window...");
                return;
        }

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            window,
            (vidmode.width() - 800) / 2,
            (vidmode.height() - 600) / 2
        );
        
        //glfwSetKeyCallback(window, new Input());

        glfwSetCursor(window, glfwCreateStandardCursor(GLFW_HAND_CURSOR));
        glfwMakeContextCurrent(window);
        glfwSwapInterval(0);
        glfwShowWindow(window);
        
        glCapabilities = GL.createCapabilities();
        
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));
        
        debugProc = GLUtil.setupDebugMessageCallback();
        
        Shader.loadAll();

        Matrix4f pr_matrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);

        Shader.DEFAULT.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.DEFAULT.setUniform1i("texture_two_d", 1);

        ent1 = new Mage("resources/mage0.png", 0f, 0f, 0f, .89f, .9f);
        ent2 = new Mage("resources/mage0.png", -1f, 0f, 0f, .9f, .9f);
    }
    
    void run() {

        try {

            init();
            loop();
            if (debugProc != null) debugProc.free();      
            //buttonCallback.free();            
            glfwDestroyWindow(window);

        } catch (final Exception eX) {
            Logger.getLogger(GL2DMain.class.getName()).log(Level.SEVERE, null, eX);
        } finally {
            glfwTerminate();
        }
    }
    
    void loop() {

        long thisTime = 0L;
        glClearColor(0.3f, 0.3f, 0.3f, 0.0f);
        
        while (!glfwWindowShouldClose(window)) {

            glfwPollEvents();
            glViewport(0, 0, 800, 600);

            update(dt);        
            render();
            glfwSwapBuffers(window);
        }
    }
    
    void render() {        
        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);  
        ent1.render();
        ent2.render();
    }
    
    void update(final float dt) {
        ent1.update();
        ent2.update();
    }
  
}
