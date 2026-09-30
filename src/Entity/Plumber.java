package Entity;

import PipeElement.PipeElement;

public class Plumber extends Entity {
    public PipeElement picked = null;

    public Plumber(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public boolean pickElement(PipeElement element)
    {
        if(picked == null)
        {
            picked = element;
            return true;
        }
        return false;
    }

    public void dropElement()
    {
        picked = null;
    }
}