
import java.io.*;

public class Main {

    public static void main(String[] args) {

        File studentFile = new File("C:\\Users\\david\\IdeaProjects\\Main\\src\\students.txt");

        System.out.println("File location: " + studentFile.getAbsolutePath());

        if (!studentFile.exists()) {
            System.out.println("ERROR: students.txt not found!");
            return;
        }

        // Put Comment on RandomAccessFile
        // RandomAccessFile allows the program to read and write
        // data at specific positions in a file.

        try (RandomAccessFile file =
                     new RandomAccessFile(studentFile, "rw")) {

            System.out.println("File size: "
                    + file.length() + " bytes");

            String line;
            boolean found = false;

            while (true) {

                // Put Comment on getFilePointer() method
                //getFilePointer() returns the current position in the file
                // before reading the next line.

                long position = file.getFilePointer();

                line = file.readLine();

                if (line == null) {
                    break;
                }

                System.out.println("Reading: " + line);

                // Put Comment on split() method
                // split(",") separates the student record into fields
                // using commas as separators.

                String[] data = line.split(",");

                if (data.length < 3) {
                    continue;
                }

                String studentID = data[0].trim();

                if (studentID.equals("1002")) {

                    String updated =
                            "1002,Alice Brown,3.9";

                    // Verify same byte length
                    if (updated.length() != line.length()) {
                        System.out.println(
                                "ERROR: Record length mismatch!");
                        break;
                    }

                    // Put Comment on seek(position) method
                    // seek(position) moves the file pointer back to the
                    // beginning of the student record that needs updating.
                    file.seek(position);

                    // Put Comment on writeBytes() method
                    // writeBytes() writes the updated student record into
                    // the file, replacing the original record.
                    file.writeBytes(updated);

                    System.out.println("Record updated!");

                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println(
                        "Student ID 1002 was not updated.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
