import java.util.HashSet;
import java.util.Scanner;

public class Folder extends Element {
    private final HashSet<Element> children;

    //Creates an empty folder
    public Folder(Element parent, String name) {
        // super calls the constructor of Element
        super(parent, name);
        children = new HashSet<>();
    }

    //Creates a root folder (parent of itself per definition)
    public Folder() {
        // super calls the constructor of Element
        super(null, "root");
        parent = this;
        children = new HashSet<>();
    }

    // Returns a child with a given name
    // or null, if the name does not exist
    public Element getChild(String name) {
        // Iterate through all children
        for (Element child : children) {
            // Return the child, if the name fits
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    // Creates an empty file and adds it as one of the children
    // (only if the name does not exist before)
    public void addFile(String name) {
        // Check if name exists
        if (getChild(name) != null)
            System.out.println(name + " existiert bereits.");
        else
            // Add new file to the set of children
            children.add(new File(this, name));
    }

    // Creates an empty folder and adds it as one of the children
    // (only if the name does not exist before)
    public void addFolder(String name) {
        // Check if name exists
        if (getChild(name) != null)
            System.out.println(name + " existiert bereits.");
        else
            // Add new folder to the set of children
            children.add(new Folder(this, name));
    }

    // Handle commands until the folder is left (using cd ..)
    public void ui() {
        Scanner scanner = new Scanner(System.in);
        String input_line = scanner.nextLine();
        while (!input_line.equals("cd ..")) {
            handleInput(input_line.split(" "));
            input_line = scanner.nextLine();
        }
    }

    // Call the corresponding function for every possible command
    private void handleInput(String[] input) {
        switch (input[0]) {
            case "cd" -> handleCd(input);
            case "ls" -> handleLs(input);
            case "pwd" -> handlePwd(input);
            case "mkdir" -> handleMkdir(input);
            case "touch" -> handleTouch(input);
            case "cat" -> handleCat(input);
            case "nano" -> handleNano(input);
            default -> System.out.println("Befehl \"" + input[0] + "\" nicht gefunden.");
        }
    }

    private void handleCd(String[] input) {
        if (input.length != 2)
            System.out.println("\"cd ORDNER\" erwartet 1 Argument.");
        else if (!Element.isFolder(getChild(input[1])))
            System.out.println(input[1] + " ist kein Ordner.");
        else
            ((Folder) getChild(input[1])).ui();
    }

    private void handleLs(String[] input) {
        if (input.length != 1)
            System.out.println("\"ls\" erwartet keine Argumente.");
        else
            for (Element child : children) System.out.println(child.getName());
    }

    private void handlePwd(String[] input) {
        if (input.length != 1)
            System.out.println("\"pwd\" erwartet keine Argumente.");
        else
            System.out.println(getPath());
    }

    private void handleMkdir(String[] input) {
        if (input.length != 2)
            System.out.println("\"mkdir ORDNER\" erwartet 1 Argument.");
        else
            addFolder(input[1]);
    }

    private void handleTouch(String[] input) {
        if (input.length != 2)
            System.out.println("\"touch FILE\" erwartet 1 Argument.");
        else
            addFile(input[1]);
    }

    private void handleCat(String[] input) {
        if (input.length != 2)
            System.out.println("\"cat FILE\" erwartet 1 Argument.");
        else if (!Element.isFile(getChild(input[1])))
            System.out.println(input[1] + " ist keine Datei.");
        else
            System.out.println(((File) getChild(input[1])).read());
    }

    private void handleNano(String[] input) {
        if (input.length != 2)
            System.out.println("\"nano FILE\" erwartet 1 Argument.");
        else if (!Element.isFile(getChild(input[1])))
            System.out.println(input[1] + " ist keine Datei.");
        else {
            System.out.print(">>>");
            File f = (File) getChild(input[1]);
            String newline = new Scanner(System.in).nextLine();
            f.write(f.read() + "\n" + newline);
        }
    }
}
