package pl.graczyk.wall;

public class MyBlock implements Block {
    private final String color;
    private final String material;

    public MyBlock(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public String material() {
        return material;
    }
}
