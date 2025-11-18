package engine4j.core;

import engine4j.util.SafeList;

public class E4JSceneManager {
    protected E4JRenderContext rendeContext;
    protected SafeList<E4JScene> scenes;
    protected E4JScene activeScene;
    protected SafeList<E4JScene> removeSceneQueue;

    public E4JSceneManager() {
        scenes = new SafeList<>();
    }

    public E4JRenderContext getRenderContext() {
        return rendeContext;
    }

    public void setRenderContext(E4JRenderContext rendeContext) {
        this.rendeContext = rendeContext;
    }

    public SafeList<E4JScene> getScenes() {
        return scenes;
    }

    public void addScene(E4JScene scene) {
        if (scene != null) {
            if (!scenes.contains(scene)) {
                scenes.add(scene);
                scene.start();
            }
        }
    }

    public void removeScene(E4JScene scene) {
        if (scene != null) {
            if (scenes.contains(scene)) {
                removeSceneQueue.add(scene);
            }
        }
    }

    public void removeScene(String sceneId) {
        for (int i = 0; i < scenes.getSize(); i++) {
            E4JScene scene = scenes.get(i);

            if (scene != null) {
                if (scene.getId().equals(sceneId)) {
                    removeSceneQueue.add(scene);
                    break;
                }
            }
        }
    }

    public E4JScene getScene(String sceneId) {
        for (int i = 0; i < scenes.getSize(); i++) {
            E4JScene scene = scenes.get(i);

            if (scene != null) {
                if (scene.getId().equals(sceneId)) {
                    return scene;
                }
            }
        }

        return null;
    }

    public void loadScene(String filePath) {

    }

    public void validateScenes(float deltaTime) {
        // Validate requests to move scenes
        if (!removeSceneQueue.isEmpty()) {
            for (int i = 0; i < removeSceneQueue.getSize(); i++) {
                E4JScene scene = removeSceneQueue.get(i);

                scene.exit();
                scenes.remove(scenes.indexOf(scene));
            }

            removeSceneQueue.clear();
        }

        // Validate existing scenes
        for (int i = 0; i < scenes.getSize(); i++) {
            E4JScene scene = scenes.get(i);

            if (scene != null) {
                scene.update(deltaTime);
            }
        }
    }
}
