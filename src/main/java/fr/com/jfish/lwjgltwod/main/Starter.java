package fr.com.jfish.lwjgltwod.main;

/**
 *
 * @author thw
 */
public class Starter {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Use -fullscreen for fullscreen mode");
        if (args.length > 0) appendArgs(args);
        final GL2DMain game = new GL2DMain();
        game.run();
        System.exit(0);
    }
    
    private static void appendArgs(final String[] args) {
        // Deal with launch args.
    }
    
}
