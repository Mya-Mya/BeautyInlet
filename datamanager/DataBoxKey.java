package datamanager;

public enum DataBoxKey {
    BOSS,
    TIMETABLE,
    BLACKWALL;

    @Override
    public String toString() {
        switch (this) {
            case BOSS:return"boss";
            case TIMETABLE:return "timeTable";
            case BLACKWALL:return"blackWall";
        }
        return "unknownName";
    }
}