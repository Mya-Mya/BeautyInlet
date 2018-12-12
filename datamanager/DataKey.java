package datamanager;

public enum DataKey {
    BOSS,
    TIMELINE,
    BLACKWALL;

    @Override
    public String toString() {
        switch (this) {
            case BOSS:return"boss";
            case TIMELINE:return "timeLine";
            case BLACKWALL:return"blackWall";
        }
        return "unknownName";
    }
}
