package pl.graczyk.wall;

import java.util.List;

public class MyCompositeBlock implements CompositeBlock {
    private final String color;
    private final String material;
    private final List<Block> blocks;

    public MyCompositeBlock(String color, String material, List<Block> blocks) {
        this.color = color;
        this.material = material;
        this.blocks = blocks;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
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
