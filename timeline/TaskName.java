package timeline;

public enum TaskName {
    Exit,
    BlackWall;

    @Override
    public String toString() {
        switch (this){
            case Exit:return "終了";
            case BlackWall:return "黒壁";
        }
        return "なんじゃこの名前はぁ！";
    }
}
