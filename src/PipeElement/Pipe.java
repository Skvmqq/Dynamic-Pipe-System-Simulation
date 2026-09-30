package PipeElement;

public class Pipe extends PipeElement {

    public Pipe()
    {
        super("pipe");
    }

    @Override
    public void breakPipeElement() {
        broken = true;
    }

    @Override
    public void fixPipeElement() {
        broken = false;
    }
}
