package exceptons;

public class FileWithThisExtensionDoesntExist extends Exception{
    public FileWithThisExtensionDoesntExist(String message) {
        super(message);
    }
}
