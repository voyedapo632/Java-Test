package engine4j.core;

import java.util.HashMap;

abstract class IComponentData {
    
}

class Vec3 extends IComponentData {
    public float x, y, z;

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }
}

class Entity {
    public String entityId;
    public EntityManager entityManager;

    public Entity(String entityId, EntityManager entityManager) {
        this.entityId = entityId;
        this.entityManager = entityManager;
    }
    
    public String getEntityId() {
        return entityId;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void addComponent(IComponentData c) {
        entityManager.addComponent(this, c);
    }

    public IComponentData getComponent(Class<?> c) {
        return entityManager.getComponent(this, c);
    }

    public boolean hasComponent(Class<?> c) {
        return entityManager.hasComponent(this, c);
    }

    public boolean removeComponent(Class<?> c) {
        return entityManager.removeComponent(this, c);
    }
}

class EntityManager {
    HashMap<String, HashMap<String, IComponentData>> map;
    
    public EntityManager() { 
        map = new HashMap<>();
    }

    public boolean addComponent(Entity e, IComponentData c) {
        if (!map.containsKey(c.getClass().getSimpleName())) {
            map.put(c.getClass().getSimpleName(), new HashMap<>());
        }
        
        var componentReg = map.get(c.getClass().getSimpleName());

        if (componentReg.containsKey(e.getEntityId())) {
            return false;
        }

        componentReg.put(e.getEntityId(), c);
        return true;
    }

    
    public IComponentData getComponent(Entity e, Class<?> c) { 
        return map.get(c.getSimpleName()).get(e.getEntityId());
    }

    public boolean hasComponent(Entity e, Class<?> c) {
        var componentReg = map.get(c.getSimpleName());

        if (componentReg == null) {
            return false;
        }

        return componentReg.containsKey(e.getEntityId());
    }

    public boolean removeComponent(Entity e, Class<?> c) {
        if (hasComponent(e, c)) {
            map.get(c.getSimpleName()).remove(e.getEntityId());
            return true;
        }

        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        E4JObject obj = new E4JObject("obj");
        System.out.println(obj.toString());

        E4JSceneManager sceneManager = new E4JSceneManager();
        E4JScene scene1 = new E4JScene("Scene 1", sceneManager);

        E4JStaticMesh gameObj = new E4JStaticMesh("gameObj", "item 1");
        gameObj.intitializeComponents();
        
        E4JComponent component = new E4JComponent("c1");
        component.addAttribute(new E4JAttribute("alpha", 255.0f, E4JAttribute.EDIT_ANYWARE));
        component.addAttribute(new E4JAttribute("alpha1", 255, E4JAttribute.EDIT_ANYWARE));

        gameObj.addComponent(component);

        E4JComponent component1 = new E4JComponent("c2");
        component1.addAttribute(new E4JAttribute("alpha", 255, E4JAttribute.EDIT_ANYWARE));
        component1.addAttribute(new E4JAttribute("alpha1", 255, E4JAttribute.EDIT_ANYWARE));
        gameObj.addComponent(component1);

        E4JGameObject gameObjNext = new E4JGameObject("gameObjNext", "item Next");
        gameObjNext.intitializeComponents();
        
        // Add the game objects
        scene1.addGameObject(gameObj);
        scene1.addGameObject(gameObjNext);

        // Print out the scene
        System.out.println(scene1.toString());

        EntityManager entityManager = new EntityManager();
        Entity player = new Entity("player", entityManager);
        Entity zombie = new Entity("zombie", entityManager);
        
        player.addComponent(new Vec3(1.0f, 2.0f, 3.0f));
        zombie.addComponent(new Vec3(2.0f, 3.0f, 4.0f));

        System.out.println(player.getEntityId() + ": " + player.getComponent(Vec3.class));
        System.out.println(zombie.getEntityId() + ": " + zombie.getComponent(Vec3.class));
    }
}
