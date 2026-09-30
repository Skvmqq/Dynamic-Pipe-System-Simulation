package PipeElement;

public class Pump extends ActiveElement {

    public Pump()
    {
        super("pump");
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
