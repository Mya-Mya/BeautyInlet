package datamanager;

public enum DataBoxKey {
    BOSS,
    TIMETABLE,
    BLACKWALL,
    WEBCAM;

    @Override
    public String toString() {
        switch (this) {
            case BOSS:return"boss";
            case TIMETABLE:return "timeTable";
            case BLACKWALL:return"blackWall";
            case WEBCAM:return "webcam";
        }
        return "unknownName";
    }
}
