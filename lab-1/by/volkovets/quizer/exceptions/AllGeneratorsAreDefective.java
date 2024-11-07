package by.volkovets.quizer.exceptions;

public class AllGeneratorsAreDefective extends RuntimeException{
    public AllGeneratorsAreDefective() {
        super("All generators from your Quiz can't generate properly((:");
    }
}
