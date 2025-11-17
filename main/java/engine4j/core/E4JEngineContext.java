package engine4j.core;

import engine4j.util.SafeList;

public class E4JEngineContext {
    protected SafeList<E4JSystem> loadedSystems;

    public E4JEngineContext() {
        loadedSystems = new SafeList<E4JSystem>();
    }

    public E4JSystem getSystem(String systemId) {
        for (int i = 0; i < loadedSystems.getSize(); i++) {
            E4JSystem system = loadedSystems.get(i);

            if (system != null) {
                if (system.getId().equals(systemId)) {
                    return system;
                }
            }
        }

        return null;
    }
}
