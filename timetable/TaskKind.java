package timetable;

public enum TaskKind {
    BLACKWALL,
    CONFIGWEBCAM,
    DISPLAYWEB,
    EXIT,
    TAKEWEBCAM;

    @Override
    public String toString() {
        return toString(this);
    }

    static public String toString(TaskKind k) {
        switch (k) {
            case EXIT:
                return "終了";
            case BLACKWALL:
                return "黒壁";
            case TAKEWEBCAM:
                return "撮影";
            case CONFIGWEBCAM:
                return "ウェブカメラ設定";
            case DISPLAYWEB:
                return "webページ表示";
        }
        return "不明識別子";
    }

    public static TaskKind search(String s) {
        for (TaskKind k : TaskKind.values()) {
            if (toString(k).equals(s)) return k;
        }
        return null;
    }
}
