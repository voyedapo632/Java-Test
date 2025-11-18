package engine4j.core;

public class Main {
    public static void main(String[] args) {
        E4JSceneManager sceneManager = new E4JSceneManager();
        E4JScene scene1 = new E4JScene("Scene 1", sceneManager);
        sceneManager.addScene(scene1);
        
        E4JGameObject gameObjNext = new E4JGameObject("gameObjNext", "item Next");
        gameObjNext.intitializeComponents();
        
        E4JComponent component = new E4JComponent("c1");
        component.addAttribute(new E4JAttribute("alpha", 255.0f, E4JAttribute.EDIT_ANYWARE));
        component.addAttribute(new E4JAttribute("alpha1", 255, E4JAttribute.EDIT_ANYWARE));
        
        gameObjNext.addComponent(component);
        
        E4JComponent component1 = new E4JComponent("c2");
        component1.addAttribute(new E4JAttribute("alpha", 255, E4JAttribute.EDIT_ANYWARE));
        component1.addAttribute(new E4JAttribute("alpha1", 255, E4JAttribute.EDIT_ANYWARE));
        gameObjNext.addComponent(component1);
        
        // Add the game objects
        scene1.addGameObject(gameObjNext);
        scene1.addGameObject(new E4JGameObject("gameObjNext ", "item Next"));

        // Print out the scene
        System.out.println(scene1.toString());
    }
}
