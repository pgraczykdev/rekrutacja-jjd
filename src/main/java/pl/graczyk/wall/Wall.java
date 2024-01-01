package pl.graczyk.wall;

import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private final List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.color().equalsIgnoreCase(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .filter(block -> block.material().equalsIgnoreCase(material))
                .toList();
    }

    @Override
    public int count() {
        final List<Block> singleBlocks = filterOutTheCompositeBlocks();
        final int numberOfBlocksInSingleBlocks = singleBlocks.size();
        return countBlocksInCompositeBlocks() + numberOfBlocksInSingleBlocks;
    }

    private List<Block> filterOutTheCompositeBlocks() {
        return blocks.stream()
                .filter(block -> !(block instanceof CompositeBlock))
                .toList();
    }

    private int countBlocksInCompositeBlocks() {
        return blocks.stream()
                .filter(CompositeBlock.class::isInstance)
                .mapToInt(compositeBlock -> ((CompositeBlock) compositeBlock).getBlocks().size())
                .sum();
    }
}
