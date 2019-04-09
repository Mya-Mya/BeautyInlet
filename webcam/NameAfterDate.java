package webcam;

import java.util.Calendar;

public class NameAfterDate implements WebcamSaver {
    @Override
    public String getFileName() {
        Calendar today = Calendar.getInstance();
        StringBuilder value = new StringBuilder();

        value.append(today.get(Calendar.YEAR));
        value.append("_");
        value.append(today.get(Calendar.MONTH) + 1);
        value.append("_");
        value.append(today.get(Calendar.DATE));
        value.append("_");
        value.append(today.get(Calendar.HOUR_OF_DAY));
        value.append("_");
        value.append(today.get(Calendar.MINUTE));
        return value.toString();
    }

}
