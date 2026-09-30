package PipeElement;

public abstract class ActiveElement extends PipeElement {
    public ActiveElement(String name) {
        super(name);
    }

    @Override
    public void breakPipeElement() {
        return;
    }

    @Override
    public void fixPipeElement() {
        return;
    }
}
