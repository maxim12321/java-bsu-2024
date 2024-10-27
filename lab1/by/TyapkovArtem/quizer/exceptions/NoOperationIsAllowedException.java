package lab1.by.TyapkovArtem.quizer.exceptions;

public class NoOperationIsAllowedException extends RuntimeException{
    public NoOperationIsAllowedException(String string){
        super(string);
    }
}
