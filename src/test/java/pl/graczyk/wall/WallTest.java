package pl.graczyk.wall;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class WallTest {

    @Test
    public void should_return_optional_with_block_by_color_if_block_with_that_color_exists() {
        //given
        final Structure wall = new Wall(List.of(
                new MyBlock("Red", "Brick"),
                new MyBlock("Gray", "Concrete")
        ));
        String color = "red";
        //when
        Optional<Block> optionalBlock = wall.findBlockByColor(color);
        //then
        assertThat(optionalBlock.isPresent()).isTrue();
    }

    @Test
    public void should_return_empty_optional_if_block_with_that_color_does_not_exists() {
        //given
        final Structure wall = new Wall(List.of(
                new MyBlock("Red", "Brick"),
                new MyBlock("Gray", "Concrete")
        ));
        String color = "Random";
        //when
        Optional<Block> optionalBlock = wall.findBlockByColor(color);
        //then
        assertThat(optionalBlock.isEmpty()).isTrue();
    }

    @Test
    public void should_return_all_blocks_by_given_material1() {
        //given
        Block firstConcreteBlock = new MyBlock("Gray", "Concrete");
        Block secondConcreteBlock = new MyBlock("Gray", "Concrete");
        Block firstBrickBlock = new MyBlock("Red", "Brick");
        final Structure wall = new Wall(List.of(
                firstConcreteBlock, secondConcreteBlock, firstBrickBlock
        ));
        String material = "Concrete";
        //when
        List<Block> blocks = wall.findBlocksByMaterial(material);
        //then
        assertAll(
                () -> assertThat(blocks).size().isEqualTo(2),
                () -> assertThat(blocks).containsExactlyInAnyOrder(
                        firstConcreteBlock, secondConcreteBlock
                )
        );
    }

    @Test
    public void should_return_all_blocks_by_given_material2() {
        //given
        Block firstConcreteBlock = new MyBlock("Gray", "Concrete");
        Block secondConcreteBlock = new MyBlock("Gray", "Concrete");
        Block firstBrickBlock = new MyBlock("Red", "Brick");
        Block secondBrickBlock = new MyBlock("Red", "Brick");
        final Structure wall = new Wall(List.of(
                firstConcreteBlock, secondConcreteBlock, firstBrickBlock, secondBrickBlock
        ));
        String material = "Brick";
        //when
        List<Block> blocks = wall.findBlocksByMaterial(material);
        //then
        assertAll(
                () -> assertThat(blocks).size().isEqualTo(2),
                () -> assertThat(blocks).containsExactlyInAnyOrder(
                        firstBrickBlock, secondBrickBlock
                )
        );
    }

    @Test
    public void should_return_2_as_number_of_blocks() {
        //given
        final Structure wall = new Wall(List.of(
                new MyBlock("Red", "Brick"),
                new MyBlock("Gray", "Concrete")
        ));
        // when
        int numberOfBlocks = wall.count();
        //then
        assertThat(numberOfBlocks).isEqualTo(2);
    }

    @Test
    public void should_return_4_as_number_of_blocks() {
        //given
        final Structure wall = new Wall(List.of(
                new MyBlock("Red", "Brick"),
                new MyBlock("Red", "Brick"),
                new MyBlock("Gray", "Concrete"),
                new MyBlock("Gray", "Concrete")
        ));
        // when
        int numberOfBlocks = wall.count();
        //then
        assertThat(numberOfBlocks).isEqualTo(4);
    }

    @Test
    public void should_return_4_as_number_of_blocks_when_only_composite_block_is_present() {
        //given
        final Structure wall = new Wall(List.of(
                new MyCompositeBlock("Red-Gray", "Composite",
                        List.of(new MyBlock("Red", "Brick"),
                                new MyBlock("Red", "Brick"),
                                new MyBlock("Gray", "Concrete"),
                                new MyBlock("Gray", "Concrete")
                        ))
        ));
        //when
        int count = wall.count();
        //then
        assertThat(count).isEqualTo(4);
    }

    @Test
    public void should_return_6_as_number_of_blocks_when_composite_block_is_present() {
        //given
        final Structure wall = new Wall(List.of(
                new MyBlock("Red", "Brick"),
                new MyBlock("Red", "Brick"),
                new MyBlock("Gray", "Concrete"),
                new MyBlock("Gray", "Concrete"),
                new MyCompositeBlock("Red-Gray", "Composite",
                        List.of(new MyBlock("Red", "Brick"),
                                new MyBlock("Gray", "Concrete")))
        ));
        //when
        int count = wall.count();
        //then
        assertThat(count).isEqualTo(6);
    }

    @Test
    public void should_return_7_as_number_of_blocks_when_2_composite_blocks_are_present() {
        //given
        final Structure wall = new Wall(List.of(
                new MyBlock("Red", "Brick"),
                new MyBlock("Red", "Brick"),
                new MyBlock("Gray", "Concrete"),
                new MyBlock("Gray", "Concrete"),
                new MyCompositeBlock("Red-Gray", "Composite",
                        List.of(new MyBlock("Red", "Brick"),
                                new MyBlock("Gray", "Concrete"))),
                new MyCompositeBlock("Red-Gray", "Composite",
                        List.of(new MyBlock("Red", "Brick")))
        ));
        //when
        int count = wall.count();
        //then
        assertThat(count).isEqualTo(7);
    }

    @Test
    public void should_return_8_as_number_of_blocks_when_2_composite_blocks_are_present() {
        //given
        final Structure wall = new Wall(List.of(
                new MyBlock("Red", "Brick"),
                new MyBlock("Red", "Brick"),
                new MyBlock("Gray", "Concrete"),
                new MyBlock("Gray", "Concrete"),
                new MyCompositeBlock("Red-Gray", "Composite",
                        List.of(new MyBlock("Red", "Brick"),
                                new MyBlock("Gray", "Concrete"))),
                new MyCompositeBlock("Red-Gray", "Composite",
                        List.of(new MyBlock("Red", "Brick"),
                                new MyBlock("Gray", "Concrete")))
        ));
        //when
        int count = wall.count();
        //then
        assertThat(count).isEqualTo(8);
    }
}