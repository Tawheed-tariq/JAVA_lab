import java.io.*;

class FirstPart{
    public FirstPart()  {
        try {
            createDirectory();
            createAndWrite();
            readFileContents();
            appendFile();
            readFileContents();
            deleteFile();
        } catch (IOException e) {
            System.err.println("Error in file operations: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    void createDirectory() throws IOException{
        File directory = new File("Demo");

        if(directory.exists()){
            System.out.println("Directory already exists");
        }else{
            boolean created = directory.mkdir();
            if(created){
                System.out.println("Directory created successfully");
            }else{
                throw new IOException("Failed to create directory: Demo");
            }
        }
    }

    void createAndWrite() throws IOException{
        File file  = new File("Demo", "example.txt");

        if(file.exists()){
            System.out.println("File already exists");
        }else{
            boolean created = file.createNewFile();
            if(created){
                System.out.println("example.txt created");
            }else{
                throw new IOException("Failed to create file: example.txt");
            }
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("My name is Anthony Gonsalves\n");
            writer.write("Mai duniya mein akela hoon.\n");
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + file.getPath() + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.err.println("Failed to close writer: " + e.getMessage());
            }
        }
    }

    void readFileContents() throws IOException{
        File file = new File("Demo", "example.txt");

        if(file.exists()){
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new IOException("Error reading file: " + file.getPath() + e.getMessage());
            } finally {
                try {
                    if (reader != null) reader.close();
                } catch (IOException e) {
                    System.err.println("Failed to close reader: " + e.getMessage());
                }
            }
        }else{
            System.out.println("File does not exist: " + file.getPath());
        }
    }

    void appendFile() throws IOException{
        File file = new File("Demo", "example.txt");

        if(file.exists()){
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(file, true));
                writer.write("I am a researcher in Computer vision.\n");
            } catch (IOException e) {
                throw new IOException("Error appending to file: " + file.getPath() + e.getMessage());
            } finally {
                try {
                    if (writer != null) writer.close();
                } catch (IOException e) {
                    System.err.println("Failed to close writer: " + e.getMessage());
                }
            }
        }
    }

    void deleteFile() throws IOException{
        File file = new File("Demo", "example.txt");
        File dir = new File("Demo");
        if(file.exists()){
            boolean deleted = file.delete();
            if(deleted){
                System.out.println("File deleted successfully");
            }else{
                throw new IOException("Failed to delete file: " + file.getPath());
            }
        }

        if(dir.exists()){
            boolean deleted = dir.delete();
            if(deleted){
                System.out.println("Directory deleted successfully");
            }else{
                throw new IOException("Failed to delete directory: " + dir.getPath());
            }
        }
    }
};

class SecondPart{

    SecondPart() {
        try {
            createAndWrite();
            readFileContents();
        } catch (IOException e) {
            System.err.println("Error in file operations: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    void createAndWrite() throws IOException{
        File file = new File("byteExample.txt");

        if(file.exists()){
            System.out.println("File already exists");
        }else{
            boolean created = file.createNewFile();
            if(created){
                System.out.println("byteExample.txt created");
            }else{
                throw new IOException("Failed to create file: byteExample.txt");
            }
        }

        FileOutputStream writer = null;
        try{
            writer = new FileOutputStream(file);
            String content = "hi hlo hi hlo hi hlo\n";
            writer.write(content.getBytes());
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + file.getPath() + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.err.println("Failed to close writer: " + e.getMessage());
            }
        }
    }

    void readFileContents() throws IOException{
        File file = new File("byteExample.txt");

        if(file.exists()){
            FileInputStream reader = null;
            try {
                reader = new FileInputStream(file);
                int data;
                while ((data = reader.read()) != -1) {
                    System.out.print((char) data);
                }
                System.out.println("\n");
            } catch (IOException e) {
                throw new IOException("Error reading file: " + file.getPath() + e.getMessage());
            } finally {
                try {
                    if (reader != null) reader.close();
                } catch (IOException e) {
                    System.err.println("Failed to close reader: " + e.getMessage());
                }
            }
        }
    }
}

class ThirdPart{

    public ThirdPart() {

        try {
            createAndWrite();
            readFileContents();
        } catch (IOException e) {
            System.err.println("Error in file operations: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    

    void createAndWrite() throws IOException{
        File file  = new File("characterExample.txt");

        if(file.exists()){
            System.out.println("File already exists");
        }else{
            boolean created = file.createNewFile();
            if(created){
                System.out.println("characterExample.txt created");
            }else{
                throw new IOException("Failed to create file: characterExample.txt");
            }
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            String content = "Document the code with appropriate comments and provide clear instructions on how to run the program.\n";
            writer.write(content);
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + file.getPath() + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.err.println("Failed to close writer: " + e.getMessage());
            }
        }
    }

    void readFileContents() throws IOException{
        File file = new File("characterExample.txt");

        if(file.exists()){
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                throw new IOException("Error reading file: " + file.getPath() + e.getMessage());
            } finally {
                try {
                    if (reader != null) reader.close();
                } catch (IOException e) {
                    System.err.println("Failed to close reader: " + e.getMessage());
                }
            }
        }
    }
}

class FileHandling{
    public static void main(String[] args){
        FirstPart firstPart = new FirstPart();
        SecondPart secondPart = new SecondPart();
        ThirdPart thirdPart = new ThirdPart();
    }
}