import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FolderSize {
    public static void main(String[] args) {
        File folder = new File("C:\\Users\\Panteley\\Downloads\\CDmusic");
        long size = getFolderSize(folder);
        System.out.println("\nAll folders size is: ~" + (size / 1024 / 1024) + " MB");
    }

    private static long getFolderSize(File folder) {

        File[] files = folder.listFiles();
        long fSize = 0;

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fName = files[i].getName();
                long lastMod = files[i].lastModified();
                String lModFormat;
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(lastMod);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
                lModFormat = dateFormat.format(calendar.getTime());

                if (files[i].isDirectory()) {
                    System.out.println("\n[" + fName + "]");
                }
                if (files[i].isFile()) {
                    fSize += files[i].length();
                    System.out.println("    " + (i + 1) + ". " + fName + " | (" + lModFormat + ") | ~" + (files[i].length() / 1024 / 1024) + " MB.");
                } else {
                    fSize += getFolderSize(files[i]);
                }
            }
        }
        return fSize;
    }
}